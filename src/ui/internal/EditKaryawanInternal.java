package ui.internal;

import db.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditKaryawanInternal extends JInternalFrame {



    public EditKaryawanInternal() {
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
        setTitle("Edit Karyawan");

        comboIdLbl = new javax.swing.JLabel();
        comboId = new javax.swing.JComboBox<>(new String[]{});
        txtNamaLbl = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtPasswordLbl = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        comboJabatanLbl = new javax.swing.JLabel();
        comboJabatan = new javax.swing.JComboBox<>(new String[]{"Karyawan", "Manager"});
        txtAlamatLbl = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        comboGenderLbl = new javax.swing.JLabel();
        comboGender = new javax.swing.JComboBox<>(new String[]{"L", "P"});
        txtHpLbl = new javax.swing.JLabel();
        txtHp = new javax.swing.JTextField();
        btnMuat = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        comboIdLbl.setText("Pilih ID Kasir:");
        txtNamaLbl.setText("Nama:");
        txtPasswordLbl.setText("Password Baru:");
        comboJabatanLbl.setText("Jabatan:");
        txtAlamatLbl.setText("Alamat:");
        comboGenderLbl.setText("Jenis Kelamin:");
        txtHpLbl.setText("No HP:");
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
                        .addComponent(txtPasswordLbl)
                        .addComponent(comboJabatanLbl)
                        .addComponent(txtAlamatLbl)
                        .addComponent(comboGenderLbl)
                        .addComponent(txtHpLbl)
                    )
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtPasswordLbl)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboJabatanLbl)
                    .addComponent(comboJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlamatLbl)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboGenderLbl)
                    .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHpLbl)
                    .addComponent(txtHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuat)
                    .addComponent(btnSimpan)
                )
        );
    }// </editor-fold>//GEN-END:initComponents


    private void loadIds() {
        comboId.removeAllItems();
        String sql = "SELECT id_kasir, nama_kasir FROM kasir ORDER BY id_kasir";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comboId.addItem(rs.getString("id_kasir") + " - " + rs.getString("nama_kasir"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat daftar karyawan:\n" + ex.getMessage());
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
            JOptionPane.showMessageDialog(this, "Data karyawan belum tersedia.");
            return;
        }
        String sql = "SELECT * FROM kasir WHERE id_kasir = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    txtNama.setText(rs.getString("nama_kasir"));
                    comboJabatan.setSelectedItem(rs.getString("jabatan"));
                    txtAlamat.setText(rs.getString("alamat_kasir"));
                    comboGender.setSelectedItem(rs.getString("jenis_kelamin"));
                    txtHp.setText(rs.getString("no_hp"));
                    txtPassword.setText("");
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
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong.");
            return;
        }

        String passwordBaru = new String(txtPassword.getPassword()).trim();
        boolean ubahPassword = !passwordBaru.isEmpty();

        String sql = ubahPassword
                ? "UPDATE kasir SET nama_kasir=?, password=?, jabatan=?, alamat_kasir=?, jenis_kelamin=?, no_hp=? WHERE id_kasir=?"
                : "UPDATE kasir SET nama_kasir=?, jabatan=?, alamat_kasir=?, jenis_kelamin=?, no_hp=? WHERE id_kasir=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int idx = 1;
            ps.setString(idx++, txtNama.getText().trim());
            if (ubahPassword) {
                ps.setString(idx++, passwordBaru);
            }
            ps.setString(idx++, (String) comboJabatan.getSelectedItem());
            ps.setString(idx++, txtAlamat.getText().trim());
            ps.setString(idx++, (String) comboGender.getSelectedItem());
            ps.setString(idx++, txtHp.getText().trim());
            ps.setString(idx, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data karyawan berhasil diperbarui.");
            txtPassword.setText("");
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
    private javax.swing.JLabel txtPasswordLbl;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JLabel comboJabatanLbl;
    private javax.swing.JComboBox<String> comboJabatan;
    private javax.swing.JLabel txtAlamatLbl;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JLabel comboGenderLbl;
    private javax.swing.JComboBox<String> comboGender;
    private javax.swing.JLabel txtHpLbl;
    private javax.swing.JTextField txtHp;
    private javax.swing.JButton btnMuat;
    private javax.swing.JButton btnSimpan;
    // End of variables declaration//GEN-END:variables
}
