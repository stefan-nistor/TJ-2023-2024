package org.example.lab8.controllers;


import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.lab8.entities.Preference;
import org.example.lab8.services.PreferenceService;

@Path("/preferences")
public class PreferenceController {

    @Inject
    private PreferenceService preferenceService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(preferenceService.getAllPreferences()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPreference(Preference preference) {
        preferenceService.createPreference(preference);
        return Response.status(Response.Status.CREATED).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePreference(Preference preference) {
        preferenceService.updatePreference(preference);
        return Response.ok().build();
    }

    @DELETE
    public Response deletePreference(Long id) {
        preferenceService.deletePreference(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
