package com.ghaida.employee.service.impl;

import com.ghaida.employee.dto.DepartmentRequestDTO;
import com.ghaida.employee.dto.DepartmentResponseDTO;
import com.ghaida.employee.entity.Department;
import com.ghaida.employee.exception.DepartmentNotFoundException;
import com.ghaida.employee.repository.DepartmentRepository;
import com.ghaida.employee.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
        Department department = toEntity(dto);
        departmentRepository.save(department);

        return toResponse(department);
    }

    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentResponseDTO> departmentResponseDTOS = departments.stream().map(this::toResponse).toList();

        return departmentResponseDTOS;
    }

    public DepartmentResponseDTO getDepartmentById(Integer id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        return toResponse(department);
    }

    public void deleteDepartmentById(Integer id) {
        departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentRepository.deleteById(id);
    }

    private Department toEntity(DepartmentRequestDTO departmentRequestDTO) {
        Department department = new Department();
        department.setDepartmentName(departmentRequestDTO.getDepartmentName());

        return department;
    }

    private DepartmentResponseDTO toResponse(Department department) {
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setDepartmentId(department.getId());
        departmentResponseDTO.setDepartmentName(department.getDepartmentName());
        departmentResponseDTO.setEmployeeCount(department.getEmployees() == null ? 0 : department.getEmployees().size());
        return departmentResponseDTO;
    }

}
