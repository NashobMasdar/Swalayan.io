package ui.internal;

import db.DBConnection;
import session.Session;
import util.IdGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * JInternalFrame untuk melayani pelanggan: pilih/daftarkan pelanggan,
 * tambahkan produk ke keranjang, lalu checkout (tersimpan ke tabel
 * pembayaran & transaksi, dan mengurangi stok produk).
 * Dapat diakses oleh Manager maupun Karyawan.
 *
 * Tampilan (initComponents) mengikuti GroupLayout yang dipasangkan dengan
 * file LayaniPelangganInternal.form.
 */
public class LayaniPelangganInternal extends JInternalFrame {


    private DefaultTableModel cartModel;
    private final Map<String, Integer> hargaMap = new HashMap<>();
    private final Map<String, String> namaProdukMap = new HashMap<>();
    private String currentIdPelanggan = null;


    public LayaniPelangganInternal() {
        initComponents();
        pack();

        loadProduk();

        btnCariPelanggan.addActionListener(e -> cariPelanggan());
        btnPelangganBaru.addActionListener(e -> pelangganBaru());
        btnTambahKeranjang.addActionListener(e -> tambahKeKeranjang());
        btnHapusItem.addActionListener(e -> hapusItemKeranjang());
        btnCheckout.addActionListener(e -> checkout());
    }

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Layani Pelanggan");

        lblIdPelanggan = new javax.swing.JLabel();
        txtIdPelanggan = new javax.swing.JTextField();
        btnCariPelanggan = new javax.swing.JButton();
        lblNamaPelanggan = new javax.swing.JLabel();
        txtNamaPelanggan = new javax.swing.JTextField();
        btnPelangganBaru = new javax.swing.JButton();

        lblProduk = new javax.swing.JLabel();
        comboProduk = new javax.swing.JComboBox<>();
        lblQty = new javax.swing.JLabel();
        spinnerQty = new javax.swing.JSpinner(new javax.swing.SpinnerNumberModel(1, 1, 9999, 1));
        btnTambahKeranjang = new javax.swing.JButton();

        cartScrollPane = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();

        btnHapusItem = new javax.swing.JButton();
        lblMetode = new javax.swing.JLabel();
        comboMetode = new javax.swing.JComboBox<>(new String[]{"Tunai", "Debit", "Kartu Kredit", "QRIS"});
        lblTotal = new javax.swing.JLabel();
        btnCheckout = new javax.swing.JButton();

        lblIdPelanggan.setText("ID Pelanggan:");
        btnCariPelanggan.setText("Cari");
        lblNamaPelanggan.setText("Nama:");
        txtNamaPelanggan.setEditable(false);
        btnPelangganBaru.setText("Pelanggan Baru");

        lblProduk.setText("Produk:");
        lblQty.setText("Qty:");
        btnTambahKeranjang.setText("Tambah ke Keranjang");

        cartModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Produk", "Nama Produk", "Harga", "Qty", "Subtotal"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable.setModel(cartModel);
        cartScrollPane.setViewportView(cartTable);

