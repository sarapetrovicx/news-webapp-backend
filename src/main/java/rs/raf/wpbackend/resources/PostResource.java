package rs.raf.wpbackend.resources;

import rs.raf.wpbackend.entities.Post;
import rs.raf.wpbackend.services.PostService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {
    @Inject
    private PostService postService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.postService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Post create(@Valid Post post) {
        return this.postService.add(post);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post find(@PathParam("id") Integer id) {
        return this.postService.find(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.postService.delete(id);
    }

    @GET
    @Path("/bycategory/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPost(@PathParam("id") Integer id) {
        return Response.ok(this.postService.byCategory(id)).build();
    }

    @PUT
    @Path("/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post edit(@Valid Post post, @PathParam("id") Integer id) {
        return this.postService.edit(post, id);
    }

    @PUT
    @Path("/visit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post visit(@Valid Post post, @PathParam("id") Integer id) {
        return this.postService.visit(id);
    }
}
