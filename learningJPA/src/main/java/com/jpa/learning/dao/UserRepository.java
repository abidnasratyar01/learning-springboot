package com.jpa.learning.dao;

import org.springframework.data.repository.CrudRepository;

import com.jpa.learning.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
