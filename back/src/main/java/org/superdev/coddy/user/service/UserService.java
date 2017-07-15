package org.superdev.coddy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.application.exception.EntityNotFoundException;
import org.superdev.coddy.user.data.Credential;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.elasticsearch.dao.UserDAO;
import org.superdev.coddy.application.exception.EntityExistsException;
import org.superdev.coddy.user.utils.SecurityUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

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
