package ui.internal;

import db.DBConnection;
import util.IdGenerator;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Form Tambah Karyawan. Tampilan (initComponents) mengikuti GroupLayout yang
 * dipasangkan dengan file TambahKaryawanInternal.form (bisa diedit lewat
 * GUI Builder / tab Design di NetBeans).
 */
public class TambahKaryawanInternal extends JInternalFrame {



    public TambahKaryawanInternal() {
        initComponents();
        pack();

        lblIdPreview.setText(IdGenerator.next("kasir", "id_kasir", "KSR-", 3));
        btnSimpan.addActionListener(e -> simpan());
    }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tambah Karyawan");

        lblIdPreviewLbl = new javax.swing.JLabel();
        lblIdPreview = new javax.swing.JLabel();
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
        btnSimpan = new javax.swing.JButton();

        lblIdPreviewLbl.setText("ID Kasir (otomatis):");
        lblIdPreview.setText("KSR-000");
        txtNamaLbl.setText("Nama:");
        txtPasswordLbl.setText("Password:");
        comboJabatanLbl.setText("Jabatan:");
        txtAlamatLbl.setText("Alamat:");
        comboGenderLbl.setText("Jenis Kelamin:");
        txtHpLbl.setText("No HP:");
        btnSimpan.setText("Simpan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblIdPreviewLbl)
                        .addComponent(txtNamaLbl)
                        .addComponent(txtPasswordLbl)
                        .addComponent(comboJabatanLbl)
                        .addComponent(txtAlamatLbl)
                        .addComponent(comboGenderLbl)
                        .addComponent(txtHpLbl)
                    )
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblIdPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnSimpan)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdPreviewLbl)
                    .addComponent(lblIdPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnSimpan)
                )
        );
    }// </editor-fold>//GEN-END:initComponents


    private void simpan() {
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama karyawan tidak boleh kosong.");
            return;
        }
        String password = new String(txtPassword.getPassword()).trim();
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password tidak boleh kosong.");
            return;
        }

        String newId = IdGenerator.next("kasir", "id_kasir", "KSR-", 3);
        String sql = "INSERT INTO kasir (id_kasir, nama_kasir, password, jabatan, alamat_kasir, jenis_kelamin, no_hp) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newId);
            ps.setString(2, txtNama.getText().trim());
            ps.setString(3, password);
            ps.setString(4, (String) comboJabatan.getSelectedItem());
            ps.setString(5, txtAlamat.getText().trim());
            ps.setString(6, (String) comboGender.getSelectedItem());
            ps.setString(7, txtHp.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Karyawan berhasil ditambahkan dengan ID: " + newId);

            txtNama.setText("");
            txtPassword.setText("");
            txtAlamat.setText("");
            txtHp.setText("");
            lblIdPreview.setText(IdGenerator.next("kasir", "id_kasir", "KSR-", 3));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data:\n" + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIdPreviewLbl;
    private javax.swing.JLabel lblIdPreview;
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
    private javax.swing.JButton btnSimpan;
    // End of variables declaration//GEN-END:variables
}
