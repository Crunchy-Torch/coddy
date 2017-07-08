package org.superdev.coddy.user.elasticsearch.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superdev.coddy.application.elasticsearch.dao.IDAO;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.elasticsearch.repository.UserRepository;

@Service
public class UserDAO implements IDAO<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity entity) {
        return this.userRepository.save(entity);
    }

    @Override
    public UserEntity find(String id) {
        return this.userRepository.findOne(id);
    }

    public UserEntity findByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }
}
