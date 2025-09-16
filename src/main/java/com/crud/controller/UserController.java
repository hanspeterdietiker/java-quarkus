package com.crud.controller;


import com.crud.model.UserModel;
import com.crud.services.UserService;
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
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") UUID userId) {
        return Response.ok(userService.getUserById(userId)).build();
    }


    @POST
    @Transactional
    public Response createUser(UserModel userModel) {
        return Response.ok(userService.createUser(userModel)).build();

    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") UUID userId, UserModel userModel) {
        return Response.ok(userService.updateUser(userId, userModel)).build();

    }
}
