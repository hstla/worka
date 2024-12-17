package com.worka.worka.login.service;

import com.worka.worka.login.domain.LoginInfo;
import com.worka.worka.login.service.port.in.LoginInfoCommandPort;
import com.worka.worka.login.service.port.in.LoginInfoInquiryPort;
import com.worka.worka.login.service.port.out.LoginInfoOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements LoginInfoCommandPort, LoginInfoInquiryPort {
    private final LoginInfoOutPort loginInfoOutPort;
    private final PasswordEncoder passwordEncoder;

    public LoginInfo register(Long userId, String loginId, String password) {
        LoginInfo loginInfo = LoginInfo.generate(userId, loginId, passwordEncoder.encode(password));
        loginInfoOutPort.save(loginInfo);

        return loginInfo;
    }

    public LoginInfo getByLoginId(String loginId) {
        return loginInfoOutPort.getByLoginId(loginId);
    }
}
