package ui.internal;

import db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditSupplierInternal extends JInternalFrame {



    public EditSupplierInternal() {
        initComponents();
        pack();

        loadIds();
        btnMuat.addActionListener(e -> muatData());
        btnSimpan.addActionListener(e -> simpan());
    }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Edit Supplier");

        comboIdLbl = new javax.swing.JLabel();
        comboId = new javax.swing.JComboBox<>(new String[]{});
        txtNamaLbl = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtAlamatLbl = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        txtHpLbl = new javax.swing.JLabel();
        txtHp = new javax.swing.JTextField();
        txtEmailLbl = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnMuat = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        comboIdLbl.setText("Pilih ID Supplier:");
        txtNamaLbl.setText("Nama Supplier:");
        txtAlamatLbl.setText("Alamat:");
        txtHpLbl.setText("No HP:");
        txtEmailLbl.setText("Email:");
        btnMuat.setText("Muat Data");
        btnSimpan.setText("Simpan Perubahan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboIdLbl)
                        .addComponent(txtNamaLbl)
                        .addComponent(txtAlamatLbl)
                        .addComponent(txtHpLbl)
                        .addComponent(txtEmailLbl)
                    )
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnMuat)
                    .addComponent(btnSimpan)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboIdLbl)
                    .addComponent(comboId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaLbl)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlamatLbl)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHpLbl)
                    .addComponent(txtHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailLbl)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuat)
                    .addComponent(btnSimpan)
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
            JOptionPane.showMessageDialog(this, "Gagal memuat daftar supplier:\n" + ex.getMessage());
        }
    }

    private String getSelectedId() {
        String selected = (String) comboId.getSelectedItem();
        if (selected == null) return null;
        return selected.split(" - ")[0].trim();
    }

    private void muatData() {
        String id = getSelectedId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Data supplier belum tersedia.");
            return;
        }
        String sql = "SELECT * FROM supplier WHERE id_supplier = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    txtNama.setText(rs.getString("nama_supplier"));
                    txtAlamat.setText(rs.getString("alamat_supplier"));
                    txtHp.setText(rs.getString("no_hp"));
                    txtEmail.setText(rs.getString("email_supplier"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data:\n" + ex.getMessage());
        }
    }

    private void simpan() {
        String id = getSelectedId();
        if (id == null) return;
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama supplier tidak boleh kosong.");
            return;
        }

        String sql = "UPDATE supplier SET nama_supplier=?, alamat_supplier=?, no_hp=?, email_supplier=? WHERE id_supplier=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txtNama.getText().trim());
            ps.setString(2, txtAlamat.getText().trim());
            ps.setString(3, txtHp.getText().trim());
            ps.setString(4, txtEmail.getText().trim());
            ps.setString(5, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data supplier berhasil diperbarui.");
            loadIds();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan perubahan:\n" + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel comboIdLbl;
    private javax.swing.JComboBox<String> comboId;
    private javax.swing.JLabel txtNamaLbl;
    private javax.swing.JTextField txtNama;
    private javax.swing.JLabel txtAlamatLbl;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JLabel txtHpLbl;
    private javax.swing.JTextField txtHp;
    private javax.swing.JLabel txtEmailLbl;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JButton btnMuat;
    private javax.swing.JButton btnSimpan;
    // End of variables declaration//GEN-END:variables
}
