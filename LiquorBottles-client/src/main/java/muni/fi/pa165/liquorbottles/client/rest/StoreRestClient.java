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
import muni.fi.pa165.liquorbottles.api.dto.StoreDTO;

/**
 * Jersey REST client generated for REST resource:StoreRestService [/store]<br>
 * USAGE:
 * <pre>
 *        StoreRestClient client = new StoreRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Michal Štora, Masaryk University
 */
public class StoreRestClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/pa165/rest";

    public StoreRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("store");
    }

    public List<StoreDTO> getAllStores(String param) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("all");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(new GenericType<List<StoreDTO>>(){});
    }

    public void close() {
        client.close();
    }
    
}
