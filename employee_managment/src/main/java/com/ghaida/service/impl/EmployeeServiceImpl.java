package com.ghaida.service.impl;

import com.ghaida.dto.EmployeeRequestDTO;
import com.ghaida.dto.EmployeeResponseDTO;
import com.ghaida.entity.Department;
import com.ghaida.entity.Employee;
import com.ghaida.exception.DepartmentNotFoundException;
import com.ghaida.exception.EmployeeNotFoundException;
import com.ghaida.repository.DepartmentRepository;
import com.ghaida.repository.EmployeeRepository;
import com.ghaida.service.EmployeeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = Logger.getLogger(EmployeeServiceImpl.class);
    @Inject
    DepartmentRepository departmentRepository;

    @Inject
    EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeResponseDTO> getAllEmployees(int page, int size) {
        LOG.info("Fetching all Employees");
        return employeeRepository.findPaginated(page, size)
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        LOG.info("Fetching employee with id " + id);
        Employee employee = employeeRepository.findByIdOptional(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return toResponse(employee);
    }

    @Transactional
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {
        LOG.info("Creating employee with name " + dto.getName());
        Employee employee = toEntity(dto);
        employeeRepository.persist(employee);
        return toResponse(employee);
    }

    @Transactional
    @Override
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO dto, Long id) {
        LOG.info("Updating employee with id " + id);
        Department department = departmentRepository.findByIdOptional(dto.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(dto.getDepartmentId()));
        Employee existingEmployee = employeeRepository.findByIdOptional(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        existingEmployee.setName(dto.getName());
        existingEmployee.setEmail(dto.getEmail());
        existingEmployee.setPhone(dto.getPhone());
        existingEmployee.setPosition(dto.getPosition());
        existingEmployee.setDepartment(department);

        return toResponse(existingEmployee);
    }

    @Transactional
    @Override
    public void deleteEmployee(Long id) {
        LOG.info("Deleting employee with id " + id);
        boolean deleted = employeeRepository.deleteById(id);
        if (!deleted) {
            throw new EmployeeNotFoundException(id);
        }
    }

    private Employee toEntity(EmployeeRequestDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setPosition(dto.getPosition());
        employee.setJoinDate(LocalDate.now());
        Department department = departmentRepository.findByIdOptional(dto.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(dto.getDepartmentId()));
        employee.setDepartment(department);

        return employee;
    }

    private EmployeeResponseDTO toResponse(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getPosition(),
                employee.getDepartment().getDepartmentName(),
                employee.getJoinDate()
        );
    }
}
