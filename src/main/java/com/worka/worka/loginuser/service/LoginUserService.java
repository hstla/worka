package com.worka.worka.loginuser.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.worka.worka.exception.comm.ErrorException;
import com.worka.worka.exception.comm.NotFoundException;
import com.worka.worka.loginuser.domain.LoginUser;
import com.worka.worka.loginuser.dto.LoginUserDto;
import com.worka.worka.loginuser.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final LoginUserRepository loginRepository;

    public void create( LoginUserDto loginDto) {
        String empNumber = loginDto.getEmpNumber();
        String loginId = loginDto.getLoginId();
        if( loginRepository.existsByLoginId( loginId)) {
            throw new ErrorException( "이미 존재하는 아이디 입니다."); // 이미 존재하는 아이디 입니다.
        }

        if( empNumber.equals( "")) {
            throw new ErrorException("존재하지 않는 사번이거나 이미 사용중인 사번입니다."); // 존재하지 않는 사번이거나 이미 사용중인 사번입니다.
        }
        LoginUser loginUser = LoginUser.builder().loginId( loginId)
            .password(loginDto.getPassword())
            .passwordCheck(loginDto.getPasswordCheck())
            .empNumber(loginDto.getEmpNumber())
            .failCount(0)
            .build();
        loginRepository.save(loginUser);
    }

    @Transactional
    public void edit( LoginUserDto loginDto) {
        LoginUser login = loginRepository.findByLoginId( loginDto.getLoginId()).orElseThrow(null);
        if( ! loginRepository.existsByLoginIdAndPassword( loginDto.getLoginId(), loginDto.getPassword()) || ! loginDto.getPassword().equals( loginDto.getPasswordCheck())) {
            throw new ErrorException("비밀번호를 확인해주세요."); // 비밀번호를 확인해주세요.
        }

        if( login.equals( null)) {
            throw new ErrorException("존재하지 않는 아이디 입니다."); // 존재하지 않는 아이디 입니다.
        }

        // 이미 사용중인 사원번호 입니다.
        LoginUserDto.LoginUserDtoBuilder loginDtoBuilder = login.toEditor();
        LoginUserDto editLogin = loginDtoBuilder.password( loginDto.getPassword())
            .empNumber(loginDto.getEmpNumber())
            .build();
        login.edit( editLogin);
    }

    public void delete(String loginId, String password) {
        LoginUser login = loginRepository.findByLoginIdAndPassword(loginId, password).orElseThrow(NotFoundException::new);
        loginRepository.delete(login);
    }

    public void login( LoginUserDto loginUserDto) {
        LoginUser login = loginRepository.findByLoginId( loginUserDto.getLoginId()).orElseThrow(null);
        if( login.equals( null)) {
            throw new ErrorException("존재하지 않거나 아이디거나 비밀번호가 틀렸습니다."); // 존재하지 않거나 아이디거나 비밀번호가 틀렸습니다.
        }else if( ! login.getPassword().equals( loginUserDto.getPassword())){
            LoginUserDto.LoginUserDtoBuilder loginDtoBuilder = login.toEditor();
            LoginUserDto editLogin = loginDtoBuilder.failCount( login.getFailCount()+1)
                .build();
            login.edit( editLogin);
            throw new ErrorException("존재하지 않거나 아이디거나 비밀번호가 틀렸습니다."); //
        }
    }
}
