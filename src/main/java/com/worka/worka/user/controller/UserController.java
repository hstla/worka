package com.worka.worka.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worka.worka.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	@PostMapping
	public void createUser(@RequestBody CreateReqUser createReqUser) {
		userService.createUser(createReqUser.getName(), createReqUser.getGender());
	}

}
