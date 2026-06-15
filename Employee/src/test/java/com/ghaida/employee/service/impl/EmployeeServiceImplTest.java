package com.ghaida.employee.service.impl;

import com.ghaida.employee.dto.EmployeeRequestDTO;
import com.ghaida.employee.dto.EmployeeResponseDTO;
import com.ghaida.employee.entity.Department;
import com.ghaida.employee.entity.Employee;
import com.ghaida.employee.exception.EmployeeNotFoundException;
import com.ghaida.employee.repository.DepartmentRepository;
import com.ghaida.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;


    @Test
    void createEmployee() {
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setName("Ghaida");
        employeeRequestDTO.setEmail("ghaida@gmail.com");
        employeeRequestDTO.setPhone("123456789");
        employeeRequestDTO.setPosition("software engineering");
        employeeRequestDTO.setDepartmentId(1);

        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("Engineering");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Ghaida");
        employee.setEmail("ghaida@gmail.com");
        employee.setPhone("123456789");
        employee.setPosition("software engineering");
        employee.setDepartment(department);

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDTO response = employeeServiceImpl.createEmployee(employeeRequestDTO);

        assertNotNull(response);
        assertEquals("Ghaida", response.getName());
        assertEquals("Engineering", response.getDepartmentName());
    }

    @Test
    void getAllEmployees() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Ghaida");
        employee.setEmail("ghaida@test.com");
        employee.setPhone("123456789");
        employee.setPosition("software engineering");

        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("Engineering");
        employee.setDepartment(department);

        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<EmployeeResponseDTO> response = employeeServiceImpl.getAllEmployees();

        assertEquals(1, response.size());
        assertEquals("Ghaida", response.get(0).getName());
    }

    @Test
    void getEmployeeById_success() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Ghaida");
        employee.setEmail("ghaida@test.com");
        employee.setPhone("123456789");
        employee.setPosition("software engineering");
        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("Engineering");
        employee.setDepartment(department);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));


        EmployeeResponseDTO response = employeeServiceImpl.getEmployeeById(1);

        assertEquals("Ghaida", response.getName());

    }

    @Test
    void getEmployeeById_notFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.getEmployeeById(1));
    }

    @Test
    void updateEmployee() {
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setName("Ghaida Alrahimi");
        employeeRequestDTO.setEmail("ghaida@test.com");
        employeeRequestDTO.setPhone("123456789");
        employeeRequestDTO.setPosition("software engineering");
        employeeRequestDTO.setDepartmentId(1);

        Department department = new Department();
        department.setId(1);
        department.setDepartmentName("Engineering");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Ghaida");
        employee.setEmail("ghaida@test.com");
        employee.setPhone("123456789");
        employee.setPosition("software engineering");
        employee.setDepartment(department);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDTO response = employeeServiceImpl.updateEmployee(1, employeeRequestDTO);

        assertEquals("Ghaida Alrahimi", response.getName());
        assertEquals("ghaida@test.com", response.getEmail());
    }

    @Test
    void deleteEmployee_success() {
        Employee employee = new Employee();
        employee.setId(1);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        employeeServiceImpl.deleteEmployee(1);

        verify(employeeRepository).deleteById(1);
    }

    @Test
    void deleteEmployee_notFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(1));
    }
}