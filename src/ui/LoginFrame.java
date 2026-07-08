package ui;

import db.DBConnection;
import session.Session;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Tampilan login aplikasi. Kasir memilih ID Kasir miliknya lalu memasukkan
 * password yang tersimpan pada kolom `password` tabel `kasir` (setelah kolom
 * `nama_kasir`). Hak akses (Manager/Karyawan) otomatis mengikuti kolom
 * `jabatan` pada tabel kasir.
 *
 * Tampilan (initComponents) mengikuti GroupLayout yang dipasangkan dengan
 * file LoginFrame.form, sehingga bisa diedit lewat GUI Builder di NetBeans.
 */
public class LoginFrame extends JFrame {



    public LoginFrame() {
        initComponents();
        setTitle(getTitle());
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        loadKasir();

        btnRefresh.addActionListener(e -> loadKasir());
        btnLogin.addActionListener(e -> doLogin());
        // Tekan Enter pada field password langsung memicu login
        txtPassword.addActionListener(e -> doLogin());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        comboKasirLbl = new javax.swing.JLabel();
        comboKasir = new javax.swing.JComboBox();
        txtPasswordLbl = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnRefresh = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Swalayan.io");
        setResizable(false);

        jLabelTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("SWALAYAN.IO - LOGIN");

        comboKasirLbl.setText("ID Kasir:");

        txtPasswordLbl.setText("Password:");

        btnRefresh.setText("Refresh Daftar");

        btnLogin.setText("Login");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPasswordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboKasirLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboKasir, 0, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRefresh)
                                .addGap(0, 0, 0)
                                .addComponent(btnLogin))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabelTitle)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboKasirLbl)
                    .addComponent(comboKasir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswordLbl)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(btnLogin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void loadKasir() {
        comboKasir.removeAllItems();
        String sql = "SELECT id_kasir, nama_kasir FROM kasir ORDER BY id_kasir";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                comboKasir.addItem(rs.getString("id_kasir") + " - " + rs.getString("nama_kasir"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal terhubung ke database:\n" + ex.getMessage()
                            + "\n\nPastikan MySQL/MariaDB aktif, database 'sistem_swalayan' sudah di-import,\n"
                            + "dan driver JDBC MySQL sudah ditambahkan ke Libraries project.",
                    "Error Koneksi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void doLogin() {
        String selected = (String) comboKasir.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Tidak ada data kasir yang tersedia di database.");
            return;
        }
        String idKasir = selected.split(" - ")[0].trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password tidak boleh kosong.");
            return;
        }

        String sql = "SELECT id_kasir, nama_kasir, jabatan, password FROM kasir WHERE id_kasir = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idKasir);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String passwordDb = rs.getString("password");
                    if (passwordDb != null && passwordDb.equals(password)) {
                        Session.login(rs.getString("id_kasir"), rs.getString("nama_kasir"), rs.getString("jabatan"));
                        JOptionPane.showMessageDialog(this, "Selamat datang, " + rs.getString("nama_kasir")
                                + " (" + rs.getString("jabatan") + ")");
                        new MenuUtamaFrame().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Password salah.");
                        txtPassword.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Data kasir tidak ditemukan.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal login:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox comboKasir;
    private javax.swing.JLabel comboKasirLbl;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JLabel txtPasswordLbl;
    // End of variables declaration//GEN-END:variables
}
