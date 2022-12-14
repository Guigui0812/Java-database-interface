/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM_package;

import SGBD_Request_Package.Mariadb_Request;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guillaume
 */
public class Display_Panel extends javax.swing.JPanel {

    /**
     * Creates new form Display_Panel
     */
    public Display_Panel() {
        
        initComponents();
        
        // Chargement du driver (chargement unique).
        // Ce moment a été prévilégié car il s'agit de la première requête effectuée.
        Mariadb_Request.LoadDriver(); 
        
        // Appel de la méthode permettant la récupération des éléments de la table.
        List<String[]> resList = Mariadb_Request.Get_Data();

        // Assure que la liste n'est pas vide. 
        if(!resList.isEmpty()){
               
            // Ajout des éléments de la liste au Jtable.
            DefaultTableModel model = (DefaultTableModel) jDataTable.getModel();

            for (int i = 1; i < resList.size(); i++) {
                model.addRow(resList.get(i));
            }  
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jDataTable = new javax.swing.JTable();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_table = new javax.swing.JLabel();
        jLabel_subtitle = new javax.swing.JLabel();
        jButton_refresh = new javax.swing.JButton();
        jLabel_p = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jDataTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(66, 130, 196)));
        jDataTable.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jDataTable.setForeground(new java.awt.Color(51, 51, 51));
        jDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prenom", "Email", "Date de Naissance", "Service"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jDataTable.setGridColor(new java.awt.Color(66, 130, 196));
        jDataTable.setMinimumSize(new java.awt.Dimension(700, 250));
        jDataTable.setPreferredSize(new java.awt.Dimension(700, 250));
        jDataTable.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jDataTable.setShowGrid(true);
        jScrollPane2.setViewportView(jDataTable);

        jLabel_Title.setBackground(new java.awt.Color(66, 130, 196));
        jLabel_Title.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(241, 243, 243));
        jLabel_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Title.setText("Application de gestion des salariés de l'entreprise RG-LBG");
        jLabel_Title.setOpaque(true);

        jLabel_table.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel_table.setForeground(new java.awt.Color(66, 130, 196));
        jLabel_table.setText("Liste des salariés de l'entreprise :");

        jLabel_subtitle.setBackground(new java.awt.Color(239, 234, 234));
        jLabel_subtitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_subtitle.setForeground(new java.awt.Color(0, 102, 204));
        jLabel_subtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_subtitle.setText("Bienvenue sur cette application de gestion conçue par Guillaume Rohee et Gautier Le Bouquin");

        jButton_refresh.setBackground(new java.awt.Color(66, 130, 196));
        jButton_refresh.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton_refresh.setForeground(new java.awt.Color(255, 255, 255));
        jButton_refresh.setText("Rafraîchir");
        jButton_refresh.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_refresh.setBorderPainted(false);
        jButton_refresh.setFocusPainted(false);
        jButton_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_refreshMouseClicked(evt);
            }
        });

        jLabel_p.setBackground(new java.awt.Color(0, 102, 204));
        jLabel_p.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel_p.setForeground(new java.awt.Color(0, 102, 204));
        jLabel_p.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_p.setText("Vous pouvez : ajouter un salarié, le supprimer et afficher ses détails à l'aide du menu déroulant");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_subtitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_p, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_table)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_subtitle)
                .addGap(9, 9, 9)
                .addComponent(jLabel_p)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_table)
                    .addComponent(jButton_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Mise à jour des informations de la Jtable au clique du bouton.
    private void jButton_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_refreshMouseClicked
        // TODO add your handling code here:
        
        // Appel de la méthode permettant la récupération des éléments de la table.
        List<String[]> resList = Mariadb_Request.Get_Data();        
        if(!resList.isEmpty()){
            DefaultTableModel model = (DefaultTableModel) jDataTable.getModel();

            // Ajout des éléments de la liste au Jtable.
            model.fireTableDataChanged();
            model.setRowCount(0);

            for (int i = 1; i < resList.size(); i++) {
                model.addRow(resList.get(i));
            } 
        }
    }//GEN-LAST:event_jButton_refreshMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_refresh;
    private javax.swing.JTable jDataTable;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_p;
    private javax.swing.JLabel jLabel_subtitle;
    private javax.swing.JLabel jLabel_table;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
