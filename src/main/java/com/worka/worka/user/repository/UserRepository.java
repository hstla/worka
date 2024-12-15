package com.worka.worka.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.worka.worka.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}