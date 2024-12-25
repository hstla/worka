package com.worka.worka.user.service;

import com.worka.worka.user.domain.Gender;

public interface ModifyUserService {
	void updateUser(Long userId, String name, Gender gender);
	void deletedUser(Long id);
}
