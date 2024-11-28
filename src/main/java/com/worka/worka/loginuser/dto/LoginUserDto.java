package com.worka.worka.loginuser.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginUserDto {
    private String loginId;

    private String password;

    private String passwordCheck;

    private String newPassword;

    private String empNumber;

    private int failCount;

    @Builder
    public LoginUserDto(String loginId, String password, String passwordCheck, String newPassword, String empNumber, int failCount) {
        this.loginId = loginId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.newPassword = newPassword;
        this.empNumber = empNumber;
        this.failCount = failCount;
    }
}
