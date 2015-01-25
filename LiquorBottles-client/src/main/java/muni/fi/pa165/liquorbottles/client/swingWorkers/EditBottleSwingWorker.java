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
public class EditBottleSwingWorker extends SwingWorker<BottleDTO, Integer> {

    BottleRestClient bottleRest;
    BottleTableModel bottleTableModel;
    BottleDTO bottle;
    JTable bottleTable;

    public EditBottleSwingWorker(BottleRestClient bottleRest, BottleTableModel bottleTableModel, BottleDTO bottle, JTable bottleTable) {
        this.bottleRest = new BottleRestClient();
        this.bottleTableModel = bottleTableModel;
        this.bottle = bottle;
        this.bottleTable = bottleTable;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
        bottleRest.update(bottle);
        bottleRest.close();
        return bottle;
    }

    @Override
    protected void done() {
        try {
            // gets the result from doInBackground and invokes exception from it if happened
            get();
            bottleTableModel.updateBottle(bottle);
            bottleTable.revalidate();
            bottleTable.repaint();
        } catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error while inserting Bottle!", "Bottle insert error", JOptionPane.WARNING_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(FindAllBottlesSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
