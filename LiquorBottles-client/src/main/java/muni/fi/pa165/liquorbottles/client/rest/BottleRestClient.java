/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.rest;

import java.util.List;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;

/**
 * Jersey REST client generated for REST resource:BottleRestService
 * [/bottle]<br>
 * USAGE:
 * <pre>
 *        BottleRestClient client = new BottleRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Michal Stora, Masaryk University
 */
public class BottleRestClient {

    private WebTarget webTarget;
    private javax.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/pa165/rest";

    public BottleRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient().register((new Authenticator("admin", "admin")));
        webTarget = client.target(BASE_URI).path("bottle");
    }

    public <T> T getBottleById(Class<T> responseType, String param) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("id/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public List<BottleDTO> getBottlesByBatchId(String param) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("batchId/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleDTO>>() {
        });
    }

    public List<BottleDTO> getBottlesByDate(String param) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("date/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleDTO>>() {
        });
    }

    public List<BottleDTO> getBottlesByToxicity(String param) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("toxicity/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleDTO>>() {
        });
    }

    public <T> T getBottleByStamp(Class<T> responseType, String param) throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("stamp/{0}", new Object[]{param}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public List<BottleDTO> getAllBottles() throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<BottleDTO>>() {
        });
    }

    public void add(BottleDTO requestEntity) throws javax.ws.rs.ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void update(BottleDTO requestEntity) throws javax.ws.rs.ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void remove(Object requestEntity) throws javax.ws.rs.ClientErrorException {
        //webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).delete(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void close() {
        client.close();
    }
}
