package rs.raf.wpbackend.resources;

import rs.raf.wpbackend.entities.User;
import rs.raf.wpbackend.requests.LoginRequest;
import rs.raf.wpbackend.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.userService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User user) {
        return this.userService.add(user);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("id") String email) {
        return this.userService.find(email);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.userService.delete(id);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest)
    {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User edit(@Valid User user, @PathParam("id") Integer id) {
        return this.userService.edit(user, id);
    }

    @PUT
    @Path("/activate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void activate(@PathParam("id") Integer id) {
        this.userService.activate(id);
    }

    @PUT
    @Path("/deactivate/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deactivate(@PathParam("id") Integer id) {
        this.userService.deactivate(id);
    }
}
