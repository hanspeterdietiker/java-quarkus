package com.crud.controller;


import com.crud.dto.user.PutUserRequest;
import com.crud.model.UserModel;
import com.crud.services.UserService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Authenticated
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID userId) {
        return Response.ok(userService.getUserById(userId)).build();
    }


    @PUT
    @Path("/{id}")
    @Transactional
    @Authenticated
    public Response updateUser(@PathParam("id") UUID userId, PutUserRequest userDto) {
        return Response.ok(userService.updateUser(userId, userDto)).build();

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") UUID userId) {
        return Response.ok(userService.deleteUserById(userId)).build();
    }
}
