/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.swingWorkers;

import java.util.List;
import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleTypeService;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTypeTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class FindAllBottleTypesSwingWorker extends SwingWorker<Integer, Integer> {

    BottleTypeService bottleTypeService;
    BottleTypeTableModel bottleTypeTableModel;
    List<BottleTypeDTO> bottleTypes;

    public FindAllBottleTypesSwingWorker(BottleTypeService bottleTypeService, BottleTypeTableModel bottleTypeTableModel) {
        this.bottleTypeService = bottleTypeService;
        this.bottleTypeTableModel = bottleTypeTableModel;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        
        bottleTypes = bottleTypeService.findAll();
        return bottleTypes.size();
    }

    @Override
    protected void done() {
        for (BottleTypeDTO bottleType : bottleTypes) {
            bottleTypeTableModel.addBottleType(bottleType);
        }
    }

}
