/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.CTHD;
import DTO.HOADON;
import GUI.GIAODIENCHINH;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MINH TUAN
 */
public class BUS_THONGKE {
    Statement ps;
    ResultSet rs;
    DAO cn = new DAO();
    public ArrayList<HOADON> LocHDTheoNgay(String ngay)
    {
        ArrayList<HOADON> list = new ArrayList<>();
        String sql ="select * from hoadon where ngaytao='"+ngay+"' order by tongtientra ASC";
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
            Double tongtientk = 0.0;
            while(rs.next())
            {
                HOADON h = new HOADON();
                h.setMaHD(rs.getString("MaHD"));
                h.setNgayTao(rs.getString("NgayTao"));
                h.setTongTienTra(rs.getDouble("TongTienTra"));
                tongtientk+=rs.getDouble("TongTienTra");
                list.add(h);
            }
            GIAODIENCHINH.lbtongdoanhthu.setText(cn.vnmoney.format(tongtientk));
        } catch (Exception e) {
        }
        return list;
    }
    
    public ArrayList<HOADON> LocHDTheoThangNam(String thang,String nam)
    {
        ArrayList<HOADON> list = new ArrayList<>();
        String sql ="select * from hoadon where ngaytao LIKE '"+nam+"/"+thang+"/%' order by tongtientra ASC";
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
             Double tongtientk = 0.0;
            while(rs.next())
            {
                HOADON h = new HOADON();
                h.setMaHD(rs.getString("MaHD"));
                h.setNgayTao(rs.getString("NgayTao"));
                h.setTongTienTra(rs.getDouble("TongTienTra"));
                tongtientk+=rs.getDouble("TongTienTra");
                list.add(h);
            }
            GIAODIENCHINH.lbtongdoanhthu.setText(cn.vnmoney.format(tongtientk));
        } catch (Exception e) {
        }
        return list;
    }
    public ArrayList<CTHD> ThongKeSPTheoNgay(String ngay)
    {
         ArrayList<CTHD> list = new ArrayList<>();
        String sql ="select TenSP,sum(SoLuong) as SoLuong,GiaBan,sum(ThanhTienTra) as ThanhTienTra\n" +
"from cthd where mahd in (select mahd from hoadon where ngaytao LIKE '"+ngay+"') \n" +
"group by TenSP,GiaBan";
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
           
            while(rs.next())
            {
                CTHD h = new CTHD();
                h.setTenSP(rs.getString("TenSP"));
                h.setSoLuong(rs.getInt("SoLuong"));
                h.setDongia(rs.getDouble("GiaBan"));
                h.setThanhTien(rs.getDouble("ThanhTienTra"));
                list.add(h);
            }
         
        } catch (Exception e) {
        }
        return list;
    }
    public ArrayList<CTHD> ThongKeSPTheoKhoangThoiGian(String from,String to)
    {
         ArrayList<CTHD> list = new ArrayList<>();
        String sql ="select TenSP,sum(SoLuong) as SoLuong,GiaBan,sum(ThanhTienTra) as ThanhTienTra\n" +
"from cthd where mahd in (select MaHD from hoadon where ngaytao between CAST('"+from+"' AS date) and CAST('"+to+"' AS date))\n" +
"group by TenSP,GiaBan";
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
           
            while(rs.next())
            {
                CTHD h = new CTHD();
                h.setTenSP(rs.getString("TenSP"));
                h.setSoLuong(rs.getInt("SoLuong"));
                h.setDongia(rs.getDouble("GiaBan"));
                h.setThanhTien(rs.getDouble("ThanhTienTra"));
                list.add(h);
            }
         
        } catch (Exception e) {
        }
        return list;
    }
    public double TongTatCa()
    {
         String sql = "Select sum(ThanhTienTra) as tong from CTHD";
        try
        {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
            while(rs.next())
            {
                return Double.parseDouble(rs.getString("tong"));
            }
        }
        catch(Exception e)
                {}
        return 0.0;
    }
    public ArrayList<CTHD> ThongKeTatCa()
    {
         ArrayList<CTHD> list = new ArrayList<>();
        String sql ="select TenSP,sum(SoLuong) as SoLuong,GiaBan,sum(ThanhTienTra) as ThanhTienTra\n" +
"from cthd\n" +
"group by TenSP,GiaBan";
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
           
            while(rs.next())
            {
                CTHD h = new CTHD();
                h.setTenSP(rs.getString("TenSP"));
                h.setSoLuong(rs.getInt("SoLuong"));
                h.setDongia(rs.getDouble("GiaBan"));
                h.setThanhTien(rs.getDouble("ThanhTienTra"));
                list.add(h);
            }
         
        } catch (Exception e) {
        }
        return list;
    }
    public ArrayList<CTHD> ThongKeSPTheoThangNam(String thang,String nam)
    {
        ArrayList<CTHD> list = new ArrayList<>();
        String sql ="select TenSP,sum(SoLuong) as SoLuong,DonGia,sum(ThanhTien) as ThanhTien\n" +
"from cthd where mahd in (select mahd from hoadon where ngayxuat LIKE '"+nam+"/"+thang+"/%') \n" +
"group by TenSP,DonGia";
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
           
            while(rs.next())
            {
                CTHD h = new CTHD();
                h.setTenSP(rs.getString("TenSP"));
                h.setSoLuong(rs.getInt("SoLuong"));
                h.setDongia(rs.getDouble("GiaBan"));
                h.setThanhTien(rs.getDouble("ThanhTienTra"));
                list.add(h);
            }
         
        } catch (Exception e) {
        }
        return list;
    }
     public ArrayList<HOADON> LocHDTheoKhoangThoiGian(String from,String to)
    {
        ArrayList<HOADON> list = new ArrayList<>();
        String sql ="select MaHD,CAST(ngaytao AS date) as NgayTao,TongTienTra from hoadon where ngaytao between CAST('"+from+"' AS date) and CAST('"+to+"' AS date) order by CAST(NgayTao AS date)";
        try {
            ps = cn.conn.createStatement();
            rs = ps.executeQuery(sql);
            Double tongtientk = 0.0;
            while(rs.next())
            {
                HOADON h = new HOADON();
                h.setMaHD(rs.getString("MaHD"));
                h.setNgayTao(rs.getString("NgayTao"));
                h.setTongTienTra(rs.getDouble("TongTienTra"));
                tongtientk+=rs.getDouble("TongTienTra");
                list.add(h);
            }
           GIAODIENCHINH.lbtongdoanhthu.setText(cn.vnmoney.format(tongtientk));
        } catch (Exception e) {
        }
        return list;
    }
}
