package muni.fi.pa165.liquorbottles.client.swingWorkers;

import javax.swing.JTable;
import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import muni.fi.pa165.liquorbottles.client.rest.BottleRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class EditBottleSwingWorker extends SwingWorker<BottleDTO, Integer> {

    BottleService bottleService;
    BottleTableModel bottleTableModel;
    BottleDTO bottle;
    JTable bottleTable;

    public EditBottleSwingWorker(BottleService bottleService, BottleTableModel bottleTableModel, BottleDTO bottle, JTable bottleTable) {
        this.bottleService = bottleService;
        this.bottleTableModel = bottleTableModel;
        this.bottle = bottle;
        this.bottleTable = bottleTable;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
        
         BottleRestClient client = new BottleRestClient();
         client.update(bottle);
         client.close();
        
        //bottleService.updateBottle(bottle);
        return bottle;
    }

    @Override
    protected void done() {
        bottleTableModel.updateBottle(bottle);
        bottleTable.revalidate();
        bottleTable.repaint();
    }

}
