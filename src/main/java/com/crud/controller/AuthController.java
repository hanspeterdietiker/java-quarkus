package com.crud.controller;

import com.crud.exception.AuthException.CredentialsException;
import com.crud.model.UserModel;
import com.crud.services.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/v1/auth")
public class AuthController {
    @Inject
    AuthService authService;


    @POST
    @Path("/sign")
    @Transactional
    @PermitAll
    public Response signUp(UserModel userModel) {
        return Response.ok(authService.signUp(userModel)).build();
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response logIn(UserModel userModel) {
        try {
            String token = authService.login(userModel.username, userModel.password);
            return Response.ok(token).build();

        } catch (CredentialsException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
