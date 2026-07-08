package ui.internal;

import db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HapusSupplierInternal extends JInternalFrame {



    public HapusSupplierInternal() {
        initComponents();
        pack();

        loadIds();
        btnRefresh.addActionListener(e -> loadIds());
        btnHapusSatu.addActionListener(e -> hapusSatu());
        btnHapusSemua.addActionListener(e -> hapusSemua());
    }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Hapus Supplier");

        comboIdLbl = new javax.swing.JLabel();
        comboId = new javax.swing.JComboBox<>(new String[]{});
        btnRefresh = new javax.swing.JButton();
        btnHapusSatu = new javax.swing.JButton();
        btnHapusSemua = new javax.swing.JButton();

        comboIdLbl.setText("Pilih ID Supplier:");
        btnRefresh.setText("Refresh");
        btnHapusSatu.setText("Hapus Data Ini");
        btnHapusSemua.setText("Hapus SEMUA Data Supplier");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboIdLbl)
                    )
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnRefresh)
                    .addComponent(btnHapusSatu)
                    .addComponent(btnHapusSemua)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboIdLbl)
                    .addComponent(comboId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(btnHapusSatu)
                    .addComponent(btnHapusSemua)
                )
        );
    }// </editor-fold>//GEN-END:initComponents


    private void loadIds() {
        comboId.removeAllItems();
        String sql = "SELECT id_supplier, nama_supplier FROM supplier ORDER BY id_supplier";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comboId.addItem(rs.getString("id_supplier") + " - " + rs.getString("nama_supplier"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data:\n" + ex.getMessage());
        }
    }

    private void hapusSatu() {
        String selected = (String) comboId.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk dihapus.");
            return;
        }
        String id = selected.split(" - ")[0].trim();
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus supplier \"" + selected + "\"?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM supplier WHERE id_supplier=?")) {
            ps.setString(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data supplier berhasil dihapus.");
            loadIds();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data:\n" + ex.getMessage());
        }
    }

    private void hapusSemua() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Anda akan menghapus SEMUA data supplier. Lanjutkan?",
                "Konfirmasi Hapus Semua", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm != JOptionPane.YES_OPTION) return;
        int confirm2 = JOptionPane.showConfirmDialog(this,
                "Tindakan ini TIDAK DAPAT dibatalkan. Yakin ingin melanjutkan?",
                "Konfirmasi Terakhir", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm2 != JOptionPane.YES_OPTION) return;

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DELETE FROM supplier");
            JOptionPane.showMessageDialog(this, "Seluruh data supplier berhasil dihapus.");
            loadIds();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus semua data:\n" + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel comboIdLbl;
    private javax.swing.JComboBox<String> comboId;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnHapusSatu;
    private javax.swing.JButton btnHapusSemua;
    // End of variables declaration//GEN-END:variables
}
