package com.worka.worka.loginuser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.worka.worka.loginuser.domain.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    boolean existsByLoginId(String loginId);
    boolean existsByLoginIdAndPassword(String loginId, String password);
    Optional<LoginUser> findByLoginId(String loginId);
    Optional<LoginUser> findByLoginIdAndPassword(String loginId, String password);
}
