package session;

/**
 * Menyimpan data kasir yang sedang login selama aplikasi berjalan.
 */
public class Session {
    private static String idKasir;
    private static String namaKasir;
    private static String jabatan;

    public static void login(String id, String nama, String jab) {
        idKasir = id;
        namaKasir = nama;
        jabatan = jab;
    }

    public static void logout() {
        idKasir = null;
        namaKasir = null;
        jabatan = null;
    }

    public static String getIdKasir() {
        return idKasir;
    }

    public static String getNamaKasir() {
        return namaKasir;
    }

    public static String getJabatan() {
        return jabatan;
    }

    public static boolean isManager() {
        return jabatan != null && jabatan.trim().equalsIgnoreCase("Manager");
    }
}
