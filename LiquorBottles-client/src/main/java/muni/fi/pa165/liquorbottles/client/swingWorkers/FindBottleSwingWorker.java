package muni.fi.pa165.liquorbottles.client.swingWorkers;

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

    public FindBottleSwingWorker(BottleRestClient bottleRest,Long bottleId, BottleTableModel bottleTableModel, BottleDTO bottle) {
        this.bottleRest = bottleRest;
        this.bottleTableModel = bottleTableModel;
        this.bottle = bottle;
        this.bottleId = bottleId;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
        bottle = bottleRest.getBottleById(BottleDTO.class, bottleId.toString());
        bottleRest.close();
        return bottle;
    }

}
