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
public class DeleteBottleTypeSwingWorker extends SwingWorker<BottleTypeDTO, Integer> {

    BottleTypeRestClient bottleTypeRestClient;
    BottleTypeTableModel bottleTypeTableModel;
    BottleTypeDTO bottleType;
    Long bottleTypeID;
    JTable bottleTypeTable;

    public DeleteBottleTypeSwingWorker(BottleTypeRestClient bottleTypeRestClient, BottleTypeTableModel bottleTypeTableModel, Long bottleTypeID, JTable bottleTypeTable) {
        this.bottleTypeRestClient = bottleTypeRestClient;
        this.bottleTypeTableModel = bottleTypeTableModel;
        this.bottleTypeID = bottleTypeID;
        this.bottleTypeTable = bottleTypeTable;
    }

    @Override
    protected BottleTypeDTO doInBackground() throws Exception {
        BottleTypeDTO bottleTypeDTO = bottleTypeRestClient.getBottleTypeById(BottleTypeDTO.class, bottleTypeID.toString());
        this.bottleType = bottleTypeDTO;
        bottleTypeRestClient.remove(bottleType);
        bottleTypeRestClient.close();
        return bottleType;
    }

    @Override
    protected void done() {
        bottleTypeTableModel.deleteBottleType(bottleType);
        bottleTypeTable.revalidate();
        bottleTypeTable.repaint();
    }

}
