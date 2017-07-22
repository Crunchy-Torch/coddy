package org.crunchytorch.coddy.user.elasticsearch.repository;


import org.crunchytorch.coddy.user.elasticsearch.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<UserEntity, String> {

    UserEntity findByLogin(String login);

}
