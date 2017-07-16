package org.superdev.coddy.user.elasticsearch.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.superdev.coddy.application.elasticsearch.dao.IDAO;
import org.superdev.coddy.user.elasticsearch.entity.UserEntity;
import org.superdev.coddy.user.elasticsearch.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDAO implements IDAO<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity entity) {
        return this.userRepository.save(entity);
    }

    @Override
    public void delete(UserEntity entity){
        this.userRepository.delete(entity);
    }

    @Override
    public UserEntity find(String id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public List<UserEntity> findAll(final int from, final int size) {

        Pageable page = new PageRequest(from, size);

        List<UserEntity> list = new ArrayList<>();
        this.userRepository.findAll(page).forEach(list::add);
        return list;
    }

    public UserEntity findByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }

    public boolean isExist(String login) {
        return this.findByLogin(login) != null;
    }
}
