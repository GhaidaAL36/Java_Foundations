package com.ghaida.service;

import com.ghaida.dto.EmployeeRequestDTO;
import com.ghaida.dto.EmployeeResponseDTO;
import com.ghaida.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees(int page, int size);

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    EmployeeResponseDTO updateEmployee(EmployeeRequestDTO dto, Long id);

    void deleteEmployee(Long id);

}
