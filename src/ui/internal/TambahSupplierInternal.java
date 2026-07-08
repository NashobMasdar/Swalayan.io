package ui.internal;

import db.DBConnection;
import util.IdGenerator;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TambahSupplierInternal extends JInternalFrame {



    public TambahSupplierInternal() {
        initComponents();
        pack();

        lblIdPreview.setText(IdGenerator.next("supplier", "id_supplier", "SPL-", 2));
        btnSimpan.addActionListener(e -> simpan());
    }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tambah Supplier");

        lblIdPreviewLbl = new javax.swing.JLabel();
        lblIdPreview = new javax.swing.JLabel();
        txtNamaLbl = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtAlamatLbl = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        txtHpLbl = new javax.swing.JLabel();
        txtHp = new javax.swing.JTextField();
        txtEmailLbl = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();

        lblIdPreviewLbl.setText("ID Supplier (otomatis):");
        lblIdPreview.setText("SPL-00");
        txtNamaLbl.setText("Nama Supplier:");
        txtAlamatLbl.setText("Alamat:");
        txtHpLbl.setText("No HP:");
        txtEmailLbl.setText("Email:");
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
                        .addComponent(txtAlamatLbl)
                        .addComponent(txtHpLbl)
                        .addComponent(txtEmailLbl)
                    )
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblIdPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnSimpan)
                )
        );
    }// </editor-fold>//GEN-END:initComponents


    private void simpan() {
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama supplier tidak boleh kosong.");
            return;
        }

        String newId = IdGenerator.next("supplier", "id_supplier", "SPL-", 2);
        String sql = "INSERT INTO supplier (id_supplier, nama_supplier, alamat_supplier, no_hp, email_supplier) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newId);
            ps.setString(2, txtNama.getText().trim());
            ps.setString(3, txtAlamat.getText().trim());
            ps.setString(4, txtHp.getText().trim());
            ps.setString(5, txtEmail.getText().trim());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Supplier berhasil ditambahkan dengan ID: " + newId);

            txtNama.setText("");
            txtAlamat.setText("");
            txtHp.setText("");
            txtEmail.setText("");
            lblIdPreview.setText(IdGenerator.next("supplier", "id_supplier", "SPL-", 2));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data:\n" + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIdPreviewLbl;
    private javax.swing.JLabel lblIdPreview;
    private javax.swing.JLabel txtNamaLbl;
    private javax.swing.JTextField txtNama;
    private javax.swing.JLabel txtAlamatLbl;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JLabel txtHpLbl;
    private javax.swing.JTextField txtHp;
    private javax.swing.JLabel txtEmailLbl;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JButton btnSimpan;
    // End of variables declaration//GEN-END:variables
}
