package com.ghaida.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DepartmentRequestDTO {

    @NotBlank
    private String departmentName;
}
