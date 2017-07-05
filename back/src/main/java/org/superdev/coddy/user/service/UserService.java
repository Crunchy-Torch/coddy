package org.superdev.coddy.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.elasticsearch.dao.UserDAO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserEntity getUserByLogin(String login) {
        return this.userDAO.findByLogin(login);
    }
}
