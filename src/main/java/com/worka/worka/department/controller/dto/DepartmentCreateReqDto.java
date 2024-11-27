package com.worka.worka.department.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentCreateReqDto {
	@NotNull
	private String name;
	private Long parentId;

	public DepartmentCreateReqDto(String name, Long parentId) {
		this.name = name;
		this.parentId = parentId;
	}
}