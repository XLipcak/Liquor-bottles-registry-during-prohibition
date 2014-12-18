/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.presentation.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@Path("/bottle")
public class BottleRestService {

    @SpringBean
    private BottleService bottleService;

    private BottleDTO bottle;

    @GET
    @Path("/id/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleById(@PathParam("param") long id) {
        bottle = bottleService.findById(id);
        if (bottle != null) {
            return Response.status(Response.Status.OK).entity(bottle).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/stamp/(param)")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleByStamp(@PathParam("param") long stamp) {
        bottle = bottleService.findByStamp(stamp);
        if (bottle != null) {
            return Response.status(Response.Status.OK).entity(bottle).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("/sm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response nieco() {
        ProducerDTO producer = new ProducerDTO();
        producer.setId(1);
        producer.setName("Jeldo");
        producer.setUsername("nn");
        producer.setAddress("bojnice");
        producer.setPassword("123");
        return Response.status(Response.Status.OK).entity(producer).build();
    }
}
