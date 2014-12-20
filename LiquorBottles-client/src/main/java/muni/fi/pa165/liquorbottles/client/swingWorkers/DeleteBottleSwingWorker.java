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
public class DeleteBottleSwingWorker extends SwingWorker<BottleDTO, Integer> {

    BottleService bottleService;
    BottleTableModel bottleTableModel;
    Long bottleId;
    BottleDTO bottle;
    JTable bottleTable;

    public DeleteBottleSwingWorker(BottleService bottleService, BottleTableModel bottleTableModel, Long bottleId, JTable bottleTable) {
        this.bottleService = bottleService;
        this.bottleTableModel = bottleTableModel;
        this.bottleId = bottleId;
        this.bottleTable = bottleTable;
    }

    @Override
    protected BottleDTO doInBackground() throws Exception {
        BottleRestClient client = new BottleRestClient();

        BottleDTO bottle = client.getBottleById(BottleDTO.class, bottleId.toString());
        this.bottle = bottle;

        client.remove(bottle);
        client.close();
        return bottle;
    }

    @Override
    protected void done() {
        bottleTableModel.deleteBottle(bottle);
        bottleTable.revalidate();
        bottleTable.repaint();
    }

}
