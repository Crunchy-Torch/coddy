package org.crunchytorch.coddy.user.service;

import org.crunchytorch.coddy.user.data.Permission;
import org.crunchytorch.coddy.user.data.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.crunchytorch.coddy.application.data.Response;
import org.crunchytorch.coddy.application.exception.EntityNotFoundException;
import org.crunchytorch.coddy.application.service.AbstractService;
import org.crunchytorch.coddy.user.data.Credential;
import org.crunchytorch.coddy.user.data.Token;
import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;
import org.crunchytorch.coddy.application.exception.EntityExistsException;
import org.crunchytorch.coddy.user.elasticsearch.repository.UserRepository;
import org.crunchytorch.coddy.user.exception.AuthenticationException;
import org.crunchytorch.coddy.user.utils.SecurityUtils;

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
     * @return the {@link UserEntity user} created
     * @throws EntityExistsException if the given user already exists
     */
    public SimpleUser create(Credential credential) {
        // check if the current login already exist
        if (this.isExist(credential.getLogin())) {
            throw new EntityExistsException("user with login : " + credential.getLogin() + " already exists");
        }

        //generate entity, hash and salt the password
        UserEntity entity = new UserEntity();
        entity.setLogin(credential.getLogin());
        byte[] salt = SecurityUtils.generateSalt();
        byte[] password = SecurityUtils.hash(credential.getPassword(), salt);
        entity.setSalt(salt);
        entity.setPassword(password);
        entity.setPermissions(new ArrayList<String>() {{
            add(Permission.PERSO_ACCOUNT);
            add(Permission.PERSO_SNIPPET);
        }});

        return new SimpleUser(super.create(entity));
    }

    /**
     * This method will delete the {@link UserEntity user} from the given login.
     *
     * @param login the user's login
     * @throws EntityNotFoundException if the given login is not associated to a {@link UserEntity user}
     */
    public void delete(final String login) {
        UserEntity entity = UserRepository.class.cast(this.repository).findByLogin(login);

        if (entity == null) {
            throw new EntityNotFoundException("user with the login : " + login + " not found");
        }
        super.delete(entity);
    }

    public SimpleUser getUserByLogin(String login) {
        UserEntity entity = UserRepository.class.cast(this.repository).findByLogin(login);

        if (entity == null) {
            throw new EntityNotFoundException("user with the login : " + login + " not found");
        }

        return new SimpleUser(entity);
    }

    private boolean isExist(String login) {
        return UserRepository.class.cast(this.repository).findByLogin(login) != null;
    }
}
