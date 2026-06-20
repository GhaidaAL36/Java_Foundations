package com.ghaida.employee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class DepartmentResponseDTO {

    private Integer departmentId;
    private String departmentName;
    private int employeeCount;

}
