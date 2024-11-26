package com.worka.worka.department.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

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
		given(departmentRepository.save(any(Department.class)))
			.willReturn(expectedDepartment);

		// when
		Department department = departmentService.createDepartment("testName", null);

		// then
		assertThat(department.getParentDepartment()).isEqualTo(expectedDepartment.getParentDepartment());
		assertThat(department.getDepth()).isEqualTo(1);
	}

	@Test
	@DisplayName("부모아이디가 틀려 부서를 생성하지 못한다.")
	public void createExceptionDepartment () throws Exception {
		//given
		Long invalidParentId = 1L;
		given(departmentRepository.findByParentId(invalidParentId))
			.willReturn(Optional.empty());

		// when then
		assertThatThrownBy(() -> departmentService.createDepartment("testName", invalidParentId))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("상위 부서를 찾을 수 없습니다. " + invalidParentId);
	}

	@Test
	@DisplayName("부서의 이름을 수정한다.")
	public void updateNameDepartment () throws Exception {
		//given
		Long parentId = 1L;
		Department testDepartment = Department.create("test1", Optional.empty());
		given(departmentRepository.findById(parentId))
			.willReturn(Optional.of(testDepartment));

		// when
		departmentService.updateNameDepartment(parentId,"test2");

		// then
		assertThat(testDepartment.getName()).isEqualTo("test2");
	}

	@Test
	@DisplayName("부서를 찾을 수 없어 부서 이름수정에 실패한다.")
	public void updateNameDepartmentException () throws Exception {
		//given
		Long departmentId = 1L;
		given(departmentRepository.findById(departmentId))
			.willReturn(Optional.empty());

		// when then
		assertThatThrownBy(() -> departmentService.updateNameDepartment(departmentId,"test2"))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("부서를 찾을 수 없습니다. " + departmentId);
	}

	@Test
	@DisplayName("상위 부서를 수정에 성공한다.")
	public void updateParentIdDepartment () throws Exception {
		//given
		Long departmentId = 1L;
		Long parentDepartmentId = 2L;
		Department department = Department.create("test1", Optional.empty());
		given(departmentRepository.findById(departmentId)).willReturn(Optional.of(department));
		given(departmentRepository.findById(parentDepartmentId)).willReturn(Optional.empty());

		// when then
		departmentService.updateParentDepartment(departmentId, Optional.empty());

		assertThatThrownBy(() -> departmentService.updateNameDepartment(departmentId,"test2"))
			.isInstanceOf(RuntimeException.class)
			.hasMessage("부서를 찾을 수 없습니다. " + departmentId);
	}
}