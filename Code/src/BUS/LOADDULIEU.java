/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.CALAM;
import DTO.CHUCVU;
import DTO.CTHD;
import DTO.CTPN;
import DTO.CTPX;
import DTO.HOADON;
import DTO.KHACHHANG;
import DTO.KHO;
import DTO.KHUYENMAI;
import DTO.MALOAI;
import DTO.NGUOIDUNG;
import DTO.NHACUNGCAP;
import DTO.NHANVIEN;
import DTO.PHIEUNHAP;
import DTO.PHIEUXUAT;
import DTO.SANPHAM;
import DTO.SANPHAMLOI;
import DTO.TAIKHOAN;
import DTO.TUDO;
import GUI.GIAODIENCHINH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MINH TUAN
 */
public class LOADDULIEU {
    DAO cn = new DAO();
    Statement ps=null;
    ResultSet rs=null;
    public ArrayList<SANPHAM> getList(){
        ArrayList<SANPHAM> list = new ArrayList<>();
        String sql = "select masp,tensp,phanloai.TenLoai,soluong,donvitinh,GiaBan,GiaNhap,NHACUNGCAP.TenNCC from sanpham,nhacungcap,phanloai where sanpham.MaNCC = NHACUNGCAP.MaNCC and SANPHAM.MaLoai = PHANLOAI.MaLoai";
        
        try{
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                SANPHAM sp = new SANPHAM();
                sp.setMaSP(rs.getString("masp"));
                sp.setTenSP(rs.getString("tensp"));
                sp.setMaLoai(rs.getString("TenLoai"));
                sp.setSoLuong(rs.getInt("soluong"));
                sp.setDonViTinh(rs.getString("donvitinh"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                sp.setGiaNhap(rs.getDouble("GiaNhap"));
                sp.setMaNCC(rs.getString("TenNCC"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<KHUYENMAI> getListKhuyenMai()
   {
       ArrayList<KHUYENMAI> list = new ArrayList<>();
       String sql = "SELECT * FROM khuyenmai";
       try {
           PreparedStatement ps = cn.conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           
           while(rs.next())
           {
               KHUYENMAI km = new KHUYENMAI();
               km.setMaKM(rs.getString("MaKM").toUpperCase());
               km.setTenCT(rs.getString("TenCT"));
               km.setNgayBatDau(rs.getString("NgayBD"));
               km.setNgayKetThuc(rs.getString("NgayKT"));
               km.setPhanTram(rs.getFloat("PhanTram"));
               
              list.add(km);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }
    public ArrayList<TAIKHOAN> getListTaiKhoan()
    {
        ArrayList<TAIKHOAN> list = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";
        
        try {
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                TAIKHOAN tk = new TAIKHOAN();
                tk.setTaiKhoan(rs.getString("username"));
                tk.setMatKhau(rs.getString("password"));
                tk.setMaNV(rs.getString("MaNV").toUpperCase());
                tk.setTrangThai(rs.getString("TrangThai"));
                
//                if(tk.getTaiKhoan().equalsIgnoreCase("admin") == false)
//                {
                list.add(tk);
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return list;
    }
     public ArrayList<NGUOIDUNG> NguoiDung(String tk)
   {
        String sql = "SELECT taikhoan.username,taikhoan.password,taikhoan.TrangThai ,nhanvien.MaNV,nhanvien.HoTen,nhanvien.GioiTinh,nhanvien.NgaySinh,nhanvien.SoDienThoai,nhanvien.DiaChi,nhanvien.Email,chucvu.TenChucVu FROM taikhoan,nhanvien,chucvu WHERE taikhoan.MaNV=nhanvien.MaNV AND chucvu.MaCV=nhanvien.MaCV AND username='"+tk+"'";
        ArrayList<NGUOIDUNG> list = new ArrayList<>();
         try{
          ps = cn.conn.createStatement();
          rs = ps.executeQuery(sql);
          while(rs.next())
          {
             NGUOIDUNG tt = new NGUOIDUNG();
             tt.setUser(rs.getString("username"));
             tt.setPass(rs.getString("password"));
             tt.setManv(rs.getString("MaNV"));
             tt.setHoten(rs.getString("HoTen"));
             tt.setNgaysinh(rs.getString("NgaySinh"));
             tt.setGioitinh(rs.getString("GioiTinh"));
             tt.setDiachi(rs.getString("DiaChi"));
             tt.setEmail(rs.getString("Email"));
             tt.setSdt(rs.getString("SoDienThoai"));
             tt.setTenChucVu(rs.getString("TenChucVu"));
             tt.setTinhtrang(rs.getInt("TinhTrang"));
             list.add(tt);
          }
          
       }catch(Exception e)
       { e.printStackTrace();}
       return list;
   }
     
     public ArrayList<NGUOIDUNG> getListNguoiDung()
   {
        String sql = "SELECT taikhoan.username,taikhoan.password,taikhoan.TrangThai ,nhanvien.MaNV,nhanvien.HoTen,nhanvien.GioiTinh,nhanvien.NgaySinh,nhanvien.SoDienThoai,nhanvien.DiaChi,nhanvien.Email,chucvu.TenChucVu FROM taikhoan,nhanvien,chucvu WHERE taikhoan.MaNV=nhanvien.MaNV AND chucvu.MaCV=nhanvien.MaCV ";
        ArrayList<NGUOIDUNG> list = new ArrayList<>();
         try{
          ps = cn.conn.createStatement();
          rs = ps.executeQuery(sql);
          while(rs.next())
          {
             NGUOIDUNG tt = new NGUOIDUNG();
             tt.setUser(rs.getString("username"));
             tt.setPass(rs.getString("password"));
             tt.setManv(rs.getString("MaNV"));
             tt.setHoten(rs.getString("HoTen"));
             tt.setNgaysinh(rs.getString("NgaySinh"));
             tt.setGioitinh(rs.getString("GioiTinh"));
             tt.setDiachi(rs.getString("DiaChi"));
             tt.setEmail(rs.getString("Email"));
             tt.setSdt(rs.getString("SoDienThoai"));
             tt.setTenChucVu(rs.getString("TenChucVu"));
             tt.setTinhtrang(rs.getInt("TinhTrang"));
             list.add(tt);
          }
          
       }catch(Exception e)
       { e.printStackTrace();}
       return list;
   }
    
     public ArrayList<NHANVIEN> getListNhanVien()
   {
        String sql = "SELECT nhanvien.MaNV,nhanvien.HoTen,nhanvien.GioiTinh,nhanvien.NgaySinh,nhanvien.SoDienThoai,nhanvien.DiaChi,nhanvien.Email,chucvu.TenChucVu FROM nhanvien,chucvu WHERE nhanvien.MaCV=chucvu.MaCV";
        ArrayList<NHANVIEN> list = new ArrayList<>();
         try{
          ps = cn.conn.createStatement();
          rs = ps.executeQuery(sql);
          while(rs.next())
          {
             NHANVIEN nv = new NHANVIEN();
             
             nv.setMaNV(rs.getString("MaNV"));
             nv.setHoTen(rs.getString("HoTen"));
             nv.setNgaySinh(rs.getString("NgaySinh"));
             nv.setGioiTinh(rs.getString("GioiTinh"));
             nv.setDiaChi(rs.getString("DiaChi"));
             nv.setEmail(rs.getString("Email"));
             nv.setSDT(rs.getString("SoDienThoai"));
             nv.setTenChucVu(rs.getString("TenChucVu"));
             list.add(nv);
          }
          
       }catch(Exception e)
       { e.printStackTrace();}
       return list;
   }
    
    public ArrayList<HOADON> getListHD(){
        ArrayList<HOADON> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadon ";
        
        try{
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                HOADON h = new HOADON();
                h.setMaHD(rs.getString("MaHD"));
                h.setMaNV(rs.getString("MaNV"));
                h.setMaKH(rs.getString("MaKH"));
                h.setMaKM(rs.getString("MaKM"));
                h.setNgayTao(rs.getString("NgayTao"));
                h.setTongCong(rs.getDouble("TongCong"));
                h.setPhanTramKM(rs.getInt("PhanTramKM"));
                h.setTongTienTra(rs.getDouble("TongTienTra"));
                h.setTrangThai(rs.getString("TrangThai"));
                
                list.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public ArrayList<CTHD> getListCTHD(String mahd){
        ArrayList<CTHD> list = new ArrayList<>();
//        String sql = "select MaSP,TenSP,sum(SoLuong) as SoLuong,DonGia,sum(SoLuong)*DonGia as ThanhTien from CTHD where MaHD='"+mahd+"' \n" +
//"group by MaSP,TenSP,DonGia";

          String sql = "select sanpham.TenSP,cthd.SoLuong,sanpham.GiaBan,(cthd.SoLuong*sanpham.GiaBan) as ThanhTien from cthd,sanpham where sanpham.MaSP = cthd.MaSP and cthd.MaHD='"+mahd+"'";
        
        try{
            PreparedStatement ps = cn.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                
                CTHD ch = new CTHD();
                ch.setTenSP(rs.getString("TenSP"));
                //ch.setTenSP(rs.getString("TenSP"));
                ch.setSoLuong(rs.getInt("SoLuong"));
                ch.setDongia(rs.getDouble("GiaBan"));         
                ch.setThanhTien(rs.getDouble("ThanhTien"));
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public ArrayList<NHACUNGCAP> getListNCC()
    {
        String sql = "Select * from NHACUNGCAP";
        ArrayList<NHACUNGCAP> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            NHACUNGCAP nsx = new NHACUNGCAP();
            nsx.setMaNSX(rs.getString(1));
            nsx.setTenNSX(rs.getString(2));
            list.add(nsx);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public ArrayList<MALOAI> getListMaLoai()
    {
        String sql = "Select * from PHANLOAI";
        ArrayList<MALOAI> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            MALOAI nsx = new MALOAI();
            nsx.setMaloai(rs.getString(1));
            nsx.setTenloai(rs.getString(2));
            list.add(nsx);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public int TongHDcuaKH(String ma)
      {
          String sql = "select count(makh) as tonghoadon from hoadon where makh='"+ma+"'";
        try {
            ps = cn.conn.createStatement();
             rs = ps.executeQuery(sql);
             while(rs.next())
             {
                 return rs.getInt("tonghoadon");
             }
        } catch (SQLException ex) {
            Logger.getLogger(LOADDULIEU.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0;
          
      }
      public double TongTienHDcuaKH(String ma)
      {
          String sql = "select sum(TongTien) as tongtien from hoadon where makh='"+ma+"'";
        try {
            ps = cn.conn.createStatement();
             rs = ps.executeQuery(sql);
             while(rs.next())
             {
                 return rs.getDouble("tongtien");
             }
        } catch (SQLException ex) {
            Logger.getLogger(LOADDULIEU.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0.0;
          
      }
     
       public ArrayList<KHACHHANG> getListKhachHang()
    {
        int tonghd;
        String sql = "select * from KHACHHANG";
        ArrayList<KHACHHANG> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            //String ma = rs.getString("MaKH");
            //int tonghd = this.TongHDcuaKH(ma);
            //double tongtien = this.TongTienHDcuaKH(ma);
            KHACHHANG kh = new KHACHHANG();
            tonghd = this.TongHDcuaKH(rs.getString(1));
            kh.setMakh(rs.getString("MaKH")); 
            kh.setTenkh(rs.getString("HoTen"));
            kh.setSdt(rs.getString("SDT"));
            kh.setTongHD(tonghd);
            kh.setTongTien(123.0);
            list.add(kh);
            tonghd = 0 ;
        }
        } catch (SQLException ex) {
           
        }
        return list;
    }
       public ArrayList<KHACHHANG> getListKhachHang2()
    {
        String sql = "select * from khachhang";
        ArrayList<KHACHHANG> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            KHACHHANG kh = new KHACHHANG();
            kh.setMakh(rs.getString(1));
            kh.setTenkh(rs.getString(2));
            kh.setSdt(rs.getString(3));
            
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public ArrayList<PHIEUNHAP> getListPhieuNhap()
    {
        String sql = "select *,ctpn.soluong*ctpn.gianhap as thanhtien from phieunhap , ctpn where phieunhap.mapn=ctpn.mapn";
        ArrayList<PHIEUNHAP> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            PHIEUNHAP kh = new PHIEUNHAP();
            kh.setMaPN(rs.getString("MaPN"));
            kh.setMaKho(rs.getString("MaKho"));
            kh.setMaNV(rs.getString("MaNV"));
            kh.setMaNCC(rs.getString("MaNCC"));
            kh.setNgayNhap(rs.getString("NgayNhap"));
            kh.setTongTien(rs.getDouble("thanhtien"));
            kh.setTrangThai(rs.getString("TrangThai"));
            
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public ArrayList<CTHD> TTGioHang()
    {
        ArrayList<CTHD> list = new ArrayList<>();
//        String sql="select sanpham.masp,sanpham.tensp,sum(giohang.soluong) as soluong,sanpham.DonGia,sum((giohang.soluong*sanpham.DonGia)) as thanhtien\n" +
//"from sanpham,giohang\n" +
//"where giohang.masp = sanpham.masp\n" +
//"group by sanpham.TenSP,sanpham.DonGia,sanpham.MaSP";

        String sql ="select * from cthd , cthd  where hoadon.MaHD=cthd.MaHD";
        try {
            ps=cn.conn.createStatement();
            rs=ps.executeQuery(sql);
            while(rs.next())
            {
                CTHD h = new CTHD();
                h.setMaSP(rs.getString("MaSP"));
                //h.setTenSP(rs.getString("TenSP"));
                h.setSoLuong(rs.getInt("SoLuong"));
                //h.setDongia(rs.getDouble("DonGia"));
                h.setThanhTien(rs.getDouble("GiaTien"));
                list.add(h);
            }
        } catch (SQLException ex) {
           
        }
       return list;
    }
      public ArrayList<HOADON> HD_KHACHHANG(String ma)
      {
          String sql = "Select MaHD,NgayXuat,TongTien from HOADON where MaKH='"+ma+"' order by CAST(NgayXuat AS date)";
          ArrayList<HOADON> list = new ArrayList<>();
          try {
              ps = cn.conn.createStatement();
              rs = ps.executeQuery(sql);
              while(rs.next())
              {
                  HOADON h = new HOADON();
                  h.setMaHD(rs.getString("MaHD"));
                  h.setNgayTao(rs.getString("NgayTao"));
                  h.setTongTienTra(rs.getDouble("TongTien"));
                  list.add(h);
              }
          } catch (Exception e) {
          }
          return list;
      }
       public ArrayList<CTPN> getListCTPN()
    {
//        String sql = "select masp,sum(soluong) as soluong,gianhap,sum(thanhtien) as thanhtien\n" +
//"from ctpn\n" +
//"where MaPN is null\n" +
//"group by masp,gianhap";
        String sql = "select * from ctpn";
        ArrayList<CTPN> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            CTPN kh = new CTPN();
            kh.setMaPN(rs.getString("mapn"));
            kh.setMaSP(rs.getString("masp"));
            kh.setTenSP(rs.getString("Tensp"));
            kh.setTenLoaiSP(rs.getString("TenLoaiSP"));
            //kh.setThanhTien(rs.getDouble("thanhtien"));
            kh.setSoLuong(rs.getInt("SoLuong"));
            kh.setDonViTinh(rs.getString("DonViTinh"));
            kh.setGiaNhap(rs.getDouble("GiaNhap"));
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
       public ArrayList<CTPN> getListCTPN(String mapn)
    {
        String sql = "select masp,sum(soluong) as soluong,gianhap,sum(thanhtien) as thanhtien\n" +
"from ctpn\n" +
"where MaPN='"+mapn+"'\n" +
"group by masp,gianhap";
        ArrayList<CTPN> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            CTPN kh = new CTPN();
            kh.setMaSP(rs.getString("masp"));
            kh.setGiaNhap(rs.getDouble("gianhap"));
            kh.setThanhTien(rs.getDouble("thanhtien"));
            kh.setSoLuong(rs.getInt("soluong"));
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public ArrayList<NHANVIEN> getListThongTinNhanVien()
   {
       ArrayList<NHANVIEN> list = new ArrayList<>();
       String sql = "SELECT MaNV,HoTen,GioiTinh,NgaySinh,SoDienThoai,DiaChi,Email FROM nhanvien";
       try {
           PreparedStatement ps = cn.conn.prepareStatement(sql);
           rs = ps.executeQuery();
           
           while(rs.next())
           {
              
           
             NHANVIEN nv = new NHANVIEN();
               nv.setMaNV(rs.getString("MaNV"));
               nv.setHoTen(rs.getString("HoTen"));
               nv.setGioiTinh(rs.getString("GioiTinh"));
               nv.setNgaySinh(rs.getString("NgaySinh"));
               nv.setSDT(rs.getString("SoDienThoai"));
               nv.setDiaChi(rs.getString("DiaChi"));
               nv.setEmail(rs.getString("Email"));
               
               if(nv.getMaNV().equalsIgnoreCase("admin") == false)
               {
                   list.add(nv);
               }
      
              
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return list;
   }
       
       
       public ArrayList<CHUCVU> getListChucVu()
       {
           ArrayList<CHUCVU> list = new ArrayList<>();
           String sql ="SELECT chucvu.MaCV , chucvu.TenChucVu , COUNT(nhanvien.MaCV) as Tong , chucvu.Luong from chucvu,nhanvien WHERE nhanvien.MaCV=chucvu.MaCV";
           try {
               PreparedStatement ps = cn.conn.prepareStatement(sql);
               rs = ps.executeQuery();
               
               while(rs.next())
        {
            CHUCVU cv = new CHUCVU();
            cv.setMaCV(rs.getString("MaCV"));
            cv.setTenCV(rs.getString("TenChucVu"));
            cv.setSoLuong(rs.getInt("Tong"));
            cv.setLuong(rs.getDouble("Luong"));
            list.add(cv);
        }
               
           } catch (Exception e) {
               e.printStackTrace();
           }
        return list;
       }
       
       public ArrayList<TUDO> getListTuDo()
       {
           ArrayList<TUDO> list = new ArrayList<>();
           String sql ="select * from tudo";
           try {
               PreparedStatement ps = cn.conn.prepareStatement(sql);
               rs = ps.executeQuery();
               
               while(rs.next())
               {
                   TUDO td = new TUDO();
                   td.setSoTu(rs.getString("SoTu"));
                   td.setMaKH(rs.getString("MaKH"));
                   td.setTenKH(rs.getString("TenNguoiGui"));
                   td.setSDT(rs.getString("SDT"));
                   list.add(td);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
        return list;
       }
       
       public ArrayList<SANPHAMLOI> getListSanPhamLoi()
       {
           ArrayList<SANPHAMLOI> list = new ArrayList<>();
           String sql ="SELECT *,DonGia*SoLuong AS ThanhTien from sanphamloi";
           try {
               PreparedStatement ps = cn.conn.prepareStatement(sql);
               rs = ps.executeQuery();
               
               while(rs.next())
               {
                   SANPHAMLOI spl = new SANPHAMLOI();
                   spl.setMaSP(rs.getString("MaSP"));
                   spl.setTenSP(rs.getString("TenSP"));
                   spl.setSoLuong(rs.getInt("SoLuong"));
                   spl.setMaNCC(rs.getString("MaNCC"));
                   spl.setDonGia(rs.getDouble("DonGia"));
                   spl.setLyDo(rs.getString("LyDo"));
                   spl.setThanhTien(rs.getDouble("ThanhTien"));
                   list.add(spl);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
        return list;
       }
       
       
       public ArrayList<KHO> getListKho()
       {
           ArrayList<KHO> list = new ArrayList<>();
           String sql ="select * from kho";
           try {
               PreparedStatement ps = cn.conn.prepareStatement(sql);
               rs = ps.executeQuery();
               
               while(rs.next())
               {
                   KHO k = new KHO();
                   k.setMaKho(rs.getString("MaKho"));
                   k.setTenKho(rs.getString("TenKho"));
                   k.setDiaChi(rs.getString("DiaChi"));
                   list.add(k);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
        return list;
       }
       
       public ArrayList<PHIEUXUAT> getListPhieuXuat()
    {
//        String sql = "select masp,sum(soluong) as soluong,gianhap,sum(thanhtien) as thanhtien\n" +
//"from ctpn\n" +
//"where MaPN is null\n" +
//"group by masp,gianhap";
        String sql = "select * from phieuxuat ";
        ArrayList<PHIEUXUAT> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            PHIEUXUAT kh = new PHIEUXUAT();
            kh.setMaPX(rs.getString("MaPX"));
            kh.setNgayXuat(rs.getString("MaKho"));
            kh.setMaKho(rs.getString("MaNV"));
            kh.setMaNV(rs.getString("NgayXuat"));
            //kh.setThanhTien(rs.getDouble("thanhtien"));
            kh.setTongTien(rs.getDouble("TongTien"));
            kh.setTrangThai(rs.getString("TrangThai"));
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
       public ArrayList<CTPX> getListCTPX()
    {
        String sql = "SELECT ctpx.MaPX,ctpx.MaSP,sanpham.TenSP,ctpx.SoLuong,ctpx.DonGia,sanpham.DonViTinh from phieuxuat,sanpham,ctpx WHERE ctpx.MaSP=sanpham.MaSP";
        ArrayList<CTPX> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            CTPX kh = new CTPX();
            kh.setMaPX(rs.getString("MaPX"));
            kh.setMaSP(rs.getString("MaSP"));
            kh.setTenSP(rs.getString("TenSP"));
            kh.setDonViTinh(rs.getString("DonViTinh"));
            kh.setSoluong(rs.getInt("SoLuong"));
            kh.setDongia(rs.getDouble("DonGia"));
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
        public ArrayList<CALAM> getListCaLam()
    {
        String sql = "SELECT * from ca";
        ArrayList<CALAM> list = new ArrayList<>();
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
        while(rs.next())
        {
            CALAM kh = new CALAM();
            kh.setMaCa(rs.getString("MaCa"));
            kh.setGioBD(rs.getString("GioBatDau"));
            kh.setGioKT(rs.getString("GioKetThuc"));
            kh.setNgayLam(rs.getString("NgayLam"));
            list.add(kh);
        }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
