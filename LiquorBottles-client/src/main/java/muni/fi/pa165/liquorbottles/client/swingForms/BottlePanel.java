package muni.fi.pa165.liquorbottles.client.swingForms;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import muni.fi.pa165.liquorbottles.api.dto.BottleDTO;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.dto.StoreDTO;
import muni.fi.pa165.liquorbottles.api.dto.ToxicityDTO;

/**
 *
 * @author Jakub Lipcak,Michal Taraj Masaryk University
 */
public class BottlePanel extends javax.swing.JPanel {

    List<StoreDTO> stores;
    List<BottleTypeDTO> bottleTypes;
    Vector<StoreItem> storeItemsModel;
    Vector<BottleTypeItem> bottleTypeModel;

    /**
     * Creates new form BottlePanel
     */
    public BottlePanel(List<StoreDTO> stores, List<BottleTypeDTO> bottleTypes) {
        this.stores = stores;
        this.bottleTypes = bottleTypes;
        initComponents();

        fillStoreCombobox();
        fillBottleTypeCombobox();
    }

    private void fillStoreCombobox() {
        storeItemsModel = new Vector();
        for (StoreDTO s : stores) {
            storeItemsModel.addElement(new StoreItem(s));
        }
        storeComboBox.setModel(new javax.swing.DefaultComboBoxModel(storeItemsModel));
    }

    private void fillBottleTypeCombobox() {
        bottleTypeModel = new Vector();
        for (BottleTypeDTO bt : bottleTypes) {
            bottleTypeModel.addElement(new BottleTypeItem(bt));
        }
        bottleTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(bottleTypeModel));
    }
    
    private StoreItem findStoreItemByDTO(StoreDTO storeDTO){
        for(StoreItem s: storeItemsModel){
            if (s.getStoreDTO().equals(storeDTO)){
                return s;
            }
        }
        return null;
    }
    
    private BottleTypeItem findBottleTypeItemByDTO(BottleTypeDTO bottleTypeDTO){
        for(BottleTypeItem b: bottleTypeModel){
            if (b.getBottleTypeDTO().equals(bottleTypeDTO)){
                return b;
            }
        }
        return null;
    }

    class StoreItem {

        private final String name;
        private final StoreDTO storeDTO;

        public StoreItem(StoreDTO storeDTO) {
            this.name = storeDTO.getName();
            this.storeDTO = storeDTO;
        }

        public StoreDTO getStoreDTO() {
            return storeDTO;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    class BottleTypeItem {

        private final String name;
        private final BottleTypeDTO bottleTypeDTO;

        public BottleTypeItem(BottleTypeDTO bottleTypeDTO) {
            this.name = bottleTypeDTO.getName();
            this.bottleTypeDTO = bottleTypeDTO;
        }

        public BottleTypeDTO getBottleTypeDTO() {
            return bottleTypeDTO;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public BottleDTO returnBottle() {
        BottleDTO result = new BottleDTO();       
        result.setBatchNumber(Long.valueOf(batchNumberTextField.getText()));
        BottleTypeItem bottleTypeItem = (BottleTypeItem) bottleTypeComboBox.getSelectedItem();  
        result.setBottleType(bottleTypeItem.getBottleTypeDTO());
        
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            result.setDateOfBirth(dateFormat.parse(dateTextField.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(BottlePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.setStamp(Long.valueOf(stampNumberTextField.getText()));
        
        StoreItem storeItem = (StoreItem) storeComboBox.getSelectedItem();
        result.setStore(storeItem.getStoreDTO());
        
        result.setToxicity(getSelectedToxicity());
        return result;
    }
    
    private ToxicityDTO getSelectedToxicity(){
        if (toxicityComboBox.getSelectedItem().toString().equalsIgnoreCase("TOXIC")){
            return ToxicityDTO.TOXIC;
        } else if (toxicityComboBox.getSelectedItem().toString().equalsIgnoreCase("NON_TOXIC")){
            return ToxicityDTO.NON_TOXIC;
        } else{
            return ToxicityDTO.UNCHECKED;
        }
    
    }
    
    private void setToxicityCombobox(ToxicityDTO toxicityDTO){
        if (toxicityDTO.equals(ToxicityDTO.TOXIC)){
            toxicityComboBox.setSelectedItem("TOXIC");
        } else if (toxicityDTO.equals(ToxicityDTO.NON_TOXIC)){
            toxicityComboBox.setSelectedItem("NON_TOXIC");
        } else {
            toxicityComboBox.setSelectedItem("UNCHECKED");
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        storeComboBox = new javax.swing.JComboBox();
        bottleTypeComboBox = new javax.swing.JComboBox();
        batchNumberTextField = new javax.swing.JTextField();
        stampNumberTextField = new javax.swing.JTextField();
        dateTextField = new javax.swing.JTextField();
        toxicityComboBox = new javax.swing.JComboBox();

        jLabel1.setText("Store: ");

        jLabel2.setText("Bottle type:");

        jLabel3.setText("Batch number:");

        jLabel4.setText("Stamp number:");

        jLabel5.setText("Date:");

        jLabel6.setText("Toxicity:");

        storeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        storeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeComboBoxActionPerformed(evt);
            }
        });

        bottleTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        toxicityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TOXIC", "NON_TOXIC", "UNCHECKED" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(storeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toxicityComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dateTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(stampNumberTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(batchNumberTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(bottleTypeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(storeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bottleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(batchNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(stampNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(toxicityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void storeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_storeComboBoxActionPerformed
  
    public void setBottle(BottleDTO bottle){
        storeComboBox.setSelectedItem(findStoreItemByDTO(bottle.getStore()));
        bottleTypeComboBox.setSelectedItem(findBottleTypeItemByDTO(bottle.getBottleType()));
        batchNumberTextField.setText(String.valueOf(bottle.getBatchNumber()));
        stampNumberTextField.setText(String.valueOf(bottle.getStamp()));
        
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateTextField.setText(dateFormat.format(bottle.getDateOfBirth()));
        setToxicityCombobox(bottle.getToxicity());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField batchNumberTextField;
    private javax.swing.JComboBox bottleTypeComboBox;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField stampNumberTextField;
    private javax.swing.JComboBox storeComboBox;
    private javax.swing.JComboBox toxicityComboBox;
    // End of variables declaration//GEN-END:variables
}
