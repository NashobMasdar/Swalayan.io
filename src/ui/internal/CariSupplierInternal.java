package ui.internal;

import db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CariSupplierInternal extends JInternalFrame {


    private javax.swing.table.DefaultTableModel model;


    public CariSupplierInternal() {
        initComponents();
        pack();

        wireEvents();
    }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cari Supplier");

        txtCariLbl = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnSemua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        txtCariLbl.setText("Cari (ID/Nama Supplier):");
        btnCari.setText("Cari");
        btnSemua.setText("Tampilkan Semua");
        model = new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {"ID Supplier", "Nama Supplier", "Alamat", "No HP", "Email"}) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table.setModel(model);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtCariLbl)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnSemua)
                )
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariLbl)
                    .addComponent(txtCari)
                    .addComponent(btnCari)
                    .addComponent(btnSemua)
                )
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void wireEvents() {
        // Live search: tabel otomatis diperbarui setiap huruf diketik
        txtCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { loadData(txtCari.getText().trim()); }
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { loadData(txtCari.getText().trim()); }
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { loadData(txtCari.getText().trim()); }
        });

        btnCari.addActionListener(e -> loadData(txtCari.getText().trim()));
        btnSemua.addActionListener(e -> {
            txtCari.setText("");
            loadData("");
        });
        loadData("");
    }

    private void loadData(String keyword) {
        model.setRowCount(0);
        String sql = "SELECT id_supplier, nama_supplier, alamat_supplier, no_hp, email_supplier FROM supplier "
                + "WHERE id_supplier LIKE ? OR nama_supplier LIKE ? ORDER BY id_supplier";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getString("id_supplier"), rs.getString("nama_supplier"),
                            rs.getString("alamat_supplier"), rs.getString("no_hp"),
                            rs.getString("email_supplier")
                    });
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data:\n" + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel txtCariLbl;
    private javax.swing.JTextField txtCari;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnSemua;
    // End of variables declaration//GEN-END:variables
}
