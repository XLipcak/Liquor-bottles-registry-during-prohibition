/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.swingForms;

import java.util.ArrayList;
import java.util.List;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.client.rest.BottleRestClient;
import muni.fi.pa165.liquorbottles.client.rest.BottleTypeRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTableModel;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTypeTableModel;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class Main {

    public static void main(String args[]) {

        List<BottleDTO> allBottles;
        List<BottleTypeDTO> allBottleTypes;
        BottleTableModel bottleTableModel;
        BottleTypeTableModel bottleTypeTableModel;
        BottleRestClient bottleRestClient;
        BottleTypeRestClient bottleTypeRestClient;

        BottleTypeDTO bottleType;

        allBottles = new ArrayList<>();
        allBottleTypes = new ArrayList<>();
        bottleTableModel = new BottleTableModel(allBottles);
        bottleTypeTableModel = new BottleTypeTableModel(allBottleTypes);

        bottleRestClient = new BottleRestClient();
        bottleTypeRestClient = new BottleTypeRestClient();

        allBottleTypes = bottleTypeRestClient.getAllBottleTypes();
        System.out.println(allBottleTypes);
        System.out.println("__________________");
        allBottleTypes = bottleTypeRestClient.getBottleTypeByAlcType("3");
        System.out.println(allBottleTypes);
        System.out.println("__________________");
        bottleType = bottleTypeRestClient.getBottleTypeById(BottleTypeDTO.class, "2");
        System.out.println(bottleType);
        System.out.println("_________________");
        allBottleTypes = bottleTypeRestClient.getBottleTypeByPower("3");
        System.out.println(allBottleTypes);
        System.out.println("__________________");
        allBottleTypes = bottleTypeRestClient.getBottleTypeByVolume("3");
        System.out.println(allBottleTypes);
        System.out.println("__________________");
        allBottleTypes = bottleTypeRestClient.getBottleTypeByProducer("2");
        System.out.println(allBottleTypes);
        System.out.println("_________________");

    }
}
