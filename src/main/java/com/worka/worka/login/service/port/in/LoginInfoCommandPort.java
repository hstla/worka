package com.worka.worka.login.service.port.in;

import com.worka.worka.login.domain.LoginInfo;

public interface LoginInfoCommandPort {
    LoginInfo register(Long userId, String loginId, String password);

}
