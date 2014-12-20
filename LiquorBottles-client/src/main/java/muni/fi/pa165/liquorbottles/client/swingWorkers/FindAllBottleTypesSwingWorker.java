/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muni.fi.pa165.liquorbottles.client.swingWorkers;

import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.client.rest.BottleTypeRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTypeTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class FindAllBottleTypesSwingWorker extends SwingWorker<Integer, Integer> {

    BottleTypeRestClient bottleTypeRestClient;
    BottleTypeTableModel bottleTypeTableModel;
    List<BottleTypeDTO> bottleTypes;
    JTable bottleTypeTable;

    public FindAllBottleTypesSwingWorker(BottleTypeRestClient bottleTypeRestClient, BottleTypeTableModel bottleTypeTableModel, JTable bottleTypeTable) {
        this.bottleTypeRestClient = bottleTypeRestClient;
        this.bottleTypeTableModel = bottleTypeTableModel;
        this.bottleTypeTable = bottleTypeTable;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        bottleTypes = bottleTypeRestClient.getAllBottleTypes();
        bottleTypeRestClient.close();
        return bottleTypes.size();
    }

    @Override
    protected void done() {
        for (BottleTypeDTO bottleType : bottleTypes) {
            bottleTypeTableModel.addBottleType(bottleType);
        }
        bottleTypeTable.revalidate();
        bottleTypeTable.repaint();
    }

}
