package com.ghaida.springboot.controller;

import com.ghaida.springboot.model.SoftwareEngineer;
import com.ghaida.springboot.service.SoftwareEngineerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService service;

    public SoftwareEngineerController(SoftwareEngineerService service) {
        this.service = service;
    }

    @GetMapping
    public List<SoftwareEngineer> getAll() {
        return service.getSoftwareEngineers();
    }

    @GetMapping("{name}")
    public SoftwareEngineer getByName(@PathVariable String name) {
        return service.getSoftwareEngineerName(name);
    }

    @PostMapping
    public void addNewSoftwareEngineer(@RequestBody SoftwareEngineer softwareEngineer) {
        service.insertSoftwareEngineer(softwareEngineer);
    }



}
