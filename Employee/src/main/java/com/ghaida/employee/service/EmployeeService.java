package com.ghaida.employee.service;

import com.ghaida.employee.dto.EmployeeRequestDTO;
import com.ghaida.employee.dto.EmployeeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);
    Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable);
    EmployeeResponseDTO getEmployeeById(Integer id);
    EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto);
    void deleteEmployee(Integer id);
}
