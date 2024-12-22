package com.worka.worka.user.service;

import com.worka.worka.user.domain.Gender;

public abstract class UserService {
	public abstract Long createUser(String name, Gender gender);


}

