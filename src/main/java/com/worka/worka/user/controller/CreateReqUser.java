package com.worka.worka.user.controller;

import com.worka.worka.user.domain.Gender;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReqUser {
	private String name;
	private Gender gender;
}
