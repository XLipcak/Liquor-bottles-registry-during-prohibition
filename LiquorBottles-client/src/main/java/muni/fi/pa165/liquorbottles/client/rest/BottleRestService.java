package muni.fi.pa165.liquorbottles.client.rest;

import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class BottleRestService {

    private RestTemplate restTemplate = new RestTemplate();

    private static final String ID_URL = "http://localhost:8080/pa165/rest/bottle/id/";

    public BottleDTO getBottleById(long id) {
        BottleDTO bottle = restTemplate.getForObject(ID_URL + id, BottleDTO.class);

        return bottle;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
