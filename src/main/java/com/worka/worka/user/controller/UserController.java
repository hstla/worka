package com.worka.worka.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worka.worka.user.service.ModifyUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
	private final ModifyUserService userService;

	@PatchMapping("/{id}")
	public void updateUserName(@PathVariable Long id, @RequestBody ModifyReqUserDto createReqUserDto) {
		userService.updateUser(id, createReqUserDto.getName(), createReqUserDto.getGender());
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deletedUser(id);
	}
}