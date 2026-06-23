package com.ghaida.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.ConcurrentModificationException;
import java.util.stream.Collectors;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof DepartmentNotFoundException ||
                exception instanceof EmployeeNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(404, exception.getMessage()))
                    .build();
        }

        if (exception instanceof ConstraintViolationException ex) {
            String message = ex.getConstraintViolations()
                    .stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(400, message))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(500, "Something went wrong"))
                .build();
    }
}