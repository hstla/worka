package com.worka.worka.user.service;

import com.worka.worka.user.domain.Gender;

public interface CreateUserService {
	Long createUser(String name, Gender gender);
}