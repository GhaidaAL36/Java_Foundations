package com.ghaida.employee.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Integer id) {
        super("Department not found with id " + id);
    }
}
