package com.worka.worka.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worka.worka.user.domain.Gender;
import com.worka.worka.user.domain.User;
import com.worka.worka.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl extends UserService {
	private final UserRepository userRepository;

	// todo name 중복 검증하기.
	@Override
	public Long createUser(String name, Gender gender) {
		if (userRepository.existsByName(name)) {
			throw new RuntimeException("중복된 이름입니다.");
		}

		User addUser = User.addUser(name, gender);
		User saveUser = userRepository.save(addUser);
		return saveUser.getId();
	}

	// 수정 성별, 이름 변경하기.
	public void updateName(Long userId, String updateName) {
		User findById = userRepository.getReferenceById(userId);
		findById.updateName(updateName);
	}

	public void updateGender(Long userId, Gender gender) {
		User findById = userRepository.getReferenceById(userId);
		findById.updateGender(gender);
	}

	// 삭제 아이디로 삭제
	public void deleteUser(Long userId) {
		// User findById = userRepository.getReferenceById(userId);
		userRepository.deleteById(userId);
	}
}