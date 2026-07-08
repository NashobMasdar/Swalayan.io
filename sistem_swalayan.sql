-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 08 Jul 2026 pada 03.18
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistem_swalayan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `gudang`
--

CREATE TABLE `gudang` (
  `id_gudang` varchar(10) NOT NULL,
  `kapasitas_gudang` int(11) DEFAULT NULL,
  `lokasi_gudang` varchar(100) DEFAULT NULL,
  `pengelola_gudang` varchar(100) DEFAULT NULL,
  `status_gudang` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `gudang`
--

INSERT INTO `gudang` (`id_gudang`, `kapasitas_gudang`, `lokasi_gudang`, `pengelola_gudang`, `status_gudang`) VALUES
('GDG-01', 5000, 'Lantai Atas Belakang', 'Budi Santoso', 'Tersedia'),
('GDG-02', 2000, 'Ruang Pendingin (Chiller)', 'Siti Aminah', 'Tersedia'),
('GDG-03', 4000, 'Samping Area Parkir', 'Rian Hidayat', 'Hampir Penuh');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kasir`
--

CREATE TABLE `kasir` (
  `id_kasir` varchar(10) NOT NULL,
  `nama_kasir` varchar(100) NOT NULL,
  `password` varchar(16) NOT NULL,
  `jabatan` varchar(10) NOT NULL,
  `alamat_kasir` text DEFAULT NULL,
  `jenis_kelamin` enum('L','P') DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kasir`
--

INSERT INTO `kasir` (`id_kasir`, `nama_kasir`, `password`, `jabatan`, `alamat_kasir`, `jenis_kelamin`, `no_hp`) VALUES
('KSR-000', 'Agung Wijaya', 'AdminYangTahu', 'Manager', 'Jl. Jenderal Sudirman No. 1', 'L', '081122334455'),
('KSR-001', 'Rina Permata', 'TanyaKasir', 'Karyawan', 'Jl. Mawar No. 12', 'P', '081234567801'),
('KSR-002', 'Doni Setiawan', 'TanyaKasir', 'Karyawan', 'Jl. Melati No. 45', 'L', '081234567802'),
('KSR-003', 'Siti Rahma', 'TanyaKasir', 'Karyawan', 'Jl. Kenanga No. 8', 'P', '081234567803'),
('KSR-004', 'Andi Wijaya', 'TanyaKasir', 'Karyawan', 'Jl. Dahlia No. 19', 'L', '081234567804'),
('KSR-005', 'Dewi Lestari', 'TanyaKasir', 'Karyawan', 'Jl. Anggrek No. 3', 'P', '081234567805');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(100) NOT NULL,
  `alamat_pelanggan` text DEFAULT NULL,
  `jenis_kelamin` enum('L','P') DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `alamat_pelanggan`, `jenis_kelamin`, `no_hp`) VALUES
('PLG-001', 'Santoso', 'Jl. Kalimantan no. 19', 'L', '081223344455'),
('PLG-002', 'Harianto', 'Jl. Bali', 'L', '089876543211');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemasokan`
--

CREATE TABLE `pemasokan` (
  `id_pemasokan` varchar(10) NOT NULL,
  `tgl_pemasokan` date NOT NULL,
  `jumlah_pemasokan` int(11) DEFAULT NULL,
  `harga_pemasokan` int(11) DEFAULT NULL,
  `status_pemasokan` varchar(50) DEFAULT NULL,
  `id_gudang` varchar(10) DEFAULT NULL,
  `id_produk` varchar(10) DEFAULT NULL,
  `id_supplier` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pemasokan`
--

