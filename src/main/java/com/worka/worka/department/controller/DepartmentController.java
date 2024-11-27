package com.worka.worka.department.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.worka.worka.department.controller.dto.DepartmentParentPutReqDto;
import com.worka.worka.department.controller.dto.DepartmentReqDto;
import com.worka.worka.department.controller.dto.DepartmentResDto;
import com.worka.worka.department.controller.dto.DepartmentNamePutReqDto;
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

	@PutMapping("/departments/id")
	public ResponseEntity<DepartmentResDto> updateDepartmentId(@Valid @RequestBody DepartmentNamePutReqDto departmentReqDto) {
		departmentService.updateNameDepartment(departmentReqDto.getDepartmentId(), departmentReqDto.getName());
		return ResponseEntity
			.status(HttpStatus.OK)
			.build();
	}

	@PutMapping("/departments/parent")
	public ResponseEntity<DepartmentResDto> updateDepartmentParent(@Valid @RequestBody DepartmentParentPutReqDto departmentReqDto) {
		departmentService.updateParentDepartment(departmentReqDto.getDepartmentId(), departmentReqDto.getParentId());
		return ResponseEntity
			.status(HttpStatus.OK)
			.build();
	}

	@DeleteMapping("/departments")
	public ResponseEntity<DepartmentResDto> deleteDepartment(@Valid @RequestBody DepartmentParentPutReqDto departmentReqDto) {
		departmentService.deleteDepartment(departmentReqDto.getDepartmentId());
		return ResponseEntity
			.status(HttpStatus.OK)
			.build();
	}

}
