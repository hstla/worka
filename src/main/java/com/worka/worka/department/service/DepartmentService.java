package com.worka.worka.department.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
     *  depth는 부모아이디를 보고 결정한다.
     *  만약 부모아이다가 없으면 최상위 부서라고 판단하고 0으로 설정
     */

}
