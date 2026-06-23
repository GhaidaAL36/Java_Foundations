package com.ghaida.repository;

import com.ghaida.entity.Department;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<Department> {
    public List<Department> findPaginated(int page, int size) {
        return findAll().page(page, size).list();
    }
}
