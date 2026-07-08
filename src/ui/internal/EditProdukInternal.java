package ui.internal;

import db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditProdukInternal extends JInternalFrame {



    public EditProdukInternal() {
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
        setTitle("Edit Produk");

        comboIdLbl = new javax.swing.JLabel();
        comboId = new javax.swing.JComboBox<>(new String[]{});
        txtNamaLbl = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtJenisLbl = new javax.swing.JLabel();
        txtJenis = new javax.swing.JTextField();
        txtStokLbl = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        txtHargaLbl = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        btnMuat = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        comboIdLbl.setText("Pilih ID Produk:");
        txtNamaLbl.setText("Nama Produk:");
        txtJenisLbl.setText("Jenis Produk:");
        txtStokLbl.setText("Stok:");
        txtHargaLbl.setText("Harga:");
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
                        .addComponent(txtJenisLbl)
                        .addComponent(txtStokLbl)
                        .addComponent(txtHargaLbl)
                    )
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtJenisLbl)
                    .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStokLbl)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHargaLbl)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuat)
                    .addComponent(btnSimpan)
                )
        );
    }// </editor-fold>//GEN-END:initComponents


    private void loadIds() {
        comboId.removeAllItems();
        String sql = "SELECT id_produk, nama_produk FROM produk ORDER BY id_produk";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comboId.addItem(rs.getString("id_produk") + " - " + rs.getString("nama_produk"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat daftar produk:\n" + ex.getMessage());
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
            JOptionPane.showMessageDialog(this, "Data produk belum tersedia.");
            return;
        }
        String sql = "SELECT * FROM produk WHERE id_produk = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    txtNama.setText(rs.getString("nama_produk"));
                    txtJenis.setText(rs.getString("jenis_produk"));
                    txtStok.setText(String.valueOf(rs.getInt("stok_produk")));
                    txtHarga.setText(String.valueOf(rs.getInt("harga_produk")));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data:\n" + ex.getMessage());
        }
    }

    private void simpan() {
        String id = getSelectedId();
        if (id == null) return;
        int stok, harga;
        try {
            stok = Integer.parseInt(txtStok.getText().trim());
            harga = Integer.parseInt(txtHarga.getText().trim());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka.");
            return;
        }

        String sql = "UPDATE produk SET nama_produk=?, jenis_produk=?, stok_produk=?, harga_produk=? WHERE id_produk=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txtNama.getText().trim());
            ps.setString(2, txtJenis.getText().trim());
            ps.setInt(3, stok);
            ps.setInt(4, harga);
            ps.setString(5, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data produk berhasil diperbarui.");
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
    private javax.swing.JLabel txtJenisLbl;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JLabel txtStokLbl;
    private javax.swing.JTextField txtStok;
    private javax.swing.JLabel txtHargaLbl;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JButton btnMuat;
    private javax.swing.JButton btnSimpan;
    // End of variables declaration//GEN-END:variables
}
