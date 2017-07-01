package org.superdev.coddy.user.elasticsearch.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.superdev.coddy.user.data.UserEntity;

public interface UserRepository extends ElasticsearchRepository<UserEntity, String> {

    UserEntity findByLogin(String login);

}
