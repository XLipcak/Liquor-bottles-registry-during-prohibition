/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.rest;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;

/**
 * Jersey REST client generated for REST resource:BottleTypeRestService
 * [/bottleType]<br>
 * USAGE:
 * <pre>
 *        BottleTypeRestClient client = new BottleTypeRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Michal Stora, Masaryk University
 */
public class BottleTypeRestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/pa165/rest";

    public BottleTypeRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient().register((new Authenticator("admin", "admin")));
        webTarget = client.target(BASE_URI).path("bottleType");
    }

    public List<BottleTypeDTO> getAllBottleTypes() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleTypeDTO>>() {
        });
    }

    public <T> T getBottleTypeById(Class<T> responseType, String param) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("id/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public List<BottleTypeDTO> getBottleTypeByAlcType(String param) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("alcType/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleTypeDTO>>() {
        });
    }

    public List<BottleTypeDTO> getBottleTypeByPower(String param) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("power/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleTypeDTO>>() {
        });
    }

    public List<BottleTypeDTO> getBottleTypeByVolume(String param) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("volume/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleTypeDTO>>() {
        });
    }

    public List<BottleTypeDTO> getBottleTypeByProducer(String param) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("producer/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleTypeDTO>>() {
        });
    }

    public void add(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void update(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void remove(Object requestEntity) throws ClientErrorException {
        //webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).delete(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void close() {
        client.close();
    }

}
