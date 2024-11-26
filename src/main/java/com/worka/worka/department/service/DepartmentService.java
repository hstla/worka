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
     * parentId가 null 일때 최상위 부서
     * parentId가 정상일 때 하위 부서
     * parentId가 틀릴 때 에러처리
     */
    public Department createDepartment(String name, Long parentId) {
        Optional<Department> parentDepartment = (parentId != null) ? findParentDepartment(parentId) : Optional.empty();
        Department department = Department.create(name, parentDepartment);
        return departmentRepository.save(department);
    }

    public Optional<Department> findParentDepartment(Long parentId) {
        Department department = departmentRepository.findByParentId(parentId)
            .orElseThrow(() -> new RuntimeException("상위 부서를 찾을 수 없습니다. " + parentId));
        return Optional.of(department);
    }

    /**
     * 수정
     * 부서이름과 부모아이디를 수정할 수 있다.
     * 부모아이디 수정 시 depth를 다시 계산한다.
     * 부모아이디 null일 시 최상위 부서라 판단하고 depth를 1로 한다.
     */
    public Department updateNameDepartment(Long departmentId, String updateName) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new RuntimeException("부서를 찾을 수 없습니다. " + departmentId));
        department.updateName(updateName);
        return departmentRepository.save(department);
    }

    public Department updateParentDepartment(Long departmentId, Optional<Long> parentId) {
        Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new RuntimeException("부서를 찾을 수 없습니다. " + departmentId));
        Optional<Department> parentDepartment = parentId.map(id -> departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("상위 부서를 찾을 수 없습니다. " + id)))
            .or(() -> Optional.empty());

        department.updateParentId(parentDepartment);
        return departmentRepository.save(department);
    }
}