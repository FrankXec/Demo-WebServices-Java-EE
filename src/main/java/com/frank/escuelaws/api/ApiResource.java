/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.frank.escuelaws.api;

import com.frank.escuelaws.controller.AlumnoController;
import com.frank.escuelaws.models.Alumno;
import com.frank.escuelaws.utils.Properties;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Frank
 */
@Path("/alumno")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiResource implements ContainerResponseFilter{

    @Context
    private UriInfo context;

    private AlumnoController controller = new AlumnoController();

    public ApiResource() {

    }

    @GET
    public List<Alumno> getAll(/*@HeaderParam("token") String token*/) {
        return controller.getAll();
        /*if(token.compareToIgnoreCase(Properties.tokenAuth)==0){
            return controller.getAll();
        }else{
            return null;
        }*/
    }

    @GET
    @Path("{id}")
    public Alumno getOne(@PathParam(value = "id") Long id) {
        return controller.getOneById(id);
    }

    @POST
    public boolean create(Alumno save) {
        return controller.create(save);
    }

    /*@PUT
    public boolean update(Alumno update) {
        return controller.update(update);
    }*/
    
    @PUT
    @Path("{id}")
    public boolean update(@PathParam(value = "id") Long id, Alumno update) {
        return controller.update(id, update);
    }

    @DELETE
    @Path("{id}")
    public boolean delete(@PathParam(value = "id") Long id) {
        return controller.delete(id);
    }

    @Override
   public void filter(final ContainerRequestContext requestContext,
                      final ContainerResponseContext cres) throws IOException {
      cres.getHeaders().add("Access-Control-Allow-Origin", "*");
      cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
      cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
      cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
      cres.getHeaders().add("Access-Control-Max-Age", "1209600");
   }
}
