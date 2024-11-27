package com.worka.worka.department.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.worka.worka.department.controller.dto.DepartmentReadResDto;
import com.worka.worka.department.domain.Department;
import com.worka.worka.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentQueryService {

	private final DepartmentRepository departmentRepository;

	/**
	 * 부서 읽기
	 * 1. 모든 부서를 읽어온다.
	 * 2. 부서아이디를 받으면 밑에 있는 부서까지 다 읽어온다.
	 */
	public List<DepartmentReadResDto> readAllDepartment() {
		List<Department> topDepthDepartments = departmentRepository.findTopDepthDepartments();

		return topDepthDepartments.stream()
			.map(DepartmentReadResDto::new)
			.collect(Collectors.toList());
	}

	public List<DepartmentReadResDto> readDepartment(Long departmentId) {
		Department department = departmentRepository.findById(departmentId)
			.orElseThrow(() -> new RuntimeException("부서를 찾을 수 없습니다. " + departmentId));
		List<Department> departmentWithChildren = departmentRepository.findDepartmentWithChildren(department.getId());

		return departmentWithChildren.stream()
			.map(DepartmentReadResDto::new)
			.collect(Collectors.toList());
	}
}