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
import muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Michal Ĺ tora, Masaryk University
 */
@Path("/bottle")
public class BottleRestService {
    
    private BottleService bottleService;
    
    private BottleDTO bottle;
    
    private void initBeforeRequest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        bottleService = (BottleService) applicationContext.getBean(BottleService.class);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBottles() {
        initBeforeRequest();
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
        initBeforeRequest();
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
        initBeforeRequest();
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
        initBeforeRequest();
        List<BottleDTO> list = bottleService.findByToxicity(toxic);
        if (list.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(list).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(BottleDTO toUpdate) {
        initBeforeRequest();
        bottleService.updateBottle(toUpdate);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(BottleDTO toAdd) {
        initBeforeRequest();
        bottleService.insertBottle(toAdd);
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void remove(BottleDTO toDelete) {
        initBeforeRequest();
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
