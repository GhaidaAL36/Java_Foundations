package com.ghaida.repository;

import com.ghaida.entity.Employee;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public List<Employee> findPaginated(int page, int size) {
        return findAll().page(page, size).list();
    }
}
