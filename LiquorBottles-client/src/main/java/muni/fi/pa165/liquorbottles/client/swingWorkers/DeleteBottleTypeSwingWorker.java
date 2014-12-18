/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.swingWorkers;

import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleTypeService;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTypeTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class DeleteBottleTypeSwingWorker extends SwingWorker<BottleTypeDTO, Integer> {

    BottleTypeService bottleTypeService;
    BottleTypeTableModel bottleTypeTableModel;
    BottleTypeDTO bottleType;
    Long bottleTypeID;

    public DeleteBottleTypeSwingWorker(BottleTypeService bottleTypeService, BottleTypeTableModel bottleTypeTableModel, Long bottleTypeID) {
        this.bottleTypeService = bottleTypeService;
        this.bottleTypeTableModel = bottleTypeTableModel;
        this.bottleTypeID = bottleTypeID;
    }

    @Override
    protected BottleTypeDTO doInBackground() throws Exception {
        bottleTypeService.deleteBottleType(bottleType);
        return bottleType;
    }

    @Override
    protected void done() {
        bottleTypeTableModel.deleteBottleType(bottleType);
    }

}
