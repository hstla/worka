package com.worka.worka.department.controller.dto;

import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentParentPutReqDto {
    Long departmentId;
    Optional<Long> parentId;

}
