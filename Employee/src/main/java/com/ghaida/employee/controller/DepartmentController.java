package com.ghaida.employee.controller;

import com.ghaida.employee.dto.DepartmentRequestDTO;
import com.ghaida.employee.dto.DepartmentResponseDTO;
import com.ghaida.employee.repository.DepartmentRepository;
import com.ghaida.employee.service.DepartmentService;
import com.ghaida.employee.wrapper.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> createDepartment(@RequestBody @Valid DepartmentRequestDTO department) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Department created successfully", departmentService.createDepartment(department)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponseDTO>>> getAllDepartments() {
        return ResponseEntity.ok(ApiResponse.success(departmentService.getAllDepartments()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponseDTO>> getDepartmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(departmentService.getDepartmentById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable Integer id) {
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.noContent().build();
    }
}
