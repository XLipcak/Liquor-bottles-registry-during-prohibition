package muni.fi.pa165.liquorbottles.client.swingWorkers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
        try {
            // gets the result from doInBackground and invokes exception from it if happened
            get();

            bottleTypeTableModel.removeRows();
            for (BottleTypeDTO bottleType : bottleTypes) {
                bottleTypeTableModel.addBottleType(bottleType);
            }
            bottleTypeTable.revalidate();
            bottleTypeTable.repaint();

        } catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error while getting Bottle Types. Bottle Types dont exist!", "No Bottle Types", JOptionPane.WARNING_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(FindAllBottlesSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
