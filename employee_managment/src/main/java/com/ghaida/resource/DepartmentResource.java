package com.ghaida.resource;

import com.ghaida.dto.DepartmentRequestDTO;
import com.ghaida.dto.DepartmentResponseDTO;
import com.ghaida.service.DepartmentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/departments")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentResource {

    @Inject
    DepartmentService departmentService;

    @GET
    public List<DepartmentResponseDTO> listAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {
        return departmentService.getAllDepartments(page, size);
    }

    @GET
    @Path("/{id}")
    public DepartmentResponseDTO getById(@PathParam("id") long id) {
        return departmentService.getDepartmentById(id);
    }


    @POST
    @ResponseStatus(201)
    public DepartmentResponseDTO create(@Valid DepartmentRequestDTO request) {
        return departmentService.createDepartment(request);
    }

    @PUT
    @Path("/{id}")
    public DepartmentResponseDTO update(@PathParam("id") long id, @Valid DepartmentRequestDTO request) {
        return departmentService.updateDepartment(request, id);
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(204)
    public void deleteById(@PathParam("id") long id) {
        departmentService.deleteDepartment(id);
    }
}
