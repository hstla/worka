package com.worka.worka.department.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentReqDto {
	@NotNull
	private String name;
	private Long parentId;

	public DepartmentReqDto(String name, Long parentId) {
		this.name = name;
		this.parentId = parentId;
	}
}