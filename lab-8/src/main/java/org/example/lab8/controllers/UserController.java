package org.example.lab8.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.lab8.entities.User;
import org.example.lab8.services.UserService;
import org.example.lab8.services.UserServiceImpl;

@Path("/users")
public class UserController {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok(userService.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User object must be provided in the request body.")
                    .build();
        }
        userService.register(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(User user) {
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User object must be provided in the request body.")
                    .build();
        }

        if (userService.validateUser(user)) {
            return Response.ok().entity("Login successful.").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials.").build();
        }
    }

}
