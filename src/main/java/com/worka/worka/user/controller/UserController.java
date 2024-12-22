package com.worka.worka.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
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
	public void createUser(@RequestBody CreateReqUserDto createReqUserDto) {
		userService.createUser(createReqUserDto.getName(), createReqUserDto.getGender());
	}

	@PostMapping
	public void updateUserName(@RequestBody CreateReqUserDto createReqUserDto) {
		userService.updateName(createReqUserDto.getName(), createReqUserDto.getGender());
	}

	@PostMapping
	public void updateUserGender(@RequestBody CreateReqUserDto createReqUserDto) {
		userService.updateGender(createReqUserDto.getName(), createReqUserDto.getGender());
	}

	@DeleteMapping
	public void deleteUser(@RequestBody CreateReqUserDto createReqUserDto) {
		userService.deletedUser(createReqUserDto.getName(), createReqUserDto.getGender());
	}

}
