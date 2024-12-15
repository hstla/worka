package com.worka.worka.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worka.worka.user.domain.Gender;
import com.worka.worka.user.domain.User;
import com.worka.worka.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	// todo name 중복 검증하기.
	@Override
	public Long createUser(String name, Gender gender) {
		User addUser = User.addUser(name, gender);
		User saveUser = userRepository.save(addUser);
		return saveUser.getId();
	}

	// 수정

	// 삭제



}