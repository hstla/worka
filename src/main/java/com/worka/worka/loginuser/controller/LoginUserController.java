package com.worka.worka.loginuser.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.worka.worka.exception.comm.ErrorException;
import com.worka.worka.loginuser.dto.LoginUserDto;
import com.worka.worka.loginuser.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginUserController {

    private final LoginUserService loginService;

    @PostMapping( "/join")
    public void join(@RequestBody LoginUserDto loginDto) {

        if( ! loginDto.getPassword().equals( loginDto.getPasswordCheck())) {
            throw new ErrorException( "비밀번호가 일치하지 않습니다.");
        }
        loginService.create( loginDto);
    }

    @PatchMapping( "/edit")
    public void edit(@RequestBody LoginUserDto loginDto) {
        loginService.edit( loginDto);
    }

    @DeleteMapping("/delete/{loginId}/{password}")
    public void delete(@PathVariable String loginId, @PathVariable String password) {
        loginService.delete(loginId, password);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginUserDto loginUserDto) {
        loginService.login(loginUserDto);
    }
}
