package com.ghaida.service.impl;

import com.ghaida.dto.DepartmentRequestDTO;
import com.ghaida.dto.DepartmentResponseDTO;
import com.ghaida.entity.Department;
import com.ghaida.exception.DepartmentNotFoundException;
import com.ghaida.repository.DepartmentRepository;
import com.ghaida.service.DepartmentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOG = Logger.getLogger(DepartmentServiceImpl.class);

    @Inject
    DepartmentRepository departmentRepository;


    @Override
    public List<DepartmentResponseDTO> getAllDepartments(int page, int size) {
        LOG.info("Fetching all departments");
        return departmentRepository.findPaginated(page, size)
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {
        LOG.info("Fetching department with id: " + id);
        Department department = departmentRepository.findByIdOptional(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        return toResponse(department);
    }

    @Transactional
    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {
        LOG.info("Creating Department with name: " + dto.getDepartmentName());
        Department department = toEntity(dto);
        departmentRepository.persist(department);
        return toResponse(department);
    }

    @Transactional
    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO dto, Long id) {
        LOG.info("Updating department with id: " + id);
        Department existingDepartment = departmentRepository.findByIdOptional(id).orElseThrow(() -> new DepartmentNotFoundException(id));
        existingDepartment.setDepartmentName(dto.getDepartmentName());

        return toResponse(existingDepartment);
    }

    @Transactional
    @Override
    public void deleteDepartment(Long id) {
        LOG.info("Deleting department with id: " + id);
        boolean deleted = departmentRepository.deleteById(id);
        if (!deleted) {
            throw new DepartmentNotFoundException(id);
        }
    }

    private Department toEntity(DepartmentRequestDTO dto) {
        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        return department;
    }

    private DepartmentResponseDTO toResponse(Department department) {
        return new DepartmentResponseDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getEmployees() == null ? 0 : department.getEmployees().size()
        );

    }
}
