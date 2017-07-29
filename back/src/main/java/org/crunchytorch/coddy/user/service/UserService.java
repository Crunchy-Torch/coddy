package org.crunchytorch.coddy.user.service;

import org.crunchytorch.coddy.user.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.application.service.AbstractService;
import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;
import org.crunchytorch.coddy.application.exception.EntityExistsException;
import org.crunchytorch.coddy.user.elasticsearch.repository.UserRepository;
import org.crunchytorch.coddy.user.exception.AuthenticationException;
import org.crunchytorch.coddy.user.utils.SecurityUtils;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;

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
     * @return a valid {@link Token token}
     * @throws AuthenticationException if the user is not found or the password is wrong.
     */
    public Token authenticate(Credential credential) {
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
     * This method will create the {@link UserEntity user} from the {@link Credential credentials}.
     * Before creating it, the given password will be salted and hashed.
     *
     * @param credential the personnal information needed to create the associated user
     * @return the {@link SimpleUser user} created
     * @throws EntityExistsException if the given user already exists
     * @throws BadRequestException   if the given user has no login
     */
    public SimpleUser create(Credential credential) {

        if (credential.getLogin() == null) {
            throw new BadRequestException("login cannot be null");
        }

        // check if the current login already exist
        if (this.isExist(credential.getLogin())) {
            throw new EntityExistsException("user with login : " + credential.getLogin() + " already exists");
        }

        //generate entity, hash and salt the password
        UserEntity entity = new UserEntity();
        entity.setLogin(credential.getLogin());
        entity.generatePasswordAndSalt(credential.getPassword());
        entity.setPermissions(new ArrayList<String>() {{
            add(Permission.PERSO_ACCOUNT);
            add(Permission.PERSO_SNIPPET);
        }});

        return new SimpleUser(super.create(entity));
    }

    /**
     * @param user
     * @return the {@link SimpleUser user} updated
     * @throws EntityNotFoundException if the given user is not found
     * @throws BadRequestException     if the given user has no login
     */
    public SimpleUser update(UpdateUser user) {
        UserEntity entity = new UserEntity();

        if (user.getLogin() == null) {
            throw new BadRequestException("login cannot be null");
        }

        UserEntity oldEntity = this.getUserEntityByLogin(user.getLogin());

        // the following data cannot be modified
        entity.setId(oldEntity.getId());
        entity.setLogin(oldEntity.getLogin());
        entity.setPermissions(oldEntity.getPermissions());

        // now the rest of the data can be updated
        if (user.getPassword() != null) {
            entity.generatePasswordAndSalt(user.getPassword());
        } else {
            entity.setPassword(oldEntity.getPassword());
            entity.setSalt(oldEntity.getSalt());
        }

        entity.setFirstName(user.getFirstName() != null ? user.getFirstName() : oldEntity.getFirstName());
        entity.setLastName(user.getLastName() != null ? user.getLastName() : oldEntity.getLastName());

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

    public SimpleUser getUserByLogin(String login) {
        return new SimpleUser(this.getUserEntityByLogin(login));
    }

    private UserEntity getUserEntityByLogin(final String login) {
        UserEntity entity = UserRepository.class.cast(this.repository).findByLogin(login);

        if (entity == null) {
            throw new EntityNotFoundException("user with the login : " + login + " not found");
        }

        return entity;
    }

    private boolean isExist(String login) {
        return UserRepository.class.cast(this.repository).findByLogin(login) != null;
    }
}
