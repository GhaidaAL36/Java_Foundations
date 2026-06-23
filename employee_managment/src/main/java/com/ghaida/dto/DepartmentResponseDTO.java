package com.ghaida.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponseDTO {

    private Long departmentId;
    private String departmentName;
    private int employeeCount;
}
