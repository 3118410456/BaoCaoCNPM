-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 21, 2020 lúc 05:33 AM
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
-- Cấu trúc bảng cho bảng `ca`
--

CREATE TABLE `ca` (
  `MaCa` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `GioBatDau` time NOT NULL,
  `GioKetThuc` time NOT NULL,
  `NgayLam` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucvu`
--

CREATE TABLE `chucvu` (
  `MaCV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenChucVu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `TenKho` varchar(50) COLLATE utf8_unicode_ci NOT NULL
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
-- Cấu trúc bảng cho bảng `luong`
--

CREATE TABLE `luong` (
  `MaLuong` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaCV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SoTien` double NOT NULL
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
  `Email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MaLuong` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phancongca`
--

CREATE TABLE `phancongca` (
  `MaCa` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
-- Cấu trúc bảng cho bảng `thexe`
--

CREATE TABLE `thexe` (
  `MaThe` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaNV` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `BangSoXe` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ThoiGianGiu` timestamp NOT NULL DEFAULT current_timestamp(),
  `ThoiGianTra` timestamp NOT NULL DEFAULT current_timestamp()
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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tudo`
--

CREATE TABLE `tudo` (
  `SoTu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TenNguoiGui` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SDT` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ca`
--
ALTER TABLE `ca`
  ADD PRIMARY KEY (`MaCa`);

--
-- Chỉ mục cho bảng `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`MaCV`);

--
-- Chỉ mục cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `fk2_cthd` (`MaSP`);

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
-- Chỉ mục cho bảng `luong`
--
ALTER TABLE `luong`
  ADD PRIMARY KEY (`MaLuong`),
  ADD KEY `fk_luong` (`MaCV`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`),
  ADD KEY `fk1_nhanvien` (`MaLuong`);

--
-- Chỉ mục cho bảng `phancongca`
--
ALTER TABLE `phancongca`
  ADD PRIMARY KEY (`MaCa`),
  ADD KEY `fk2_phancongca` (`MaNV`);

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
-- Chỉ mục cho bảng `thexe`
--
ALTER TABLE `thexe`
  ADD PRIMARY KEY (`MaThe`),
  ADD KEY `fk1_thexe` (`MaNV`);

--
-- Chỉ mục cho bảng `thongtinkho`
--
ALTER TABLE `thongtinkho`
  ADD PRIMARY KEY (`MaKho`),
  ADD KEY `fk2_ttkho` (`MaSP`);

--
-- Chỉ mục cho bảng `tudo`
--
ALTER TABLE `tudo`
  ADD PRIMARY KEY (`SoTu`),
  ADD KEY `fk1_tudo` (`MaKH`);

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
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk1_hd` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `fk2_hd` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk3_hd` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`);

--
-- Các ràng buộc cho bảng `luong`
--
ALTER TABLE `luong`
  ADD CONSTRAINT `fk_luong` FOREIGN KEY (`MaCV`) REFERENCES `chucvu` (`MaCV`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `fk1_nhanvien` FOREIGN KEY (`MaLuong`) REFERENCES `luong` (`MaLuong`);

--
-- Các ràng buộc cho bảng `phancongca`
--
ALTER TABLE `phancongca`
  ADD CONSTRAINT `fk2_phancongca` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `fk_phancongca` FOREIGN KEY (`MaCa`) REFERENCES `ca` (`MaCa`);

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
-- Các ràng buộc cho bảng `thexe`
--
ALTER TABLE `thexe`
  ADD CONSTRAINT `fk1_thexe` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);

--
-- Các ràng buộc cho bảng `thongtinkho`
--
ALTER TABLE `thongtinkho`
  ADD CONSTRAINT `fk1_ttkho` FOREIGN KEY (`MaKho`) REFERENCES `kho` (`MaKho`),
  ADD CONSTRAINT `fk2_ttkho` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `tudo`
--
ALTER TABLE `tudo`
  ADD CONSTRAINT `fk1_tudo` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
