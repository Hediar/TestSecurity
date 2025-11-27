package com.example.TestSecurity.repository;

import com.example.TestSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // 1번인자: repo Entity, 2번인자: id값의 타입

}
