/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.presentation.rest;

import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@Path("/bottle")
public class BottleRestService {
    
    private BottleService bottleService;
    
    private BottleDTO bottle;
    
    private void initBeforeRequest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        bottleService = (BottleService) applicationContext.getBean(BottleService.class);
    }

    /**
     *
     *
     * void insertBottle(BottleDTO bottle);
     *
     * void updateBottle(BottleDTO bottle);
     *
     * void deleteBottle(BottleDTO bottle);
     *
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBottles() {
        List<BottleDTO> list = bottleService.findAll();
        if (list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(list).build();
        }
    }
    
    @GET
    @Path("/id/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleById(@PathParam("param") long id) {
        initBeforeRequest();
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
        initBeforeRequest();
        bottle = bottleService.findByStamp(stamp);
        if (bottle != null) {
            return Response.status(Response.Status.OK).entity(bottle).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/batchId/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottlesByBatchId(@PathParam("param") long btach) {
        List<BottleDTO> list = bottleService.findByBatchId(btach);
        if (list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(list).build();
        }
    }
    
    @GET
    @Path("/date/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottlesByDate(@PathParam("param") Date date) {
        List<BottleDTO> list = bottleService.findByDate(date);
        if (list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(list).build();
        }
    }
    
    @GET
    @Path("/toxicity/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottlesByToxicity(@PathParam("param") ToxicityDTO toxic) {
        List<BottleDTO> list = bottleService.findByToxicity(toxic);
        if (list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(list).build();
        }
    }
    
    @GET
    @Path("/sm")
    @Produces(MediaType.APPLICATION_JSON)
    public Response nieco() {
        initBeforeRequest();
        ProducerDTO producer = new ProducerDTO();
        producer.setId(1);
        producer.setName("Jeldo");
        producer.setUsername("nn");
        producer.setAddress("bojnice");
        producer.setPassword("123");
        return Response.status(Response.Status.OK).entity(producer).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(BottleDTO toUpdate) {
        bottleService.updateBottle(toUpdate);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(BottleDTO toAdd) {
        bottleService.insertBottle(toAdd);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void remove(BottleDTO toDelete) {
        bottleService.deleteBottle(toDelete);
    }
    
    public BottleService getBottleService() {
        return bottleService;
    }
    
    public void setBottleService(BottleService bottleService) {
        this.bottleService = bottleService;
    }
    
    public BottleDTO getBottle() {
        return bottle;
    }
    
    public void setBottle(BottleDTO bottle) {
        this.bottle = bottle;
    }
    
}
