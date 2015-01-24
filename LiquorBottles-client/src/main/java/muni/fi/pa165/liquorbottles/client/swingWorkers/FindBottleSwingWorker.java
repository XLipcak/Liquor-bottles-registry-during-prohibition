package muni.fi.pa165.liquorbottles.client.swingWorkers;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.client.rest.BottleRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class FindBottleSwingWorker extends SwingWorker<BottleDTO, Integer> {

    BottleRestClient bottleRest;
    BottleTableModel bottleTableModel;
    BottleDTO bottle;
    Long bottleId;

    public FindBottleSwingWorker(BottleRestClient bottleRest, Long bottleId, BottleTableModel bottleTableModel, BottleDTO bottle) {
        this.bottleRest = bottleRest;
        this.bottleTableModel = bottleTableModel;
        this.bottle = bottle;
        this.bottleId = bottleId;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
        try {
            // gets the result from doInBackground and invokes exception from it if happened
            get();
            bottle = bottleRest.getBottleById(BottleDTO.class, bottleId.toString());
            bottleRest.close();
            return bottle;
        } catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error while getting Bottle. Bottle doesnt exist!", "No Bottle", JOptionPane.WARNING_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(FindAllBottlesSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
