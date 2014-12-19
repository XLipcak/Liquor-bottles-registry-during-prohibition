package muni.fi.pa165.liquorbottles.client.tableModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.dto.StoreDTO;

/**
 *
 * @author Jakub Lipcak,Michal Taraj Masaryk University
 */
public class BottleTableModel extends AbstractTableModel {

    private List<BottleDTO> bottles = new ArrayList<BottleDTO>();

    public BottleTableModel(List<BottleDTO> bottles) {
        this.bottles = bottles;
    }

    @Override
    public int getRowCount() {
        return bottles.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BottleDTO bottle = bottles.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bottle.getId();
            case 1:
                return bottle.getStore().getName();
            case 2:
                return bottle.getBottleType().getName();
            case 3:
                return bottle.getBatchNumber();
            case 4:
                return bottle.getStamp();
            case 5:{
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");               
                return dateFormat.format(bottle.getDateOfBirth());
            }
            case 6:
                return bottle.getToxicity();
            default:
                throw new IllegalArgumentException("Wrong column index!");
        }
    }
    
    public BottleDTO getBottleAt(int rowIndex){
        return bottles.get(rowIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id";
            case 1:
                return "Store";
            case 2:
                return "Bottle type";
            case 3:
                return "Batch number";
            case 4:
                return "Stamp";
            case 5:
                return "Date of birth";
            case 6:
                return "Toxicity";
            default:
                throw new IllegalArgumentException("Wrong column index!");
        }
    }

    public void addBottle(BottleDTO bottle) {
        bottles.add(bottle);
        int lastRow = bottles.size() - 1;
        fireTableRowsInserted(lastRow, lastRow);
        
    }

    public void deleteBottle(BottleDTO bottle) {
        Long id = bottles.get(0).getId();
        int row = 0;
        while (bottle.getId() != id) {
            row++;
            id = bottles.get(row).getId();
        }
        bottles.set(row, bottle);
        fireTableRowsDeleted(row, row);
    }

    public void updateBottle(BottleDTO bottle) {
        Long id = bottle.getId();
        int row = 0;
        Long tableID = bottles.get(row).getId();
        while (!id.equals(tableID)) {
            row++;
            tableID = bottles.get(row).getId();
        }
        bottles.set(row, bottle);
        fireTableRowsUpdated(row, row);
    }
    
    // TU treba dorobit nacitavanie vsetkych stores a bottletypes nie len tych co su pre vytvorene flasky
    
    public List<StoreDTO> getStores(){
        List<StoreDTO> result = new ArrayList<>();
        Set<StoreDTO> stores = new HashSet<>();
        
        for(BottleDTO b : bottles){
                stores.add(b.getStore());
        }
        
        result.addAll(stores);
        return result;
    }
    
    public List<BottleTypeDTO> getBottleTypes(){
        List<BottleTypeDTO> result = new ArrayList<>();
        Set<BottleTypeDTO> bottleTypes = new HashSet();
        
        for(BottleDTO b : bottles){
            bottleTypes.add(b.getBottleType());
        }
        
        result.addAll(bottleTypes);
        return result;
    }

}
