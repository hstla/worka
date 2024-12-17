package com.worka.worka.login.service.port.in;

import com.worka.worka.login.domain.LoginInfo;

public interface LoginInfoInquiryPort {
    LoginInfo getByLoginId(String loginId);
}
