package com.worka.worka.user.service;

import com.worka.worka.user.domain.Gender;

public interface UserService {
	public Long createUser(String name, Gender gender);
}
