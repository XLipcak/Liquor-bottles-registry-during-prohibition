package muni.fi.pa165.liquorbottles.client.swingWorkers;

import java.util.List;
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
 * @author Jakub Lipcak, Masaryk University
 */
public class FindAllBottlesSwingWorker extends SwingWorker<Integer, Integer> {

    BottleRestClient bottleRest;
    BottleTableModel bottleTableModel;
    List<BottleDTO> bottles;
    JTable bottleTable;

    public FindAllBottlesSwingWorker(BottleRestClient bottleRest, BottleTableModel bottleTableModel, JTable bottleTable) {
        this.bottleRest = bottleRest;
        this.bottleTableModel = bottleTableModel;
        this.bottleTable = bottleTable;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        bottles = bottleRest.getAllBottles();
        bottleRest.close();
        return bottles.size();
    }

    @Override
    protected void done() {
        try {
            // gets the result from doInBackground and invokes exception from it if happened
            get();

            bottleTableModel.removeRows();
            for (BottleDTO bottle : bottles) {
                bottleTableModel.addBottle(bottle);
            }
            bottleTable.revalidate();
            bottleTable.repaint();
        } catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null, "Error while getting Bottles. Bottles dont exist!",  "No Bottles", JOptionPane.WARNING_MESSAGE);
        } catch (InterruptedException ex) {
            Logger.getLogger(FindAllBottlesSwingWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
