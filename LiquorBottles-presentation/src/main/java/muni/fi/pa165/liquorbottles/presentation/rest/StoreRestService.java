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
import muni.fi.pa165.liquorbottles.api.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.api.services.StoreService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Michal Štora, Masaryk University
 */
@Path("/store")
public class StoreRestService {

    private StoreService storeService;

    private void initBeforeRequest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        storeService = (StoreService) applicationContext.getBean(StoreService.class);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StoreDTO> getAllStores() {
        initBeforeRequest();
        List<StoreDTO> listStores = storeService.findAll();
        return listStores;
    }
}
