package com.worka.worka.login.repository.jpa;

import com.worka.worka.login.domain.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginInfoJpaRepository extends JpaRepository<LoginInfo, Long> {
    Optional<LoginInfo> findByLoginId(String loginId);
}
