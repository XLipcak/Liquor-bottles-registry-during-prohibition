/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.swingForms;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericType;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.client.rest.BottleRestClient;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class Main {

    public static void main(String args[]) {
        BottleRestClient client = new BottleRestClient();

        List<BottleDTO> bottles = client.getAllBottles();
       // System.out.println(bottles);
    }
}
