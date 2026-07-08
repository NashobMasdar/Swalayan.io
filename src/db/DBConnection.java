package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas untuk menangani koneksi ke database sistem_swalayan.
 * Ubah URL, USER, dan PASS sesuai konfigurasi MySQL/MariaDB di komputer Anda.
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sistem_swalayan?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // isi password MySQL/MariaDB Anda jika ada

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
