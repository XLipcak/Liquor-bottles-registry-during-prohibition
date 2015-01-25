package muni.fi.pa165.liquorbottles.client.swingWorkers;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.client.rest.BottleRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class DeleteBottleSwingWorker extends SwingWorker<BottleDTO, Integer> {

    BottleRestClient bottleRest;
    BottleTableModel bottleTableModel;
    Long bottleId;
    BottleDTO bottle;
    JTable bottleTable;

    public DeleteBottleSwingWorker(BottleRestClient bottleRest, BottleTableModel bottleTableModel, BottleDTO bottleD, JTable bottleTable) {
        this.bottleRest = new BottleRestClient();
        this.bottleTableModel = bottleTableModel;
        this.bottle = bottleD;
        this.bottleTable = bottleTable;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
        bottleRest.remove(bottle.getId());
        bottleRest.close();
        return bottle;
    }

    @Override
    protected void done() {
        try {
            get();
            bottleTableModel.deleteBottle(bottle);
            bottleTable.revalidate();
            bottleTable.repaint();
        }catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error while deleting Bottle. Bottle doesnt exist!", "No Bottle", JOptionPane.WARNING_MESSAGE);
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(FindAllBottlesSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
