package rs.raf.wpbackend.resources;

import rs.raf.wpbackend.entities.Category;
import rs.raf.wpbackend.entities.Post;
import rs.raf.wpbackend.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
public class CategoryResource {
    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.categoryService.all()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(@Valid Category category) {
        return this.categoryService.add(category);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category find(@PathParam("id") Integer id) {
        return this.categoryService.find(id);
    }


    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.categoryService.delete(id);
    }

    @PUT
    @Path("/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category edit(@Valid Category category, @PathParam("id") Integer id) {
        return this.categoryService.edit(category, id);
    }
}

