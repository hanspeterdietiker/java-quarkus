package com.crud.exception.DataException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(EntityNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(),"Entity with this Id Not Found")
                .build();
    }

}

