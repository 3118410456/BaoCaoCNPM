-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2020 at 01:06 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlsieuthi`
--
CREATE DATABASE IF NOT EXISTS `qlsieuthi` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `qlsieuthi`;

-- --------------------------------------------------------

--
-- Table structure for table `cthd`
--

CREATE TABLE `cthd` (
  `MaHD` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `GiaTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctkhuyenmaivip`
--

CREATE TABLE `ctkhuyenmaivip` (
  `MaKM` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctpn`
--

CREATE TABLE `ctpn` (
  `MaPN` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenSP` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonViTinh` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `GiaNhap` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctpx`
--

CREATE TABLE `ctpx` (
  `MaPX` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doanhsokhachhang`
--

CREATE TABLE `doanhsokhachhang` (
  `MaDS` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Nam` year(4) NOT NULL,
  `DSQuy1` int(11) DEFAULT 0,
  `DSQuy2` int(11) DEFAULT 0,
  `DSQuy3` int(11) DEFAULT 0,
  `DSQuy4` int(11) DEFAULT 0,
  `DSNam` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doanhsonhanvien`
--

CREATE TABLE `doanhsonhanvien` (
  `MaDS` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Nam` year(4) NOT NULL,
  `DSQ1` int(11) DEFAULT 0,
  `DSQ2` int(11) DEFAULT 0,
  `DSQ3` int(11) DEFAULT 0,
  `DSQ4` int(11) DEFAULT 0,
  `DSNam` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doanhsonhanvien`
--

INSERT INTO `doanhsonhanvien` (`MaDS`, `MaNV`, `TenNV`, `Nam`, `DSQ1`, `DSQ2`, `DSQ3`, `DSQ4`, `DSNam`) VALUES
('ds1', 'nv1', 'hello', 2019, 25, 20, 10, 5, 60),
('ds2', 'nv1', 'hello', 2020, 0, 0, 0, 0, 0),
('ds3', 'nv1', 'hello', 2021, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKM` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp(),
  `TongCong` double NOT NULL,
  `PhanTramKM` float NOT NULL,
  `TongTienTra` double NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `HoTen` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `HoTen`, `SDT`) VALUES
('kh12', 'hello', ''),
('kh2', 'hello', ''),
('KH416954', 'Nguyen Thanh Sang', '0934045700');

-- --------------------------------------------------------

--
-- Table structure for table `kho`
--

CREATE TABLE `kho` (
  `MaKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenCT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayBD` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayKT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GiaTienToiThieu` double NOT NULL,
  `PhanTram` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenCT`, `NgayBD`, `NgayKT`, `GiaTienToiThieu`, `PhanTram`) VALUES
('HFMLH879612', 'Khai trương', '14/06/2020', '15/06/2020', 0, 50);

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmaivip`
--

CREATE TABLE `khuyenmaivip` (
  `MaKM` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenCT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayBatDau` timestamp NOT NULL DEFAULT current_timestamp(),
  `NgayKetThuc` timestamp NOT NULL DEFAULT current_timestamp(),
  `GiaTienToiThieu` double NOT NULL,
  `PhanTram` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`) VALUES
('VN0001', 'Việt Nam');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `HoTen` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NgaySinh` timestamp NULL DEFAULT current_timestamp(),
  `GioiTinh` int(50) DEFAULT NULL,
  `DiaChi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SoDienThoai` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `NgaySinh`, `GioiTinh`, `DiaChi`, `SoDienThoai`, `Email`) VALUES
('nv1', 'hello', '2020-11-18 11:54:42', 0, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `phanloai`
--

CREATE TABLE `phanloai` (
  `MaLoai` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoai` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phanloai`
--

INSERT INTO `phanloai` (`MaLoai`, `TenLoai`) VALUES
('DD', 'Đồ dùng');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayNhap` timestamp NOT NULL DEFAULT current_timestamp(),
  `TongSoLuong` int(11) NOT NULL,
  `TongTien` double NOT NULL,
  `GhiChu` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `MaPX` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayXuat` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `TongSoLuong` int(11) NOT NULL,
  `TongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaLoai` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonViTinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GiaNhap` double NOT NULL,
  `GiaBan` double NOT NULL,
  `MaNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sanphamloi`
--

CREATE TABLE `sanphamloi` (
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` double NOT NULL,
  `MaNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LyDo` text COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `fk2_cthd` (`MaSP`);

--
-- Indexes for table `ctkhuyenmaivip`
--
ALTER TABLE `ctkhuyenmaivip`
  ADD PRIMARY KEY (`MaKM`),
  ADD KEY `fk2_ctkmvip` (`MaKH`);

--
-- Indexes for table `ctpn`
--
ALTER TABLE `ctpn`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `fk2_ctpn` (`MaSP`);

--
-- Indexes for table `ctpx`
--
ALTER TABLE `ctpx`
  ADD PRIMARY KEY (`MaPX`),
  ADD KEY `fk2_ctpx` (`MaSP`);

--
-- Indexes for table `doanhsokhachhang`
--
ALTER TABLE `doanhsokhachhang`
  ADD PRIMARY KEY (`MaDS`),
  ADD UNIQUE KEY `Nam` (`Nam`),
  ADD KEY `fk_dskh` (`MaKH`);

--
-- Indexes for table `doanhsonhanvien`
--
ALTER TABLE `doanhsonhanvien`
  ADD PRIMARY KEY (`MaDS`),
  ADD UNIQUE KEY `Nam` (`Nam`),
  ADD KEY `fk1_dsnv` (`MaNV`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `fk1_hd` (`MaKH`),
  ADD KEY `fk2_hd` (`MaNV`),
  ADD KEY `fk4_hd` (`MaKM`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `kho`
--
ALTER TABLE `kho`
  ADD PRIMARY KEY (`MaKho`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Indexes for table `khuyenmaivip`
--
ALTER TABLE `khuyenmaivip`
  ADD PRIMARY KEY (`MaKM`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `phanloai`
--
ALTER TABLE `phanloai`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `fk1_pn` (`MaNV`),
  ADD KEY `fk2_pn` (`MaNCC`),
  ADD KEY `fk3_pn` (`MaKho`);

--
-- Indexes for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`MaPX`),
  ADD KEY `fk1_px` (`MaNV`),
  ADD KEY `fk2_px` (`MaKho`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `fk1_sp` (`MaLoai`),
  ADD KEY `fk2_sp` (`MaNCC`);

--
-- Indexes for table `sanphamloi`
--
ALTER TABLE `sanphamloi`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `fk2_spl` (`MaNCC`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`username`),
  ADD KEY `fk_tk` (`MaNV`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cthd`
--
ALTER TABLE `cthd`
  ADD CONSTRAINT `fk1_cthd` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `fk2_cthd` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `ctkhuyenmaivip`
--
ALTER TABLE `ctkhuyenmaivip`
  ADD CONSTRAINT `fk1_ctkmvip` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmaivip` (`MaKM`),
  ADD CONSTRAINT `fk2_ctkmvip` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);

--
-- Constraints for table `ctpn`
--
ALTER TABLE `ctpn`
  ADD CONSTRAINT `fk1_ctpn` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`),
  ADD CONSTRAINT `fk2_ctpn` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `ctpx`
--
ALTER TABLE `ctpx`
  ADD CONSTRAINT `fk1_ctpx` FOREIGN KEY (`MaPX`) REFERENCES `phieuxuat` (`MaPX`),
  ADD CONSTRAINT `fk2_ctpx` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Constraints for table `doanhsokhachhang`
--
ALTER TABLE `doanhsokhachhang`
  ADD CONSTRAINT `fk_dskh` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);

--
-- Constraints for table `doanhsonhanvien`
--
ALTER TABLE `doanhsonhanvien`
  ADD CONSTRAINT `fk1_dsnv` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk1_hd` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `fk2_hd` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk3_hd` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`),
  ADD CONSTRAINT `fk4_hd` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmaivip` (`MaKM`);

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `fk1_pn` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk2_pn` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  ADD CONSTRAINT `fk3_pn` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`);

--
-- Constraints for table `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `fk1_px` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk2_px` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`);

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `fk1_sp` FOREIGN KEY (`MaLoai`) REFERENCES `phanloai` (`MaLoai`),
  ADD CONSTRAINT `fk2_sp` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Constraints for table `sanphamloi`
--
ALTER TABLE `sanphamloi`
  ADD CONSTRAINT `fk1_spl` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  ADD CONSTRAINT `fk2_spl` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `fk_tk` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
