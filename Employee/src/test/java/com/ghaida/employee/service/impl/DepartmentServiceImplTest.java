package com.ghaida.employee.service.impl;

import com.ghaida.employee.dto.DepartmentResponseDTO;
import com.ghaida.employee.entity.Department;
import com.ghaida.employee.exception.DepartmentNotFoundException;
import com.ghaida.employee.repository.DepartmentRepository;
import com.ghaida.employee.dto.DepartmentRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {


    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @Test
    void createDepartment() {
        DepartmentRequestDTO dto = new DepartmentRequestDTO();
        dto.setDepartmentName("Engineering");


        Department department = new Department();
        department.setDepartmentName("Engineering");
        department.setId(1);

        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        DepartmentResponseDTO response = departmentServiceImpl.createDepartment(dto);

        assertNotNull(response);
        assertEquals("Engineering", response.getDepartmentName());
    }

    @Test
    void getAllDepartments() {
        Department department = new Department();
        department.setDepartmentName("Engineering");
        department.setId(1);

        when(departmentRepository.findAll()).thenReturn(List.of(department));

        List<DepartmentResponseDTO> response = departmentServiceImpl.getAllDepartments();

        assertNotNull(response);
        assertEquals("Engineering", response.get(0).getDepartmentName());

    }

    @Test
    void getDepartmentById_success() {
        Department department = new Department();
        department.setDepartmentName("Engineering");
        department.setId(1);

        when((departmentRepository.findById(1))).thenReturn(Optional.of(department));

        DepartmentResponseDTO response = departmentServiceImpl.getDepartmentById(1);
        assertNotNull(response);
        assertEquals("Engineering", response.getDepartmentName()); // ✅ add this
        assertEquals(1, response.getDepartmentId());
    }

    @Test
    void getDepartmentById_notFound() {
        when((departmentRepository.findById(1))).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> departmentServiceImpl.getDepartmentById(1));

    }

    @Test
    void deleteDepartmentById_success() {
        Department department = new Department();
        department.setDepartmentName("Engineering");
        department.setId(1);

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        departmentServiceImpl.deleteDepartmentById(1);

        verify(departmentRepository).deleteById(1);
    }

    @Test
    void deleteDepartmentById_notFound() {
        when((departmentRepository.findById(1))).thenReturn(Optional.empty());
        assertThrows(DepartmentNotFoundException.class, () -> departmentServiceImpl.deleteDepartmentById(1));
    }
}