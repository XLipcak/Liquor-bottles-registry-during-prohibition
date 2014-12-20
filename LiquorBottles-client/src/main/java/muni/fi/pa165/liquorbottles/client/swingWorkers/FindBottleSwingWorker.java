package muni.fi.pa165.liquorbottles.client.swingWorkers;

import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import muni.fi.pa165.liquorbottles.client.rest.BottleRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTableModel;

/**
 *
 * @author Matus Novak, Masaryk University
 */
public class FindBottleSwingWorker extends SwingWorker<BottleDTO, Integer> {

    BottleService bottleService;
    BottleTableModel bottleTableModel;
    BottleDTO bottle;
    Long bottleId;

    public FindBottleSwingWorker(BottleService bottleService,Long bottleId, BottleTableModel bottleTableModel, BottleDTO bottle) {
        this.bottleService = bottleService;
        this.bottleTableModel = bottleTableModel;
        this.bottle = bottle;
        this.bottleId = bottleId;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
         BottleRestClient client = new BottleRestClient();

        bottle = client.getBottleById(BottleDTO.class, bottleId.toString());
        client.close();
        return bottle;
    }

}
