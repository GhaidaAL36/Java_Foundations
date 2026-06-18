package com.ghaida.employee.service;

import com.ghaida.employee.dto.DepartmentRequestDTO;
import com.ghaida.employee.dto.DepartmentResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DepartmentService {
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto);
    Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable);
    DepartmentResponseDTO getDepartmentById(Integer id);
    void deleteDepartmentById(Integer id);
}
