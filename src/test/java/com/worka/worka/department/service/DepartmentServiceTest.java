package com.worka.worka.department.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.worka.worka.department.domain.Department;
import com.worka.worka.department.repository.DepartmentRepository;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

	@InjectMocks
	DepartmentService departmentService;

	@Mock
	DepartmentRepository departmentRepository;

	@Test
	@DisplayName("최상위 부서 생성에 성공한다.")
	public void createDepartment () {
	    //given
		Department expectedDepartment = new Department("testName", 1, null);
		doReturn(expectedDepartment).when(departmentRepository)
			.save(any(Department.class));

		// when
		Department department = departmentService.createDepartment("testName", null);

		// then
		assertThat(department.getParentId()).isEqualTo(expectedDepartment.getParentId());
		assertThat(department.getDepth()).isEqualTo(1);
	}

	@Test
	@DisplayName("부모아이디가 틀려 부서를 생성하지 못한다.")
	public void createExceptionDepartment () throws Exception {
		//given
		Long invalidParentId = 1L;
		doReturn(Optional.empty()).when(departmentRepository).findByParentId(invalidParentId);

		// when then
		assertThatThrownBy(() -> departmentService.createDepartment("testName", invalidParentId))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("부모 부서를 찾을 수 없습니다.");
	}
}