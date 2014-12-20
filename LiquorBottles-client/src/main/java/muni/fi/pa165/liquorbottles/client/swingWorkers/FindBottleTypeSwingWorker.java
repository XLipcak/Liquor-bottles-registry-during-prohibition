/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.swingWorkers;

import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.client.rest.BottleTypeRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTypeTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class FindBottleTypeSwingWorker extends SwingWorker<BottleTypeDTO, Integer> {

    BottleTypeRestClient bottleTypeRestClient;
    BottleTypeTableModel bottleTypeTableModel;
    BottleTypeDTO bottleType;
    Long bottleTypeId;

    public FindBottleTypeSwingWorker(BottleTypeRestClient bottleTypeRestClient, BottleTypeTableModel bottleTypeTableModel, BottleTypeDTO bottleType, long bottleTypeId) {
        this.bottleTypeRestClient = bottleTypeRestClient;
        this.bottleTypeTableModel = bottleTypeTableModel;
        this.bottleType = bottleType;
        this.bottleTypeId = bottleTypeId;
    }

    @Override
    protected BottleTypeDTO doInBackground() throws Exception {
        bottleType = bottleTypeRestClient.getBottleTypeById(BottleTypeDTO.class, bottleTypeId.toString());
        bottleTypeRestClient.close();
        return bottleType;
    }

}
