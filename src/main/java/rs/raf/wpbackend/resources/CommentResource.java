package rs.raf.wpbackend.resources;

import rs.raf.wpbackend.entities.Comment;
import rs.raf.wpbackend.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {
    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.commentService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) {
        return this.commentService.add(comment);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment find(@PathParam("id") Integer id) {
        return this.commentService.find(id);
    }

    @GET
    @Path("/bypost/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPost(@PathParam("id") Integer id) {
        return Response.ok(this.commentService.findByPost(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.commentService.delete(id);
    }
}
