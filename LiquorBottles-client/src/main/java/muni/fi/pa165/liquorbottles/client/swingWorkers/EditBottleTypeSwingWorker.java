/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.swingWorkers;

import javax.swing.JTable;
import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.client.rest.BottleTypeRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTypeTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class EditBottleTypeSwingWorker extends SwingWorker<BottleTypeDTO, Integer> {

    BottleTypeRestClient bottleTypeRestClient;
    BottleTypeTableModel bottleTypeTableModel;
    BottleTypeDTO bottleType;
    JTable bottleTypeTable;

    public EditBottleTypeSwingWorker(BottleTypeRestClient bottleTypeRestClient, BottleTypeTableModel bottleTypeTableModel, BottleTypeDTO bottleType, JTable bottleTypeTable) {
        this.bottleTypeRestClient = new BottleTypeRestClient();
        this.bottleTypeTableModel = bottleTypeTableModel;
        this.bottleType = bottleType;
        this.bottleTypeTable = bottleTypeTable;
    }

    @Override
    protected BottleTypeDTO doInBackground() throws Exception {
         bottleTypeRestClient.update(bottleType);
         bottleTypeRestClient.close(); 
        //bottleService.updateBottle(bottle);
        return bottleType;
    }

    @Override
    protected void done() {
        bottleTypeTableModel.updateBottleType(bottleType);
        bottleTypeTable.revalidate();
        bottleTypeTable.repaint();
    }

}
