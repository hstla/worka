package com.worka.worka.department.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.worka.worka.department.domain.Department;
import com.worka.worka.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    /**
     * 부서 생성
     * 부서이름과 부모아이디를 받는다.
     * depth는 부모아이디를 보고 결정한다.
     * 만약 부모아이다가 null로 입력되면 최상위 부서라고 판단하고 depth를 1로 설정한다.
     */
    public void createDepartment(String name, Long parentId) {
        Optional<Department> parentDepartment = (parentId != null) ? departmentRepository.findByParentId(parentId) : Optional.empty();
        Department department = Department.create(name, parentDepartment);
        departmentRepository.save(department);
    }
}
