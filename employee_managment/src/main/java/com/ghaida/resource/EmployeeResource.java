package com.ghaida.resource;


import com.ghaida.dto.EmployeeRequestDTO;
import com.ghaida.dto.EmployeeResponseDTO;
import com.ghaida.service.DepartmentService;
import com.ghaida.service.EmployeeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/employees")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeService employeeService;

    @GET
    public List<EmployeeResponseDTO> listAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        return employeeService.getAllEmployees(page, size);
    }

    @GET
    @Path("/{id}")
    public EmployeeResponseDTO getById(@PathParam("id") long id) {
        return employeeService.getEmployeeById(id);
    }

    @POST
    @ResponseStatus(201)
    public EmployeeResponseDTO createEmployee(@Valid EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.createEmployee(employeeRequestDTO);
    }

    @PUT
    @Path("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathParam("id") long id, @Valid EmployeeRequestDTO employeeRequestDTO) {
        return employeeService.updateEmployee(employeeRequestDTO, id);
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(204)
    public void deleteEmployee(@PathParam("id") long id) {
        employeeService.deleteEmployee(id);
    }
}
