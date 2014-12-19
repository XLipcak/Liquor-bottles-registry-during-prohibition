package muni.fi.pa165.liquorbottles.client.tableModels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;

/**
 *
 * @author Matus Novak,Michal Taraj Masaryk University
 */
public class BottleTypeTableModel extends AbstractTableModel {

    private List<BottleTypeDTO> bottleTypes = new ArrayList<BottleTypeDTO>();

    public BottleTypeTableModel(List<BottleTypeDTO> bottleTypes) {
        this.bottleTypes = bottleTypes;
    }

    @Override
    public int getRowCount() {
        return bottleTypes.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }
    
    public BottleTypeDTO getBottleAt(int rowIndex){
        return bottleTypes.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BottleTypeDTO bottleType = bottleTypes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bottleType.getId();
            case 1:
                return bottleType.getName();
            case 2:
                return bottleType.getAlcType();
            case 3:
                return bottleType.getPower();
            case 4:
                return bottleType.getVolume();
            case 5:
                return bottleType.getProducer().getName();
            default:
                throw new IllegalArgumentException("Wrong column index!");
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id";
            case 1:
                return "Name";
            case 2:
                return "Alcohol Type";
            case 3:
                return "Power";
            case 4:
                return "Volume";
            case 5:
                return "Producer";
            default:
                throw new IllegalArgumentException("Wrong column index!");
        }
    }

    public void addBottleType(BottleTypeDTO bottleType) {
        bottleTypes.add(bottleType);
        int lastRow = bottleTypes.size() - 1;
        fireTableRowsInserted(lastRow, lastRow);
    }

    public void deleteBottleType(BottleTypeDTO bottleType) {
        Long id = bottleTypes.get(0).getId();
        int row = 0;
        while (bottleType.getId() != id) {
            row++;
            id = bottleTypes.get(row).getId();
        }
        bottleTypes.set(row, bottleType);
        fireTableRowsDeleted(row, row);
    }

    public void updateBottleType(BottleTypeDTO bottleType) {
        Long id = bottleType.getId();
        int row = 0;
        Long tableID = bottleTypes.get(row).getId();
        while (!id.equals(tableID)) {
            row++;
            tableID = bottleTypes.get(row).getId();
        }
        bottleTypes.set(row, bottleType);
        fireTableRowsUpdated(row, row);
    }
    // TU treba dorobit nacitavanie vsetkych producers nie len tych co su pre vytvorene flasky
  
    public List<ProducerDTO> getProducers(){
        List<ProducerDTO> result = new ArrayList<>();
        Set<ProducerDTO> producers = new HashSet();
        
        for(BottleTypeDTO bt : bottleTypes){
            producers.add(bt.getProducer());
        }
        
        result.addAll(producers);
        return result;
    }
}
