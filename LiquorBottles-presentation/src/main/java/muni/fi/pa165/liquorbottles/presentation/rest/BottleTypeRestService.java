/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.presentation.rest;

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
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@Path("/bottleType")
public class BottleTypeRestService {
    
    private BottleTypeService bottleTypeService;

    public BottleTypeService getBottleTypeService() {
        return bottleTypeService;
    }

    public void setBottleTypeService(BottleTypeService bottleTypeService) {
        this.bottleTypeService = bottleTypeService;
    }

    public BottleTypeDTO getBottleType() {
        return bottleType;
    }

    public void setBottleType(BottleTypeDTO bottleType) {
        this.bottleType = bottleType;
    }
    
    private BottleTypeDTO bottleType;
    
    private void initBeforeRequest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        bottleTypeService = (BottleTypeService) applicationContext.getBean(BottleTypeService.class);
    }
    
    
    @GET
    @Path("/id/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleTypeById(@PathParam("param") long id) {
        initBeforeRequest();
        bottleType = bottleTypeService.findById(id);
        if (bottleType != null) {
            return Response.status(Response.Status.OK).entity(bottleType).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBottleTypes() {
        initBeforeRequest();
        List<BottleTypeDTO> bottles = bottleTypeService.findAll();
        if (bottles != null) {
            return Response.status(Response.Status.OK).entity(bottles).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/alcType/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleTypeByAlcType(@PathParam("param") String AlcType) {
        initBeforeRequest();
        List<BottleTypeDTO> bottles = bottleTypeService.findByAlcType(AlcType);
        if (bottles != null) {
            return Response.status(Response.Status.OK).entity(bottles).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/power/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleTypeByPower(@PathParam("param") int power) {
        initBeforeRequest();
        List<BottleTypeDTO> bottles = bottleTypeService.findByPower(power);
        if (bottles != null) {
            return Response.status(Response.Status.OK).entity(bottles).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/volume/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleTypeByVolume(@PathParam("param") int volume) {
        initBeforeRequest();
        List<BottleTypeDTO> bottles = bottleTypeService.findByVolume(volume);
        if (bottles != null) {
            return Response.status(Response.Status.OK).entity(bottles).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/producer/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBottleTypeByAlcType(@PathParam("param") long producerId) {
        initBeforeRequest();
        List<BottleTypeDTO> bottles = bottleTypeService.findByProducer(producerId);
        if (bottles != null) {
            return Response.status(Response.Status.OK).entity(bottles).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(BottleTypeDTO toUpdate) {
        initBeforeRequest();
        bottleTypeService.updateBottleType(toUpdate);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(BottleTypeDTO toAdd) {
        initBeforeRequest();
        bottleTypeService.insertBottleType(toAdd);
    }

    @DELETE
    @Path("/delete/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void remove(@PathParam("param") long Id) {
        initBeforeRequest();
        BottleTypeDTO toDelete = bottleTypeService.findById(Id);
        if (toDelete != null){
            bottleTypeService.deleteBottleType(toDelete);
        }
    }
    
    
}
