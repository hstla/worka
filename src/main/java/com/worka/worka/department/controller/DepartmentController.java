package com.worka.worka.department.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worka.worka.department.controller.dto.DepartmentParentPutReqDto;
import com.worka.worka.department.controller.dto.DepartmentCreateReqDto;
import com.worka.worka.department.controller.dto.DepartmentCreateResDto;
import com.worka.worka.department.controller.dto.DepartmentNamePutReqDto;
import com.worka.worka.department.controller.dto.DepartmentReadResDto;
import com.worka.worka.department.service.DepartmentQueryService;
import com.worka.worka.department.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;
	private final DepartmentQueryService departmentQueryService;

	@PostMapping("/departments")
	public ResponseEntity<DepartmentCreateResDto> add(@Valid @RequestBody DepartmentCreateReqDto departmentReqDto) {
		departmentService.createDepartment(departmentReqDto.getName(), departmentReqDto.getParentId());
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.build();
	}

	@PutMapping("/departments/id")
	public ResponseEntity<DepartmentCreateResDto> updateDepartmentId(@Valid @RequestBody DepartmentNamePutReqDto departmentReqDto) {
		departmentService.updateNameDepartment(departmentReqDto.getDepartmentId(), departmentReqDto.getName());
		return ResponseEntity
			.status(HttpStatus.OK)
			.build();
	}

	@PutMapping("/departments/parent")
	public ResponseEntity<DepartmentCreateResDto> updateDepartmentParent(@Valid @RequestBody DepartmentParentPutReqDto departmentReqDto) {
		departmentService.updateParentDepartment(departmentReqDto.getDepartmentId(), departmentReqDto.getParentId());
		return ResponseEntity
			.status(HttpStatus.OK)
			.build();
	}

	@DeleteMapping("/departments")
	public ResponseEntity<DepartmentCreateResDto> deleteDepartment(@Valid @RequestBody DepartmentParentPutReqDto departmentReqDto) {
		departmentService.deleteDepartment(departmentReqDto.getDepartmentId());
		return ResponseEntity
			.status(HttpStatus.OK)
			.build();
	}

	@GetMapping("/departments")
	public ResponseEntity<List<DepartmentReadResDto>> getAllDepartment() {
		List<DepartmentReadResDto> AllDepartment = departmentQueryService.readAllDepartment();
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(AllDepartment);
	}

	@GetMapping("/departments/{departmentId}")
	public ResponseEntity<List<DepartmentReadResDto>> getAllDepartment(@RequestParam  Long departmentId) {

		List<DepartmentReadResDto> AllDepartment = departmentQueryService.readAllDepartment();
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(AllDepartment);
	}
}
