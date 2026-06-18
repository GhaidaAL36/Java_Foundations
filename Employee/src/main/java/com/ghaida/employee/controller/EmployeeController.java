package com.ghaida.employee.controller;


import com.ghaida.employee.dto.EmployeeRequestDTO;
import com.ghaida.employee.dto.EmployeeResponseDTO;
import com.ghaida.employee.service.EmployeeService;
import com.ghaida.employee.wrapper.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> createEmployee(@RequestBody @Valid EmployeeRequestDTO employee) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Employee created successfully", employeeService.createEmployee(employee)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeResponseDTO>>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResponse.success(employeeService.getAllEmployees(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> findById(@PathVariable Integer id) {

        return ResponseEntity.ok(ApiResponse.success(employeeService.getEmployeeById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> updateEmployee(@PathVariable Integer id, @RequestBody @Valid EmployeeRequestDTO employee) {
        return ResponseEntity.ok(ApiResponse.success("Employee updated successfully", employeeService.updateEmployee(id, employee)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
