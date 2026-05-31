package com.ghaida.springboot.repository;

import com.ghaida.springboot.model.SoftwareEngineer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, UUID> {

    SoftwareEngineer findByName(String name);
}
