package muni.fi.pa165.liquorbottles.client.swingWorkers;

import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class EditBottleSwingWorker extends SwingWorker<BottleDTO, Integer> {

    BottleService bottleService;
    BottleTableModel bottleTableModel;
    BottleDTO bottle;

    public EditBottleSwingWorker(BottleService bottleService, BottleTableModel bottleTableModel, BottleDTO bottle) {
        this.bottleService = bottleService;
        this.bottleTableModel = bottleTableModel;
        this.bottle = bottle;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
        bottleService.updateBottle(bottle);
        return bottle;
    }

    @Override
    protected void done() {
        bottleTableModel.updateBottle(bottle);
    }

}