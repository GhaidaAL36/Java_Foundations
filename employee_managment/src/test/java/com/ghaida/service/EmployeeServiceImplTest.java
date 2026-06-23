package com.ghaida.service;

import com.ghaida.dto.EmployeeRequestDTO;
import com.ghaida.dto.EmployeeResponseDTO;
import com.ghaida.entity.Department;
import com.ghaida.entity.Employee;
import com.ghaida.exception.DepartmentNotFoundException;
import com.ghaida.repository.DepartmentRepository;
import com.ghaida.repository.EmployeeRepository;
import com.ghaida.service.impl.EmployeeServiceImpl;
import com.ghaida.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void shouldReturnAllEmployees() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("HR");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ghaida");
        employee.setEmail("ghaida@gmail.com");
        employee.setPhone("0501234567");
        employee.setPosition("Developer");
        employee.setJoinDate(LocalDate.now());
        employee.setDepartment(department);

        when(employeeRepository.findPaginated(0, 10))
                .thenReturn(List.of(employee));

        List<EmployeeResponseDTO> result = employeeService.getAllEmployees(0, 10);

        assertEquals(1, result.size());
        assertEquals("Ghaida", result.get(0).getName());
        assertEquals("HR", result.get(0).getDepartmentName());
    }

    @Test
    void shouldReturnEmployeeById() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("HR");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Ghaida");
        employee.setEmail("ghaida@gmail.com");
        employee.setPhone("0501234567");
        employee.setPosition("Developer");
        employee.setJoinDate(LocalDate.now());
        employee.setDepartment(department);

        when(employeeRepository.findByIdOptional(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponseDTO result = employeeService.getEmployeeById(1L);

        assertEquals("Ghaida", result.getName());
        assertEquals("HR", result.getDepartmentName());
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        when(employeeRepository.findByIdOptional(1L))
                .thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class,
                () -> employeeService.getEmployeeById(1L));
    }

    @Test
    void shouldCreateEmployee() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("HR");

        EmployeeRequestDTO dto = new EmployeeRequestDTO();
        dto.setName("Ghaida");
        dto.setEmail("ghaida@gmail.com");
        dto.setPhone("0501234567");
        dto.setPosition("Developer");
        dto.setDepartmentId(1L);

        when(departmentRepository.findByIdOptional(1L))
                .thenReturn(Optional.of(department));

        EmployeeResponseDTO result = employeeService.createEmployee(dto);

        assertEquals("Ghaida", result.getName());
        assertEquals("HR", result.getDepartmentName());
    }

    @Test
    void shouldThrowExceptionWhenDepartmentNotFoundOnCreate() {
        EmployeeRequestDTO dto = new EmployeeRequestDTO();
        dto.setName("Ghaida");
        dto.setEmail("ghaida@gmail.com");
        dto.setPhone("0501234567");
        dto.setPosition("Developer");
        dto.setDepartmentId(99L);

        when(departmentRepository.findByIdOptional(99L))
                .thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class,
                () -> employeeService.createEmployee(dto));
    }
}


