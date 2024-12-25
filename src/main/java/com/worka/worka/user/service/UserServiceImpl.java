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
public class UserServiceImpl implements CreateUserService, ModifyUserService {
	private final UserRepository userRepository;

	@Override
	public Long createUser(String name, Gender gender) {
		if (userRepository.existsByName(name)) {
			throw new RuntimeException("중복된 이름입니다.");
		}

		User addUser = User.addUser(name, gender);
		User saveUser = userRepository.save(addUser);
		return saveUser.getId();
	}

	// 수정 성별, 이름 한번에 변경하기.
	@Override
	public void updateUser(Long userId, String updateName, Gender gender) {
		User findById = userRepository.getReferenceById(userId);
		findById.updateUser(updateName, gender);
	}

	// 삭제 아이디로 삭제
	@Override
	public void deletedUser(Long userId) {
		// User findById = userRepository.getReferenceById(userId);
		userRepository.deleteById(userId);
	}
}