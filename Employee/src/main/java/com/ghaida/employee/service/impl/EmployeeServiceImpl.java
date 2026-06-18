package com.ghaida.employee.service.impl;

import com.ghaida.employee.dto.EmployeeRequestDTO;
import com.ghaida.employee.dto.EmployeeResponseDTO;
import com.ghaida.employee.entity.Department;
import com.ghaida.employee.entity.Employee;
import com.ghaida.employee.exception.DepartmentNotFoundException;
import com.ghaida.employee.exception.EmployeeNotFoundException;
import com.ghaida.employee.repository.DepartmentRepository;
import com.ghaida.employee.repository.EmployeeRepository;
import com.ghaida.employee.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(() -> new DepartmentNotFoundException(dto.getDepartmentId()));
        Employee employee = toEntity(dto);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);

        return toResponse(savedEmployee);
    }

    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).map(this::toResponse);
    }

    public EmployeeResponseDTO getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return toResponse(employee);
    }

    public EmployeeResponseDTO updateEmployee(Integer id, EmployeeRequestDTO dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        existingEmployee.setName(dto.getName());
        existingEmployee.setEmail(dto.getEmail());
        existingEmployee.setPhone(dto.getPhone());
        existingEmployee.setPosition(dto.getPosition());

        employeeRepository.save(existingEmployee);
        return toResponse(existingEmployee);

    }

    public void deleteEmployee(Integer id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
    }

    private Employee toEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setPosition(employeeRequestDTO.getPosition());

        return employee;
    }

    private EmployeeResponseDTO toResponse(Employee employee) {
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setId(employee.getId());
        employeeResponseDTO.setName(employee.getName());
        employeeResponseDTO.setEmail(employee.getEmail());
        employeeResponseDTO.setPhone(employee.getPhone());
        employeeResponseDTO.setPosition(employee.getPosition());
        employeeResponseDTO.setJoinDate(employee.getJoinDate());
        employeeResponseDTO.setDepartmentName(employee.getDepartment().getDepartmentName());
        return employeeResponseDTO;
    }
}
