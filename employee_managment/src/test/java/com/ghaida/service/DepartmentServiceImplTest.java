package com.ghaida.service;

import com.ghaida.dto.DepartmentResponseDTO;
import com.ghaida.entity.Department;
import com.ghaida.exception.DepartmentNotFoundException;
import com.ghaida.repository.DepartmentRepository;
import com.ghaida.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void shouldReturnAllDepartments() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("HR");
        department.setEmployees(new ArrayList<>());

        when(departmentRepository.findPaginated(0, 10))
                .thenReturn(List.of(department));

        List<DepartmentResponseDTO> result = departmentService.getAllDepartments(0, 10);

        assertEquals(1, result.size());
        assertEquals("HR", result.get(0).getDepartmentName());
    }

    @Test
    void shouldReturnDepartmentById() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("HR");
        department.setEmployees(new ArrayList<>());

        when(departmentRepository.findByIdOptional(1L))
                .thenReturn(Optional.of(department));

        DepartmentResponseDTO result = departmentService.getDepartmentById(1L);

        assertEquals("HR", result.getDepartmentName());
    }

    @Test
    void shouldThrowExceptionWhenDepartmentNotFound() {
        when(departmentRepository.findByIdOptional(1L))
                .thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.getDepartmentById(1L));
    }
}
