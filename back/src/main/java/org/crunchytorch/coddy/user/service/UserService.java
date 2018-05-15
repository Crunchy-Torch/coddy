package org.crunchytorch.coddy.user.service;

import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.BadRequestException;
import org.crunchytorch.coddy.application.exception.EntityExistsException;
import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.application.service.AbstractService;
import org.crunchytorch.coddy.security.data.JWTToken;
import org.crunchytorch.coddy.security.data.Permission;
import org.crunchytorch.coddy.security.service.JWTService;
import org.crunchytorch.coddy.security.utils.SecurityUtils;
import org.crunchytorch.coddy.user.data.in.Credential;
import org.crunchytorch.coddy.user.data.in.UpdateUser;
import org.crunchytorch.coddy.user.data.out.SimpleUser;
import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;
import org.crunchytorch.coddy.user.elasticsearch.repository.UserRepository;
import org.crunchytorch.coddy.user.exception.AuthenticationException;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService<UserEntity> {

    private final JWTService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, JWTService jwtService) {
        super(userRepository);
        this.jwtService = jwtService;
    }

    /**
     * This method will authenticate the user from the given {@link Credential credential}. To achieve this goal, there are three steps :
     * 1. Check if the {@link UserEntity user} exists
     * 2. Check if the user's password in the {@link Credential credential's object} is correct
     * 3. Generate the token from the {@link UserEntity user information}
     *
     * @param credential the credential used to authenticate the {@link UserEntity user}
     * @return a valid {@link JWTToken token}
     * @throws AuthenticationException if the user is not found or the password is wrong.
     */
    public JWTToken authenticate(Credential credential) {
        UserEntity entity = UserRepository.class.cast(this.repository).findByLogin(credential.getLogin());

        if (entity == null) {
            throw new AuthenticationException(Response.WRONG_CREDENTIAL);
        }

        if (!SecurityUtils.isExpectedPassword(credential.getPassword(), entity.getSalt(), entity.getPassword())) {
            throw new AuthenticationException(Response.WRONG_CREDENTIAL);
        }

        return this.jwtService.generateToken(entity);
    }

    /**
     * This method will create the {@link UserEntity user} from the {@link UpdateUser credentials}.
     * Before creating it, the given password will be salted and hashed.
     *
     * @param user the personal information needed to create the associated user
     * @return the {@link SimpleUser user} created
     * @throws EntityExistsException if the given user already exists
     * @throws BadRequestException   if the given user has no login
     */
    public SimpleUser create(UpdateUser user) {
        List<String> permissions = new ArrayList<>();
        permissions.add(Permission.USER);
        return this.create(user, permissions);
    }

    public SimpleUser create(UpdateUser user, List<String> permissions) {
        if (user.getLogin() == null || user.getPassword() == null) {
            throw new BadRequestException("login and password cannot be null");
        }

        // check if the current login already exist
        if (this.exists(user.getLogin())) {
            throw new EntityExistsException("user with login : " + user.getLogin() + " already exists");
        }

        // remove unknown permission
        List<String> filteredPermissions = permissions.stream().filter(Permission::isValid).collect(Collectors.toList());

        //generate entity, hash and salt the password
        UserEntity entity = new UserEntity(user, filteredPermissions);

        return new SimpleUser(super.create(entity));
    }

    /**
     * @param user the personal information needed to update the associated user
     * @return the {@link SimpleUser user} updated
     * @throws EntityNotFoundException if the given user is not found
     * @throws BadRequestException     if the given user has no login
     */
    public SimpleUser update(UpdateUser user) {

        if (user.getLogin() == null) {
            throw new BadRequestException("login cannot be null");
        }

        UserEntity oldEntity = this.getUserEntityByLogin(user.getLogin());

        UserEntity entity = new UserEntity(user, oldEntity);

        return new SimpleUser(super.create(entity));
    }

    /**
     * This method will delete the {@link UserEntity user} from the given login.
     *
     * @param login the user's login
     * @throws EntityNotFoundException if the given login is not associated to a {@link UserEntity user}
     */
    public void delete(final String login) {
        super.delete(this.getUserEntityByLogin(login));
    }

    public List<SimpleUser> search(final String loginToSearch, final int from, final int size) {
        final Pageable page = new PageRequest(from, size);
        final List<SimpleUser> list = new ArrayList<>();
        this.repository.search(QueryBuilders.regexpQuery("login", ".*" + loginToSearch + ".*"), page)
                .forEach(entity -> list.add(new SimpleUser(entity)));
        return list;
    }

    public SimpleUser getUserByLogin(String login) {
        return new SimpleUser(this.getUserEntityByLogin(login));
    }

    public boolean exists(String login) {
        return UserRepository.class.cast(this.repository).findByLogin(login) != null;
    }

    private UserEntity getUserEntityByLogin(final String login) {
        UserEntity entity = UserRepository.class.cast(this.repository).findByLogin(login);

        if (entity == null) {
            throw new EntityNotFoundException("user with the login : " + login + " not found");
        }

        return entity;
    }
}
