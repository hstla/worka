package com.worka.worka.login.service;

import com.worka.worka.common.exception.CommonException;
import com.worka.worka.login.domain.LoginInfo;
import com.worka.worka.login.domain.LoginStatus;
import com.worka.worka.login.domain.UserRole;
import com.worka.worka.login.repository.jpa.LoginInfoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class LoginServiceTest {
    @Autowired private LoginInfoJpaRepository loginInfoJpaRepository;
    @Autowired private LoginService loginService;
    @Autowired private PasswordEncoder passwordEncoder;

    @BeforeEach
    void clear() {
        loginInfoJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 아이디, 비밀번호, 회원 key 값을 통해 로그인 정보를 등록할 수 있다.")
    void register() {
        // given
        Long userId = 1L;
        String loginId = "lee";
        String password = "qwe123";

        // when
        LoginInfo loginInfo = loginService.register(userId, loginId, password);

        // then
        assertThat(loginInfo.getUserId()).isEqualTo(userId);
        assertThat(loginInfo.getLoginId()).isEqualTo(loginId);
        assertThat(passwordEncoder.matches(password, loginInfo.getPassword())).isTrue();
    }

    @Test
    @DisplayName("로그인 정보를 등록하면 'userRole = USER', 'loginStatus = ACTIVE' 이다.")
    void register2() {
        // given
        Long userId = 1L;
        String loginId = "lee";
        String password = "qwe123";

        // when
        LoginInfo loginInfo = loginService.register(userId, loginId, password);

        // then
        assertThat(loginInfo.getUserRole()).isEqualTo(UserRole.USER);
        assertThat(loginInfo.getLoginStatus()).isEqualTo(LoginStatus.ACTIVE);
    }

    @Test
    @DisplayName("loginId를 통해 loginInfo 를 조회할 수 있다.")
    void getByLoginId() {
        // given
        Long userId = 1L;
        String loginId = "lee";
        String password = "qwe123";
        loginService.register(userId, loginId, password);

        // when
        LoginInfo loginInfo = loginService.getByLoginId(loginId);

        // then
        assertThat(loginInfo.getLoginId()).isEqualTo(loginId);
    }

    @Test
    @DisplayName("잘못된 loginId를 통해 loginInfo 를 조회하면 Exception 이 발생한다.")
    void getByLoginId2() {
        // given
        String loginId = "lee1234";

        // expected
        assertThatThrownBy(() -> loginService.getByLoginId(loginId))
                .isInstanceOf(CommonException.class);
    }
}