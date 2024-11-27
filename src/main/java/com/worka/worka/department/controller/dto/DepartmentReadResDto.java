package com.worka.worka.department.controller.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.worka.worka.department.domain.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentReadResDto {
    Long id;
    String name;
    List<DepartmentReadResDto> children;

    public DepartmentReadResDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.children = department.getChildren()
            .stream()
            .map(DepartmentReadResDto::new)
            .collect(Collectors.toList());
    }
}