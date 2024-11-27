package com.worka.worka.department.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentNamePutReqDto {
    @NotNull
    String Name;
    @NotNull
    Long departmentId;
}
