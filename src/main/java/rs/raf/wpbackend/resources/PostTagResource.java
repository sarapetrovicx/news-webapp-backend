package rs.raf.wpbackend.resources;

import rs.raf.wpbackend.entities.PostTag;
import rs.raf.wpbackend.entities.Tag;
import rs.raf.wpbackend.services.PostTagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/poststags")
public class PostTagResource {
    @Inject
    private PostTagService postTagService;


    @GET
    @Path("/bytag/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response byTag(@PathParam("id") Integer id) {
        return Response.ok(this.postTagService.allPostsForTag(id)).build();
    }

    @GET
    @Path("/bypost/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response byPost(@PathParam("id") Integer id) {
        return Response.ok(this.postTagService.allTagsForPost(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public PostTag create(@Valid PostTag postTag) {
        return this.postTagService.add(postTag);
    }

}
