package org.superdev.coddy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.application.data.Response;
import org.superdev.coddy.application.exception.EntityNotFoundException;
import org.superdev.coddy.user.data.Credential;
import org.superdev.coddy.user.data.Token;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.elasticsearch.dao.UserDAO;
import org.superdev.coddy.application.exception.EntityExistsException;
import org.superdev.coddy.user.exception.AuthenticationException;
import org.superdev.coddy.user.utils.SecurityUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JWTService jwtService;

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
        UserEntity entity = this.userDAO.findByLogin(credential.getLogin());

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
    public UserEntity create(Credential credential) {
        // check if the current login already exist
        if (this.userDAO.isExist(credential.getLogin())) {
            throw new EntityExistsException("user with login : " + credential.getLogin() + " already exists");
        }

        //generate entity, hash and salt the password
        UserEntity entity = new UserEntity();
        entity.setLogin(credential.getLogin());
        byte[] salt = SecurityUtils.generateSalt();
        byte[] password = SecurityUtils.hash(credential.getPassword(), salt);
        entity.setSalt(salt);
        entity.setPassword(password);
        return this.userDAO.create(entity);
    }

    /**
     * This method will delete the {@link UserEntity user} from the given login.
     *
     * @param login the user's login
     * @throws EntityNotFoundException if the given login is not associated to a {@link UserEntity user}
     */
    public void delete(final String login) {
        this.userDAO.delete(this.getUserByLogin(login));
    }


    public List<UserEntity> getUsers(final int from, final int size) {
        return this.userDAO.findAll(from, size);
    }

    public UserEntity getUserByLogin(String login) {
        UserEntity entity = this.userDAO.findByLogin(login);

        if (entity == null) {
            throw new EntityNotFoundException("user with the login : " + login + " not found");
        }

        //remove password before send it to the consumer in order to not send how a password looks like
        entity.setPassword(null);
        entity.setSalt(null);


        return entity;
    }
}
