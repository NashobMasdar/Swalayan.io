package ui.internal;

import db.DBConnection;
import util.IdGenerator;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Form Tambah Produk. Tampilan (initComponents) dibuat mengikuti struktur
 * GroupLayout yang dipasangkan dengan file TambahProdukInternal.form,
 * sehingga bisa diedit langsung lewat GUI Builder (tab Design) di NetBeans.
 */
public class TambahProdukInternal extends JInternalFrame {



    public TambahProdukInternal() {
        initComponents();
        pack();

        lblIdPreview.setText(IdGenerator.next("produk", "id_produk", "PRD-", 2));
        btnSimpan.addActionListener(e -> simpan());
    }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tambah Produk");

        lblIdPreviewLbl = new javax.swing.JLabel();
        lblIdPreview = new javax.swing.JLabel();
        txtNamaLbl = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtJenisLbl = new javax.swing.JLabel();
        txtJenis = new javax.swing.JTextField();
        txtStokLbl = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        txtHargaLbl = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();

        lblIdPreviewLbl.setText("ID Produk (otomatis):");
        lblIdPreview.setText("PRD-00");
        txtNamaLbl.setText("Nama Produk:");
        txtJenisLbl.setText("Jenis Produk:");
        txtStokLbl.setText("Stok:");
        txtHargaLbl.setText("Harga:");
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
                        .addComponent(txtJenisLbl)
                        .addComponent(txtStokLbl)
                        .addComponent(txtHargaLbl)
                    )
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblIdPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnSimpan)
                )
        );
    }// </editor-fold>//GEN-END:initComponents


    private void simpan() {
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama produk tidak boleh kosong.");
            return;
        }
        int stok, harga;
        try {
            stok = Integer.parseInt(txtStok.getText().trim());
            harga = Integer.parseInt(txtHarga.getText().trim());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Stok dan Harga harus berupa angka.");
            return;
        }

        String newId = IdGenerator.next("produk", "id_produk", "PRD-", 2);
        String sql = "INSERT INTO produk (id_produk, nama_produk, jenis_produk, stok_produk, harga_produk) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newId);
            ps.setString(2, txtNama.getText().trim());
            ps.setString(3, txtJenis.getText().trim());
            ps.setInt(4, stok);
            ps.setInt(5, harga);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan dengan ID: " + newId);

            txtNama.setText("");
            txtJenis.setText("");
            txtStok.setText("");
            txtHarga.setText("");
            lblIdPreview.setText(IdGenerator.next("produk", "id_produk", "PRD-", 2));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data:\n" + ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIdPreviewLbl;
    private javax.swing.JLabel lblIdPreview;
    private javax.swing.JLabel txtNamaLbl;
    private javax.swing.JTextField txtNama;
    private javax.swing.JLabel txtJenisLbl;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JLabel txtStokLbl;
    private javax.swing.JTextField txtStok;
    private javax.swing.JLabel txtHargaLbl;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JButton btnSimpan;
    // End of variables declaration//GEN-END:variables
}
