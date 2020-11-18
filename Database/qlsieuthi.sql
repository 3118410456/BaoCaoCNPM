-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 18, 2020 lúc 06:05 AM
-- Phiên bản máy phục vụ: 10.4.14-MariaDB
-- Phiên bản PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlsieuthi`
--
CREATE DATABASE IF NOT EXISTS `qlsieuthi` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `qlsieuthi`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cthd`
--

CREATE TABLE `cthd` (
  `MaHD` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `GiaTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctkhuyenmaivip`
--

CREATE TABLE `ctkhuyenmaivip` (
  `MaKM` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctpn`
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
-- Cấu trúc bảng cho bảng `ctpx`
--

CREATE TABLE `ctpx` (
  `MaPX` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doanhsokhachhang`
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
-- Cấu trúc bảng cho bảng `doanhsonhanvien`
--

CREATE TABLE `doanhsonhanvien` (
  `MaDS` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Nam` year(4) NOT NULL,
  `DSQuy1` int(11) DEFAULT 0,
  `DSQuy2` int(11) DEFAULT 0,
  `DSQuy3` int(11) DEFAULT 0,
  `DSQuy4` int(11) DEFAULT 0,
  `DSNam` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `doanhsonhanvien`
--

INSERT INTO `doanhsonhanvien` (`MaDS`, `MaNV`, `TenNV`, `Nam`, `DSQuy1`, `DSQuy2`, `DSQuy3`, `DSQuy4`, `DSNam`) VALUES
('ds1', 'nv1', 'hello', 2019, 25, 20, 10, 5, 60),
('ds2', 'nv1', 'hello', 2020, 0, 0, 0, 0, 0),
('ds3', 'nv1', 'hello', 2021, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doanhsosp`
--

CREATE TABLE `doanhsosp` (
  `MaDS` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DonViTinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Nam` year(4) NOT NULL,
  `DSQuy1` int(11) NOT NULL DEFAULT 0,
  `DSQuy2` int(11) NOT NULL DEFAULT 0,
  `DSQuy3` int(11) NOT NULL DEFAULT 0,
  `DSQuy4` int(11) NOT NULL DEFAULT 0,
  `DSNam` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `doanhsosp`
--

INSERT INTO `doanhsosp` (`MaDS`, `MaSP`, `DonViTinh`, `Nam`, `DSQuy1`, `DSQuy2`, `DSQuy3`, `DSQuy4`, `DSNam`) VALUES
('1', '1', 'cái', 2020, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
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
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `HoTen` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `HoTen`, `SDT`) VALUES
('kh12', 'hello', ''),
('kh2', 'hello', ''),
('KH416954', 'Nguyen Thanh Sang', '0934045700');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `kho`
--

CREATE TABLE `kho` (
  `MaKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `MaKM` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenCT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NgayBD` timestamp NOT NULL DEFAULT current_timestamp(),
  `NgayKT` timestamp NOT NULL DEFAULT current_timestamp(),
  `GiaTienToiThieu` double NOT NULL,
  `PhanTram` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khuyenmai`
--

INSERT INTO `khuyenmai` (`MaKM`, `TenCT`, `NgayBD`, `NgayKT`, `GiaTienToiThieu`, `PhanTram`) VALUES
('HFMLH879612', 'Khai trương', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 50);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmaivip`
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
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`) VALUES
('VN0001', 'Việt Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
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
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `NgaySinh`, `GioiTinh`, `DiaChi`, `SoDienThoai`, `Email`) VALUES
('nv1', 'hello', '2020-11-18 11:54:42', 0, NULL, NULL, NULL),
('nv2', 'world', '2020-01-15 15:56:43', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanloai`
--

CREATE TABLE `phanloai` (
  `MaLoai` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoai` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phanloai`
--

INSERT INTO `phanloai` (`MaLoai`, `TenLoai`) VALUES
('DD', 'Đồ dùng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
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
-- Cấu trúc bảng cho bảng `phieuxuat`
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
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `AnhSP` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaLoai` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonViTinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GiaNhap` double NOT NULL,
  `GiaBan` double NOT NULL,
  `MaNCC` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `AnhSP`, `MaLoai`, `TenLoaiSP`, `SoLuong`, `DonViTinh`, `GiaNhap`, `GiaBan`, `MaNCC`, `TrangThai`) VALUES
('1', 'kẹo', NULL, 'DD', 'snack', 0, '', 0, 0, 'VN0001', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphamloi`
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
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtinkho`
--

CREATE TABLE `thongtinkho` (
  `MaKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiSP` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonViTinh` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `fk2_cthd` (`MaSP`);

--
-- Chỉ mục cho bảng `ctkhuyenmaivip`
--
ALTER TABLE `ctkhuyenmaivip`
  ADD PRIMARY KEY (`MaKM`),
  ADD KEY `fk2_ctkmvip` (`MaKH`);

--
-- Chỉ mục cho bảng `ctpn`
--
ALTER TABLE `ctpn`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `fk2_ctpn` (`MaSP`);

--
-- Chỉ mục cho bảng `ctpx`
--
ALTER TABLE `ctpx`
  ADD PRIMARY KEY (`MaPX`),
  ADD KEY `fk2_ctpx` (`MaSP`);

--
-- Chỉ mục cho bảng `doanhsokhachhang`
--
ALTER TABLE `doanhsokhachhang`
  ADD PRIMARY KEY (`MaDS`),
  ADD KEY `fk_dskh` (`MaKH`);

--
-- Chỉ mục cho bảng `doanhsonhanvien`
--
ALTER TABLE `doanhsonhanvien`
  ADD PRIMARY KEY (`MaDS`),
  ADD KEY `fk1_dsnv` (`MaNV`);

--
-- Chỉ mục cho bảng `doanhsosp`
--
ALTER TABLE `doanhsosp`
  ADD PRIMARY KEY (`MaDS`),
  ADD KEY `fk1_dssp` (`MaSP`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `fk1_hd` (`MaKH`),
  ADD KEY `fk2_hd` (`MaNV`),
  ADD KEY `fk4_hd` (`MaKM`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `kho`
--
ALTER TABLE `kho`
  ADD PRIMARY KEY (`MaKho`);

--
-- Chỉ mục cho bảng `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`MaKM`);

--
-- Chỉ mục cho bảng `khuyenmaivip`
--
ALTER TABLE `khuyenmaivip`
  ADD PRIMARY KEY (`MaKM`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Chỉ mục cho bảng `phanloai`
--
ALTER TABLE `phanloai`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `fk1_pn` (`MaNV`),
  ADD KEY `fk2_pn` (`MaNCC`),
  ADD KEY `fk3_pn` (`MaKho`);

--
-- Chỉ mục cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`MaPX`),
  ADD KEY `fk1_px` (`MaNV`),
  ADD KEY `fk2_px` (`MaKho`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `fk1_sp` (`MaLoai`),
  ADD KEY `fk2_sp` (`MaNCC`);

--
-- Chỉ mục cho bảng `sanphamloi`
--
ALTER TABLE `sanphamloi`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `fk2_spl` (`MaNCC`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`username`),
  ADD KEY `fk_tk` (`MaNV`);

--
-- Chỉ mục cho bảng `thongtinkho`
--
ALTER TABLE `thongtinkho`
  ADD PRIMARY KEY (`MaKho`),
  ADD KEY `fk2_ttkho` (`MaSP`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD CONSTRAINT `fk1_cthd` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `fk2_cthd` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `ctkhuyenmaivip`
--
ALTER TABLE `ctkhuyenmaivip`
  ADD CONSTRAINT `fk1_ctkmvip` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmaivip` (`MaKM`),
  ADD CONSTRAINT `fk2_ctkmvip` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);

--
-- Các ràng buộc cho bảng `ctpn`
--
ALTER TABLE `ctpn`
  ADD CONSTRAINT `fk1_ctpn` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`),
  ADD CONSTRAINT `fk2_ctpn` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `ctpx`
--
ALTER TABLE `ctpx`
  ADD CONSTRAINT `fk1_ctpx` FOREIGN KEY (`MaPX`) REFERENCES `phieuxuat` (`MaPX`),
  ADD CONSTRAINT `fk2_ctpx` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `doanhsokhachhang`
--
ALTER TABLE `doanhsokhachhang`
  ADD CONSTRAINT `fk_dskh` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);

--
-- Các ràng buộc cho bảng `doanhsonhanvien`
--
ALTER TABLE `doanhsonhanvien`
  ADD CONSTRAINT `fk1_dsnv` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Các ràng buộc cho bảng `doanhsosp`
--
ALTER TABLE `doanhsosp`
  ADD CONSTRAINT `fk1_dssp` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk1_hd` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `fk2_hd` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk3_hd` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`),
  ADD CONSTRAINT `fk4_hd` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmaivip` (`MaKM`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `fk1_pn` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk2_pn` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  ADD CONSTRAINT `fk3_pn` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`);

--
-- Các ràng buộc cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `fk1_px` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk2_px` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `fk1_sp` FOREIGN KEY (`MaLoai`) REFERENCES `phanloai` (`MaLoai`),
  ADD CONSTRAINT `fk2_sp` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Các ràng buộc cho bảng `sanphamloi`
--
ALTER TABLE `sanphamloi`
  ADD CONSTRAINT `fk1_spl` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  ADD CONSTRAINT `fk2_spl` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `fk_tk` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Các ràng buộc cho bảng `thongtinkho`
--
ALTER TABLE `thongtinkho`
  ADD CONSTRAINT `fk1_ttkho` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`),
  ADD CONSTRAINT `fk2_ttkho` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
