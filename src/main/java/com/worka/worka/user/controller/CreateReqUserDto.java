package com.worka.worka.user.controller;

import com.worka.worka.user.domain.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReqUserDto {
	private String name;
	private Gender gender;
}
