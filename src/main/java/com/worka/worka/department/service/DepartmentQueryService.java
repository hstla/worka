package com.worka.worka.department.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.worka.worka.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentQueryService {

	private final DepartmentRepository departmentRepository;

}