        btnHapusItem.setText("Hapus Item Terpilih");
        lblMetode.setText("   Metode Pembayaran:");
        lblTotal.setText("   Total: Rp 0   ");
        lblTotal.setFont(lblTotal.getFont().deriveFont(java.awt.Font.BOLD, 13f));
        btnCheckout.setText("Checkout");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblIdPelanggan)
                    .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPelanggan)
                    .addComponent(lblNamaPelanggan)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPelangganBaru)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblProduk)
                    .addComponent(comboProduk, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblQty)
                    .addComponent(spinnerQty, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTambahKeranjang)
                )
                .addComponent(cartScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnHapusItem)
                    .addComponent(lblMetode)
                    .addComponent(comboMetode, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal)
                    .addComponent(btnCheckout)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdPelanggan)
                    .addComponent(txtIdPelanggan)
                    .addComponent(btnCariPelanggan)
                    .addComponent(lblNamaPelanggan)
                    .addComponent(txtNamaPelanggan)
                    .addComponent(btnPelangganBaru)
                )
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProduk)
                    .addComponent(comboProduk)
                    .addComponent(lblQty)
                    .addComponent(spinnerQty)
                    .addComponent(btnTambahKeranjang)
                )
                .addComponent(cartScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHapusItem)
                    .addComponent(lblMetode)
                    .addComponent(comboMetode)
                    .addComponent(lblTotal)
                    .addComponent(btnCheckout)
                )
        );
    }// </editor-fold>//GEN-END:initComponents


    private void loadProduk() {
        comboProduk.removeAllItems();
        hargaMap.clear();
        namaProdukMap.clear();
        String sql = "SELECT id_produk, nama_produk, harga_produk, stok_produk FROM produk ORDER BY id_produk";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id_produk");
                hargaMap.put(id, rs.getInt("harga_produk"));
                namaProdukMap.put(id, rs.getString("nama_produk"));
                comboProduk.addItem(id + " - " + rs.getString("nama_produk") + " (Stok: " + rs.getInt("stok_produk") + ")");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data produk:\n" + ex.getMessage());
        }
    }

    private void cariPelanggan() {
        String id = txtIdPelanggan.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan ID Pelanggan terlebih dahulu.");
            return;
        }
        String sql = "SELECT nama_pelanggan FROM pelanggan WHERE id_pelanggan = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    txtNamaPelanggan.setText(rs.getString("nama_pelanggan"));
                    currentIdPelanggan = id;
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Pelanggan tidak ditemukan. Gunakan tombol 'Pelanggan Baru' untuk mendaftarkan.");
                    txtNamaPelanggan.setText("");
                    currentIdPelanggan = null;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mencari pelanggan:\n" + ex.getMessage());
        }
    }

    private void pelangganBaru() {
        JTextField nama = new JTextField();
        JTextField alamat = new JTextField();
        JComboBox<String> gender = new JComboBox<>(new String[]{"L", "P"});
        JTextField hp = new JTextField();
        JPanel panel = new JPanel(new java.awt.GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Nama:")); panel.add(nama);
        panel.add(new JLabel("Alamat:")); panel.add(alamat);
        panel.add(new JLabel("Jenis Kelamin:")); panel.add(gender);
        panel.add(new JLabel("No HP:")); panel.add(hp);

        int result = JOptionPane.showConfirmDialog(this, panel, "Daftarkan Pelanggan Baru",
                JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        if (nama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pelanggan tidak boleh kosong.");
            return;
        }

        String newId = IdGenerator.next("pelanggan", "id_pelanggan", "PLG-", 3);
        String sql = "INSERT INTO pelanggan (id_pelanggan, nama_pelanggan, alamat_pelanggan, jenis_kelamin, no_hp) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newId);
            ps.setString(2, nama.getText().trim());
            ps.setString(3, alamat.getText().trim());
            ps.setString(4, (String) gender.getSelectedItem());
            ps.setString(5, hp.getText().trim());
            ps.executeUpdate();

            currentIdPelanggan = newId;
            txtIdPelanggan.setText(newId);
            txtNamaPelanggan.setText(nama.getText().trim());
            JOptionPane.showMessageDialog(this, "Pelanggan baru terdaftar dengan ID: " + newId);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan pelanggan baru:\n" + ex.getMessage());
        }
    }

    private void tambahKeKeranjang() {
        String selected = (String) comboProduk.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Data produk belum tersedia.");
            return;
        }
        String idProduk = selected.split(" - ")[0].trim();
        int qty = (Integer) spinnerQty.getValue();
        int harga = hargaMap.getOrDefault(idProduk, 0);
        String nama = namaProdukMap.getOrDefault(idProduk, "");
        int subtotal = harga * qty;
        cartModel.addRow(new Object[]{idProduk, nama, harga, qty, subtotal});
        updateTotal();
    }

    private void hapusItemKeranjang() {
        int row = cartTable.getSelectedRow();
        if (row >= 0) {
            cartModel.removeRow(row);
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Pilih item pada keranjang yang ingin dihapus.");
        }
    }

    private void updateTotal() {
        int total = 0;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            total += (Integer) cartModel.getValueAt(i, 4);
        }
        lblTotal.setText("   Total: Rp " + total + "   ");
    }

    private void checkout() {
        if (currentIdPelanggan == null) {
            JOptionPane.showMessageDialog(this, "Pilih atau daftarkan pelanggan terlebih dahulu.");
            return;
        }
        if (cartModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Keranjang belanja masih kosong.");
            return;
        }

        int total = 0;
        for (int i = 0; i < cartModel.getRowCount(); i++) {
            total += (Integer) cartModel.getValueAt(i, 4);
        }
        String metode = (String) comboMetode.getSelectedItem();
        String idPembayaran = IdGenerator.next("pembayaran", "id_pembayaran", "PBY-", 3);
        String today = LocalDate.now().toString();

        // Ambil nomor urut awal id_transaksi sekali di luar transaksi DB (autocommit=false)
        // agar tidak terjadi ID duplikat saat menyimpan beberapa item sekaligus.
        int txCounter = IdGenerator.currentMax("transaksi", "id_transaksi");

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sqlPembayaran = "INSERT INTO pembayaran (id_pembayaran, tgl_pembayaran, metode_pembayaran, status_pembayaran, total_pembayaran) VALUES (?,?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlPembayaran)) {
                ps.setString(1, idPembayaran);
                ps.setString(2, today);
                ps.setString(3, metode);
                ps.setString(4, "Lunas");
                ps.setInt(5, total);
                ps.executeUpdate();
            }

            for (int i = 0; i < cartModel.getRowCount(); i++) {
                String idProduk = (String) cartModel.getValueAt(i, 0);
                int qty = (Integer) cartModel.getValueAt(i, 3);
                int subtotal = (Integer) cartModel.getValueAt(i, 4);

                txCounter++;
                String idTransaksi = IdGenerator.format("TRX-", 3, txCounter);

                String sqlTransaksi = "INSERT INTO transaksi (id_transaksi, tgl_transaksi, total_transaksi, id_pembayaran, id_pelanggan, id_kasir, id_produk) VALUES (?,?,?,?,?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sqlTransaksi)) {
                    ps.setString(1, idTransaksi);
                    ps.setString(2, today);
                    ps.setInt(3, subtotal);
                    ps.setString(4, idPembayaran);
                    ps.setString(5, currentIdPelanggan);
                    ps.setString(6, Session.getIdKasir());
                    ps.setString(7, idProduk);
                    ps.executeUpdate();
                }

                String sqlStok = "UPDATE produk SET stok_produk = stok_produk - ? WHERE id_produk = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlStok)) {
                    ps.setInt(1, qty);
                    ps.setString(2, idProduk);
                    ps.executeUpdate();
                }
            }

            conn.commit();
            JOptionPane.showMessageDialog(this, "Transaksi berhasil disimpan!\nID Pembayaran: " + idPembayaran
                    + "\nTotal: Rp " + total);

            cartModel.setRowCount(0);
            updateTotal();
            txtIdPelanggan.setText("");
            txtNamaPelanggan.setText("");
            currentIdPelanggan = null;
            loadProduk();

        } catch (SQLException ex) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ignored) {
            }
            JOptionPane.showMessageDialog(this, "Gagal melakukan transaksi:\n" + ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException ignored) {
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIdPelanggan;
    private javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JButton btnCariPelanggan;
    private javax.swing.JLabel lblNamaPelanggan;
    private javax.swing.JTextField txtNamaPelanggan;
    private javax.swing.JButton btnPelangganBaru;
    private javax.swing.JLabel lblProduk;
    private javax.swing.JComboBox<String> comboProduk;
    private javax.swing.JLabel lblQty;
    private javax.swing.JSpinner spinnerQty;
    private javax.swing.JButton btnTambahKeranjang;
    private javax.swing.JScrollPane cartScrollPane;
    private javax.swing.JTable cartTable;
    private javax.swing.JButton btnHapusItem;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JComboBox<String> comboMetode;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JButton btnCheckout;
    // End of variables declaration//GEN-END:variables
}
