package com.ghaida.service;

import com.ghaida.dto.DepartmentRequestDTO;
import com.ghaida.dto.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponseDTO> getAllDepartments(int page, int size);
    DepartmentResponseDTO getDepartmentById(Long id);
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto);
    DepartmentResponseDTO updateDepartment(DepartmentRequestDTO dto, Long id);
    void deleteDepartment(Long id);

}
