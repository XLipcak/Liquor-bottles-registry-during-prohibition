package muni.fi.pa165.liquorbottles.client.swingWorkers;

import java.util.List;
import javax.swing.SwingWorker;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.services.BottleService;
import muni.fi.pa165.liquorbottles.client.rest.BottleRestClient;
import muni.fi.pa165.liquorbottles.client.tableModels.BottleTableModel;

/**
 *
 * @author Jakub Lipcak, Masaryk University
 */
public class FindAllBottlesSwingWorker extends SwingWorker<Integer, Integer> {

    BottleService bottleService;
    BottleTableModel bottleTableModel;
    List<BottleDTO> bottles;

    public FindAllBottlesSwingWorker(BottleService bottleService, BottleTableModel bottleTableModel) {
        this.bottleService = bottleService;
        this.bottleTableModel = bottleTableModel;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        BottleRestClient client = new BottleRestClient();

        bottles = client.getAllBottles(List.class);
        client.close();
        return bottles.size();
    }

    @Override
    protected void done() {
        for (BottleDTO bottle : bottles) {
            bottleTableModel.addBottle(bottle);
        }
    }

}