INSERT INTO `pemasokan` (`id_pemasokan`, `tgl_pemasokan`, `jumlah_pemasokan`, `harga_pemasokan`, `status_pemasokan`, `id_gudang`, `id_produk`, `id_supplier`) VALUES
('PMS-001', '2026-06-15', 50, 3250000, 'Selesai', 'GDG-01', 'PRD-01', 'SPL-01'),
('PMS-002', '2026-06-16', 100, 3000000, 'Selesai', 'GDG-03', 'PRD-02', 'SPL-03'),
('PMS-003', '2026-06-18', 120, 1920000, 'Selesai', 'GDG-02', 'PRD-03', 'SPL-02'),
('PMS-004', '2026-06-20', 60, 1100000, 'Selesai', 'GDG-01', 'PRD-04', 'SPL-03'),
('PMS-005', '2026-06-29', 30, 3150000, 'Dalam Proses', 'GDG-03', 'PRD-05', 'SPL-01');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` varchar(10) NOT NULL,
  `tgl_pembayaran` date NOT NULL,
  `metode_pembayaran` varchar(50) DEFAULT NULL,
  `status_pembayaran` varchar(30) DEFAULT NULL,
  `total_pembayaran` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `tgl_pembayaran`, `metode_pembayaran`, `status_pembayaran`, `total_pembayaran`) VALUES
('PBY-001', '2026-07-04', 'Tunai', 'Lunas', 558500),
('PBY-002', '2026-07-05', 'QRIS', 'Lunas', 390000),
('PBY-003', '2026-07-05', 'Tunai', 'Lunas', 318000),
('PBY-004', '2026-07-08', 'Tunai', 'Lunas', 382000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `produk`
--

CREATE TABLE `produk` (
  `id_produk` varchar(10) NOT NULL,
  `nama_produk` varchar(100) NOT NULL,
  `jenis_produk` varchar(50) DEFAULT NULL,
  `stok_produk` int(11) DEFAULT NULL,
  `harga_produk` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `jenis_produk`, `stok_produk`, `harga_produk`) VALUES
('PRD-01', 'Beras Premium 5kg', 'Sembako', 148, 85000),
('PRD-02', 'Minyak Goreng 2L', 'Sembako', 300, 34000),
('PRD-03', 'Susu UHT Full Cream 1L', 'Minuman/Dairy', 197, 18500),
('PRD-04', 'Sabun Mandi Cair 450ml', 'Personal Care', 116, 22000),
('PRD-05', 'Mie Instan Rasa Soto (Dus)', 'Makanan Instan', 69, 115000),
('PRD-06', 'Gula Pasir 1 Kg', 'Sembako', 98, 45000),
('PRD-07', 'Garam Dapur Beryodium 250g', 'Bumbu Dapur', 150, 3500);

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(10) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `alamat_supplier` text DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `email_supplier` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `alamat_supplier`, `no_hp`, `email_supplier`) VALUES
('SPL-01', 'PT Sumber Indah Makmur', 'Jl. Industri No. 12, Surabaya', '081234567890', 'sales@sumberindah.com'),
('SPL-02', 'CV Segar Utama', 'Kawasan Agrobisnis Blok C, Malang', '081399887766', 'info@segarutama.co.id'),
('SPL-03', 'PT Distribusi Nusantara', 'Jl. Gatot Subroto Kav. 4, Jakarta', '082155443322', 'nusantara.dist@gmail.com'),
('SPL-04', 'PT Boga Rasa Semesta', 'Kawasan Industri Candi Blok B, Semarang', '081177665544', 'info@bogarasa.co.id');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(10) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `total_transaksi` int(11) DEFAULT NULL,
  `id_pembayaran` varchar(10) DEFAULT NULL,
  `id_pelanggan` varchar(10) DEFAULT NULL,
  `id_kasir` varchar(10) DEFAULT NULL,
  `id_produk` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tgl_transaksi`, `total_transaksi`, `id_pembayaran`, `id_pelanggan`, `id_kasir`, `id_produk`) VALUES
('TRX-001', '2026-07-04', 150000, 'PBY-001', 'PLG-001', 'KSR-000', 'PRD-01'),
('TRX-002', '2026-07-04', 18500, 'PBY-001', 'PLG-001', 'KSR-000', 'PRD-03'),
('TRX-003', '2026-07-04', 45000, 'PBY-001', 'PLG-001', 'KSR-000', 'PRD-06'),
('TRX-004', '2026-07-04', 345000, 'PBY-001', 'PLG-001', 'KSR-000', 'PRD-05'),
('TRX-005', '2026-07-05', 345000, 'PBY-002', 'PLG-001', 'KSR-000', 'PRD-05'),
('TRX-006', '2026-07-05', 45000, 'PBY-002', 'PLG-001', 'KSR-000', 'PRD-06'),
('TRX-007', '2026-07-05', 88000, 'PBY-003', 'PLG-001', 'KSR-000', 'PRD-04'),
('TRX-008', '2026-07-05', 230000, 'PBY-003', 'PLG-001', 'KSR-000', 'PRD-05'),
('TRX-009', '2026-07-08', 345000, 'PBY-004', 'PLG-002', 'KSR-000', 'PRD-05'),
('TRX-010', '2026-07-08', 37000, 'PBY-004', 'PLG-002', 'KSR-000', 'PRD-03');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `gudang`
--
ALTER TABLE `gudang`
  ADD PRIMARY KEY (`id_gudang`);

--
-- Indeks untuk tabel `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id_kasir`);

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `pemasokan`
--
ALTER TABLE `pemasokan`
  ADD PRIMARY KEY (`id_pemasokan`),
  ADD KEY `id_gudang` (`id_gudang`),
  ADD KEY `id_produk` (`id_produk`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indeks untuk tabel `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`);

--
-- Indeks untuk tabel `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_pembayaran` (`id_pembayaran`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_kasir` (`id_kasir`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pemasokan`
--
ALTER TABLE `pemasokan`
  ADD CONSTRAINT `pemasokan_ibfk_1` FOREIGN KEY (`id_gudang`) REFERENCES `gudang` (`id_gudang`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `pemasokan_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `pemasokan_ibfk_3` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_pembayaran`) REFERENCES `pembayaran` (`id_pembayaran`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_4` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
