package util;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility untuk membuat ID baru secara otomatis, contoh: PRD-07, KSR-006, dst.
 * Karena tabel di database tidak menggunakan AUTO_INCREMENT (memakai varchar),
 * ID baru dibuat dengan mencari nomor terbesar yang sudah ada lalu menambah 1.
 */
public class IdGenerator {

    /**
     * Mengambil nomor urut terbesar yang sudah tersimpan di kolom id pada tabel.
     * Mengembalikan 0 jika tabel masih kosong.
     */
    public static int currentMax(String table, String column) {
        String sql = "SELECT " + column + " FROM " + table + " ORDER BY " + column + " DESC LIMIT 1";
        int max = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String id = rs.getString(1);
                String numPart = id.replaceAll("[^0-9]", "");
                if (!numPart.isEmpty()) {
                    max = Integer.parseInt(numPart);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max;
    }

    /**
     * Membuat ID baru dengan format prefix + nomor urut berpadding.
     * Contoh: next("produk", "id_produk", "PRD-", 2) -> "PRD-07"
     */
    public static String next(String table, String column, String prefix, int digits) {
        int max = currentMax(table, column);
        return format(prefix, digits, max + 1);
    }

    public static String format(String prefix, int digits, int number) {
        return prefix + String.format("%0" + digits + "d", number);
    }
}
