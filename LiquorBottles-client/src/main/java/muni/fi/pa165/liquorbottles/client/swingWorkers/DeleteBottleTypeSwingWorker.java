package muni.fi.pa165.liquorbottles.client.swingWorkers;

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
public class DeleteBottleTypeSwingWorker extends SwingWorker<BottleTypeDTO, Integer> {

    BottleTypeRestClient bottleTypeRestClient;
    BottleTypeTableModel bottleTypeTableModel;
    BottleTypeDTO bottleType;
    Long bottleTypeID;
    JTable bottleTypeTable;

    public DeleteBottleTypeSwingWorker(BottleTypeRestClient bottleTypeRestClient, BottleTypeTableModel bottleTypeTableModel, BottleTypeDTO bottleType, JTable bottleTypeTable) {
        this.bottleTypeRestClient = bottleTypeRestClient;
        this.bottleTypeTableModel = bottleTypeTableModel;
        this.bottleType = bottleType;
        this.bottleTypeTable = bottleTypeTable;
    }

    @Override
    protected BottleTypeDTO doInBackground() throws Exception {
        bottleTypeRestClient.remove(bottleType.getId());
        bottleTypeRestClient.close();
        return bottleType;
    }

    @Override
    protected void done() {
        try {
            get();
            bottleTypeTableModel.deleteBottleType(bottleType);
            bottleTypeTable.revalidate();
            bottleTypeTable.repaint();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error while deleting Bottle Type!", "Bottle Type delete error", JOptionPane.WARNING_MESSAGE);
        } catch (ExecutionException ex) {
            Logger.getLogger(DeleteBottleTypeSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
