package com.spring.security.spring.security.practical.repository;

import com.spring.security.spring.security.practical.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseUserRepository extends JpaRepository<BaseUser,Long> {

    Optional<BaseUser> findByUserName(String username);
}
