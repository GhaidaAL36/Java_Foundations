package com.ghaida.employee.service;

import com.ghaida.employee.dto.DepartmentRequestDTO;
import com.ghaida.employee.dto.DepartmentResponseDTO;

import java.util.List;


public interface DepartmentService {
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto);
    List<DepartmentResponseDTO> getAllDepartments();
    DepartmentResponseDTO getDepartmentById(Integer id);
    void deleteDepartmentById(Integer id);
}
