package com.nnt.banhang.repository;

import org.springframework.data.repository.CrudRepository;

import com.nnt.banhang.entity.users;

public interface UserRepository extends CrudRepository<users, Integer> {

    users findByEmail(String email);

}