package ui;

import session.Session;
import ui.internal.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Tampilan Menu Utama. JMenuBar dan seluruh JMenu/JMenuItem dideklarasikan
 * langsung di initComponents() (dan file MenuUtamaFrame.form) sehingga
 * terlihat & bisa diedit di tab Design NetBeans. Untuk Karyawan, menu
 * Tambahkan/Edit/Hapus disembunyikan saat program berjalan (lihat konstruktor).
 */
public class MenuUtamaFrame extends JFrame {

    public MenuUtamaFrame() {
        initComponents();
        setTitle(getTitle() + "  |  Login sebagai: "
                + Session.getNamaKasir() + " (" + Session.getJabatan() + ")");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Wiring aksi menu (di luar guarded block, bebas diedit)
        itemLayaniPelanggan.addActionListener(e -> showInternal(new LayaniPelangganInternal()));
        itemGantiKasir.addActionListener(e -> gantiKasir());
        itemKeluar.addActionListener(e -> keluar());

        itemCariProduk.addActionListener(e -> showInternal(new CariProdukInternal()));
        itemCariSupplier.addActionListener(e -> showInternal(new CariSupplierInternal()));
        itemCariGudang.addActionListener(e -> showInternal(new CariGudangInternal()));
        itemCariRiwayat.addActionListener(e -> showInternal(new CariRiwayatInternal()));
        itemCariPemasokan.addActionListener(e -> showInternal(new CariPemasokanInternal()));

        itemTambahProduk.addActionListener(e -> showInternal(new TambahProdukInternal()));
        itemTambahKaryawan.addActionListener(e -> showInternal(new TambahKaryawanInternal()));
        itemTambahSupplier.addActionListener(e -> showInternal(new TambahSupplierInternal()));

        itemEditProduk.addActionListener(e -> showInternal(new EditProdukInternal()));
        itemEditKaryawan.addActionListener(e -> showInternal(new EditKaryawanInternal()));
        itemEditSupplier.addActionListener(e -> showInternal(new EditSupplierInternal()));

        itemHapusProduk.addActionListener(e -> showInternal(new HapusProdukInternal()));
        itemHapusKaryawan.addActionListener(e -> showInternal(new HapusKaryawanInternal()));
        itemHapusSupplier.addActionListener(e -> showInternal(new HapusSupplierInternal()));

        // Karyawan tidak boleh melihat menu Tambahkan/Edit/Hapus
        if (!Session.isManager()) {
            menuBar.remove(menuTambah);
            menuBar.remove(menuEdit);
            menuBar.remove(menuHapus);
            menuBar.revalidate();
            menuBar.repaint();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                keluar();
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        gambarLobi = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuUtama = new javax.swing.JMenu();
        itemLayaniPelanggan = new javax.swing.JMenuItem();
        itemGantiKasir = new javax.swing.JMenuItem();
        sepUtama = new javax.swing.JPopupMenu.Separator();
        itemKeluar = new javax.swing.JMenuItem();
        menuCari = new javax.swing.JMenu();
        itemCariProduk = new javax.swing.JMenuItem();
        itemCariSupplier = new javax.swing.JMenuItem();
        itemCariGudang = new javax.swing.JMenuItem();
        itemCariRiwayat = new javax.swing.JMenuItem();
        itemCariPemasokan = new javax.swing.JMenuItem();
        menuTambah = new javax.swing.JMenu();
        itemTambahProduk = new javax.swing.JMenuItem();
        itemTambahKaryawan = new javax.swing.JMenuItem();
        itemTambahSupplier = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        itemEditProduk = new javax.swing.JMenuItem();
        itemEditKaryawan = new javax.swing.JMenuItem();
        itemEditSupplier = new javax.swing.JMenuItem();
        menuHapus = new javax.swing.JMenu();
        itemHapusProduk = new javax.swing.JMenuItem();
        itemHapusKaryawan = new javax.swing.JMenuItem();
        itemHapusSupplier = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menu Utama - Swalayan.io");

        gambarLobi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/LobbyMenu.png"))); // NOI18N
        gambarLobi.setText("jLabel2");
        desktopPane.add(gambarLobi);
        gambarLobi.setBounds(0, 0, 1100, 680);

        menuUtama.setText("Utama");

        itemLayaniPelanggan.setText("Layani Pelanggan");
        menuUtama.add(itemLayaniPelanggan);

        itemGantiKasir.setText("Ganti Kasir");
        menuUtama.add(itemGantiKasir);
        menuUtama.add(sepUtama);

        itemKeluar.setText("Keluar");
        menuUtama.add(itemKeluar);

        menuBar.add(menuUtama);

        menuCari.setText("Cari");

        itemCariProduk.setText("Produk");
        menuCari.add(itemCariProduk);

        itemCariSupplier.setText("Supplier");
        menuCari.add(itemCariSupplier);

        itemCariGudang.setText("Gudang");
        menuCari.add(itemCariGudang);

        itemCariRiwayat.setText("Riwayat Pelanggan");
        menuCari.add(itemCariRiwayat);

        itemCariPemasokan.setText("Pemasokan");
        menuCari.add(itemCariPemasokan);

        menuBar.add(menuCari);

        menuTambah.setText("Tambahkan");

        itemTambahProduk.setText("Produk");
        menuTambah.add(itemTambahProduk);

        itemTambahKaryawan.setText("Karyawan");
        menuTambah.add(itemTambahKaryawan);

        itemTambahSupplier.setText("Supplier");
        itemTambahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTambahSupplierActionPerformed(evt);
            }
        });
        menuTambah.add(itemTambahSupplier);

        menuBar.add(menuTambah);

        menuEdit.setText("Ubah");

        itemEditProduk.setText("Produk");
        menuEdit.add(itemEditProduk);

        itemEditKaryawan.setText("Karyawan");
        itemEditKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEditKaryawanActionPerformed(evt);
            }
        });
        menuEdit.add(itemEditKaryawan);

        itemEditSupplier.setText("Supplier");
        menuEdit.add(itemEditSupplier);

        menuBar.add(menuEdit);

        menuHapus.setText("Hapus");

        itemHapusProduk.setText("Produk");
        menuHapus.add(itemHapusProduk);

        itemHapusKaryawan.setText("Karyawan");
        itemHapusKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHapusKaryawanActionPerformed(evt);
            }
        });
        menuHapus.add(itemHapusKaryawan);

        itemHapusSupplier.setText("Supplier");
        itemHapusSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemHapusSupplierActionPerformed(evt);
            }
        });
        menuHapus.add(itemHapusSupplier);

        menuBar.add(menuHapus);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itemTambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTambahSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemTambahSupplierActionPerformed

    private void itemEditKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEditKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemEditKaryawanActionPerformed

    private void itemHapusKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHapusKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemHapusKaryawanActionPerformed

    private void itemHapusSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemHapusSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemHapusSupplierActionPerformed

    /** Menampilkan JInternalFrame pada desktopPane. Jika sudah terbuka, cukup difokuskan. */
    private void showInternal(JInternalFrame frame) {
        for (JInternalFrame existing : desktopPane.getAllFrames()) {
            if (existing.getClass() == frame.getClass()) {
                existing.toFront();
                try {
                    existing.setSelected(true);
                } catch (Exception ignored) {
                }
                return;
            }
        }
        desktopPane.add(frame);
        frame.setLocation(20 + (desktopPane.getAllFrames().length * 15) % 200, 20);
        frame.setVisible(true);
        frame.toFront();
        try {
            frame.setSelected(true);
        } catch (Exception ignored) {
        }
    }

    private void gantiKasir() {
        int confirm = JOptionPane.showConfirmDialog(this, "Ganti kasir/pengguna yang login sekarang?",
                "Ganti Kasir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Session.logout();
            new LoginFrame().setVisible(true);
            dispose();
        }
    }

    private void keluar() {
        int confirm = JOptionPane.showConfirmDialog(this, "Keluar dari program?",
                "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JLabel gambarLobi;
    private javax.swing.JMenuItem itemCariGudang;
    private javax.swing.JMenuItem itemCariPemasokan;
    private javax.swing.JMenuItem itemCariProduk;
    private javax.swing.JMenuItem itemCariRiwayat;
    private javax.swing.JMenuItem itemCariSupplier;
    private javax.swing.JMenuItem itemEditKaryawan;
    private javax.swing.JMenuItem itemEditProduk;
    private javax.swing.JMenuItem itemEditSupplier;
    private javax.swing.JMenuItem itemGantiKasir;
    private javax.swing.JMenuItem itemHapusKaryawan;
    private javax.swing.JMenuItem itemHapusProduk;
    private javax.swing.JMenuItem itemHapusSupplier;
    private javax.swing.JMenuItem itemKeluar;
    private javax.swing.JMenuItem itemLayaniPelanggan;
    private javax.swing.JMenuItem itemTambahKaryawan;
    private javax.swing.JMenuItem itemTambahProduk;
    private javax.swing.JMenuItem itemTambahSupplier;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCari;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuHapus;
    private javax.swing.JMenu menuTambah;
    private javax.swing.JMenu menuUtama;
    private javax.swing.JPopupMenu.Separator sepUtama;
    // End of variables declaration//GEN-END:variables
}
