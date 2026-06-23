package com.ghaida.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String position;
    private String departmentName;
    private LocalDate joinDate;

}
