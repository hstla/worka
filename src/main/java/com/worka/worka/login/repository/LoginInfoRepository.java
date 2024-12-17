package com.worka.worka.login.repository;

import com.worka.worka.common.exception.CommonErrorCode;
import com.worka.worka.common.exception.CommonException;
import com.worka.worka.login.domain.LoginInfo;
import com.worka.worka.login.repository.jpa.LoginInfoJpaRepository;
import com.worka.worka.login.service.port.out.LoginInfoOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginInfoRepository implements LoginInfoOutPort {
    private final LoginInfoJpaRepository loginInfoJpaRepository;

    @Override
    public LoginInfo save(LoginInfo loginInfo) {
        loginInfoJpaRepository.save(loginInfo);
        return loginInfo;
    }

    @Override
    public LoginInfo getByLoginId(String loginId) {
        return loginInfoJpaRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.INVALID_Id));
    }
}
