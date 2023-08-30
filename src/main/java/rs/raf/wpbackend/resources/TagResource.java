package rs.raf.wpbackend.resources;

import rs.raf.wpbackend.entities.Tag;
import rs.raf.wpbackend.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tags")
public class TagResource {
    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.tagService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(@Valid Tag tag) {
        return this.tagService.add(tag);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag find(@PathParam("id") Integer id) {
        return this.tagService.find(id);
    }


    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.tagService.delete(id);
    }
}
