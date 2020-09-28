package com.nnt.banhang.repository;

import org.springframework.data.repository.CrudRepository;

import com.nnt.banhang.entity.role;

public interface RoleRepository extends CrudRepository<role, Integer> {

    role findByName(String name);

}
