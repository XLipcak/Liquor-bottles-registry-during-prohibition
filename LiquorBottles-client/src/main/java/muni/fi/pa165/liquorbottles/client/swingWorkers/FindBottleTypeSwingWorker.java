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
public class FindBottleTypeSwingWorker extends SwingWorker<BottleTypeDTO, Integer> {

    BottleTypeService bottleTypeService;
    BottleTypeTableModel bottleTypeTableModel;
    BottleTypeDTO bottleType;

    public FindBottleTypeSwingWorker(BottleTypeService bottleTypeService, BottleTypeTableModel bottleTypeTableModel, BottleTypeDTO bottleType) {
        this.bottleTypeService = bottleTypeService;
        this.bottleTypeTableModel = bottleTypeTableModel;
        this.bottleType = bottleType;
    }

    @Override
    protected BottleTypeDTO doInBackground() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
