package com.worka.worka.login.domain;

import jakarta.persistence.*;
import lombok.*;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(of = "id")
public class LoginInfo {
    @Id @GeneratedValue
    private Long id;
    private String loginId;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private LoginStatus loginStatus;
    private Long userId;

    public static LoginInfo generate(Long userId, String loginId, String password) {
        return LoginInfo.builder()
                .loginId(loginId)
                .password(password)
                .userRole(UserRole.USER)
                .loginStatus(LoginStatus.ACTIVE)
                .userId(userId)
                .build();
    }
}
