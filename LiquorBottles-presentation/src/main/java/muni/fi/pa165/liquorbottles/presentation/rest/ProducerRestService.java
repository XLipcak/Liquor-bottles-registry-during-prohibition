/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.presentation.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;
import muni.fi.pa165.liquorbottles.api.services.ProducerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@Path("/producer")
public class ProducerRestService {

    private ProducerService producerService;

    private void initBeforeRequest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        producerService = (ProducerService) applicationContext.getBean(ProducerService.class);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducers() {
        initBeforeRequest();
        List<ProducerDTO> listProducer = producerService.findAll();
        if (listProducer.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(listProducer).build();
        }
    }
}
