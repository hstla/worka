package com.worka.worka.user.domain;

import lombok.Getter;

@Getter
public enum Gender{
	UNSPECIFIED("선택안함"),
	MALE("남자"),
	FEMALE("여자");

	private String displayName;

	Gender(String name) {
		this.displayName = name;
	}

	public String getDisplayName() {
		return displayName;
	}
}
