package muni.fi.pa165.liquorbottles.client.swingForms;

import java.util.List;
import java.util.Vector;
import muni.fi.pa165.liquorbottles.api.dto.BottleTypeDTO;
import muni.fi.pa165.liquorbottles.api.dto.ProducerDTO;

/**
 *
 * @author Michal Taraj
 */
public class BottleTypePanel extends javax.swing.JPanel {

    List<ProducerDTO> producers;
    Vector<ProducerItem> producerItemsModel;

    /**
     * Creates new form BottleTypePanel
     */
    public BottleTypePanel(List<ProducerDTO> producers) {
        this.producers = producers;
        initComponents();
        fillProducerCombobox();
    }

    private void fillProducerCombobox() {
        producerItemsModel = new Vector();
        for (ProducerDTO p : producers) {
            producerItemsModel.addElement(new ProducerItem(p));
        }
        producerComboBox.setModel(new javax.swing.DefaultComboBoxModel(producerItemsModel));
    }

    private ProducerItem findProducerItemByDTO(ProducerDTO producerDTO) {
        for (ProducerItem p : producerItemsModel) {
            if (p.getProducerDTO().equals(producerDTO)) {
                return p;
            }
        }
        return null;
    }

    class ProducerItem {

        private final String name;
        private final ProducerDTO producerDTO;

        public ProducerItem(ProducerDTO producerDTO) {
            this.name = producerDTO.getName();
            this.producerDTO = producerDTO;
        }

        public ProducerDTO getProducerDTO() {
            return producerDTO;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public boolean validatePanel() {
        if (producerComboBox.getSelectedItem() == null || alcoholTypeTextField.getText() == null
                || bottleTypeNameTextField.getText() == null || powerTextField.getText() == null
                || alcoholTypeTextField.getText().equals("") || bottleTypeNameTextField.getText().equals("")
                || volumeTextField.getText() == null) {
            return false;
        }
        try {
            Integer.parseInt(powerTextField.getText());
            Integer.parseInt(volumeTextField.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public BottleTypeDTO returnBottleType() {
        BottleTypeDTO result = new BottleTypeDTO();
        ProducerItem producerItem = (ProducerItem) producerComboBox.getSelectedItem();
        result.setProducer(producerItem.getProducerDTO());

        result.setAlcType(alcoholTypeTextField.getText());
        result.setName(bottleTypeNameTextField.getText());
        result.setPower(Integer.valueOf(powerTextField.getText()));
        result.setVolume(Integer.valueOf(volumeTextField.getText()));
        return result;
    }

    public void setBottleType(BottleTypeDTO bottleType) {
        producerComboBox.setSelectedItem(findProducerItemByDTO(bottleType.getProducer()));
        alcoholTypeTextField.setText(bottleType.getAlcType());
        bottleTypeNameTextField.setText(bottleType.getName());
        powerTextField.setText(String.valueOf(bottleType.getPower()));
        volumeTextField.setText(String.valueOf(bottleType.getVolume()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        producerLabel = new javax.swing.JLabel();
        bottleNameLabel = new javax.swing.JLabel();
        volumeLabel = new javax.swing.JLabel();
        alcoholTypeLabel = new javax.swing.JLabel();
        powerLabel = new javax.swing.JLabel();
        producerComboBox = new javax.swing.JComboBox();
        volumeTextField = new javax.swing.JTextField();
        alcoholTypeTextField = new javax.swing.JTextField();
        powerTextField = new javax.swing.JTextField();
        bottleTypeNameTextField = new javax.swing.JTextField();

        producerLabel.setText("Producer: ");

        bottleNameLabel.setText("Bottle Type name:");

        volumeLabel.setText("Volume:");

        alcoholTypeLabel.setText("Alcohol type:");

        powerLabel.setText("Power:");

        producerComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        producerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                producerComboBoxActionPerformed(evt);
            }
        });

        volumeTextField.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(producerLabel)
                            .addComponent(bottleNameLabel)
                            .addComponent(volumeLabel)
                            .addComponent(powerLabel))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(powerTextField)
                            .addComponent(volumeTextField)
                            .addComponent(bottleTypeNameTextField)
                            .addComponent(producerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(alcoholTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(alcoholTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(producerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bottleTypeNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bottleNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alcoholTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alcoholTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(powerLabel)
                    .addComponent(powerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void producerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_producerComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_producerComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alcoholTypeLabel;
    private javax.swing.JTextField alcoholTypeTextField;
    private javax.swing.JLabel bottleNameLabel;
    private javax.swing.JTextField bottleTypeNameTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel powerLabel;
    private javax.swing.JTextField powerTextField;
    private javax.swing.JComboBox producerComboBox;
    private javax.swing.JLabel producerLabel;
    private javax.swing.JLabel volumeLabel;
    private javax.swing.JTextField volumeTextField;
    // End of variables declaration//GEN-END:variables
}
