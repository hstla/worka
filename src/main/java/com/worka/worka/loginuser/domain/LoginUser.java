package com.worka.worka.loginuser.domain;

import static lombok.AccessLevel.*;
import org.antlr.v4.runtime.misc.NotNull;
import com.worka.worka.loginuser.dto.LoginUserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class LoginUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "EMP_NUMBER")
    @NotNull
    private String empNumber;

    @Column(name = "FAIL_COUNT")
    private int failCount;

    @Builder
    public LoginUser(Long id, String loginId, String password, String passwordCheck, String empNumber, int failCount) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.empNumber = empNumber;
        this.failCount = failCount;
    }

    public LoginUserDto.LoginUserDtoBuilder toEditor() {
        return LoginUserDto.builder()
            .loginId(loginId)
            .password(password)
            .empNumber(empNumber)
            .failCount(failCount);
    }

    public void edit(LoginUserDto loginDto) {
        password = loginDto.getPassword();
        empNumber = loginDto.getEmpNumber();
        failCount = loginDto.getFailCount();
    }
}
