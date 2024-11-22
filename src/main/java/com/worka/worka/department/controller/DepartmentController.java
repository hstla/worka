package com.worka.worka.department.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worka.worka.department.controller.dto.DepartmentReqDto;
import com.worka.worka.department.controller.dto.DepartmentResDto;
import com.worka.worka.department.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	@PostMapping("/departments")
	public ResponseEntity<DepartmentResDto> add(@Valid @RequestBody DepartmentReqDto departmentReqDto) {
		departmentService.createDepartment(departmentReqDto.getName(), departmentReqDto.getParentId());
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.build();
	}

}
