package com.ghaida.employee.service;

import com.ghaida.employee.dto.EmployeeRequestDTO;
import com.ghaida.employee.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO getEmployeeById(Integer id);
    EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto);
    void deleteEmployee(Integer id);
}
