package com.prueba.cruduser.repository;

import com.prueba.cruduser.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
