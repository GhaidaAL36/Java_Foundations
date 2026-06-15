package com.ghaida.employee.dto;


import com.ghaida.employee.entity.Department;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeResponseDTO {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String position;
    private String departmentName;
    private LocalDate joinDate;

}
