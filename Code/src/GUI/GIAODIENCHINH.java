/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BUS_SUA;
import BUS.BUS_THEM;
import BUS.BUS_THONGKE;
import BUS.BUS_XOA;
import BUS.LOADDULIEU;
import DTO.KHUYENMAI;
import DTO.SANPHAM;
import DTO.CTHD;
import DTO.HOADON;
import DTO.NGUOIDUNG;

import DAO.DAO;
import DAO.KiemTraLoi;
import DTO.CALAM;
import DTO.CHUCVU;
import DTO.CTPN;
import DTO.CTPX;
import DTO.KHACHHANG;
import DTO.KHO;
import DTO.NHACUNGCAP;
import DTO.NHANVIEN;
import DTO.PHANCONGCA;
import DTO.PHIEUNHAP;
import DTO.PHIEUXUAT;
import DTO.SANPHAMLOI;
import DTO.TAIKHOAN;
import DTO.THONGTINKHO;
import DTO.TUDO;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MINH TUAN
 */
public class GIAODIENCHINH extends javax.swing.JFrame {
    public CardLayout card;
    public String pass;
    static NumberFormat vnmoney = NumberFormat.getInstance(new Locale("vi", "vn"));
    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    public static DefaultComboBoxModel boxpn;
    public static String matruyen;
    public static DAO d = new DAO();
    
    //public static DAOPhieuNhap dpn = new DAOPhieuNhap();
    //public static ArrayList<PhieuNhap> listphieunhap = new ArrayList<>();
    //public static ArrayList<ChiTietPhieuNhap> listctpn = new ArrayList<>();
    public static double os;
    public static ArrayList<SANPHAM> list = new ArrayList<>();
    public static ArrayList<HOADON> listhd = new ArrayList<>();
    public static ArrayList<CTHD> listcthd = new ArrayList<>();
    public static ArrayList<CTHD> tktc = new ArrayList<>();
    public static ArrayList<NGUOIDUNG> ttdn = new ArrayList<>();
    public static ArrayList<KHACHHANG> listkh = new ArrayList<>();
    public static ArrayList<KHUYENMAI> listkm = new ArrayList<>();
    public static ArrayList<CTPN> listctpn = new ArrayList<>();
    public static ArrayList<PHIEUNHAP> listphieunhap = new ArrayList<>();
    public static ArrayList<CHUCVU> listchucvu = new ArrayList<>();
    public static ArrayList<TAIKHOAN> listtaikhoan = new ArrayList<>();
    public static ArrayList<NHANVIEN> listnhanvien = new ArrayList<>();
    public static ArrayList<TUDO> listtudo = new ArrayList<>();
    public static ArrayList<SANPHAMLOI> listsploi = new ArrayList<>();
    public static ArrayList<KHO> listkho = new ArrayList<>();
    public static ArrayList<PHIEUXUAT> listphieuxuat = new ArrayList<>();
    public static ArrayList<CTPX> listctpx = new ArrayList<>();
    public static ArrayList<NHACUNGCAP> listncc = new ArrayList<>();
    public static ArrayList<CALAM> listcalam = new ArrayList<>();
    public static ArrayList<PHANCONGCA> listphancongca = new ArrayList<>();
    public static ArrayList<THONGTINKHO> listthongtinkho = new ArrayList<>();
    public static DefaultTableModel modelqlsp,modeldshd,modelcthd,modelthongke,modelcttk,modeltksp,modelkm,modelkhachhang,modelctkh,modelctpn,modelphieunhap,
            modelchucvu,modeltaikhoan,modelnhanvien,modeltudo,modelsploi,modelphieuxuat,modelctpx,modelkho,modelthongtinkho,modelncc,modelcalam,modelphancongca;
   int mx,my;
   
    public GIAODIENCHINH() {
       
        
        initComponents();
        boxpn = (DefaultComboBoxModel) combosp.getModel();
        KhoiTaoGiaoDien();
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        hienthingay();
        hienthigio();
        setHeaderTable();
        getModelTable();

        
//        setThongTinCaNhan();
//        setAvatar(ttdn.get(0).getUser());
        LocAll();
        
        
    }
    public static void LocListKH()
    {
        modelkhachhang.setRowCount(0);
        listkh = new LOADDULIEU().getListKhachHang2();
        for(KHACHHANG kh:listkh)
        {
            modelkhachhang.addRow(new Object[]{kh.getMakh(),kh.getTenkh(),kh.getSdt(),new LOADDULIEU().TongHDcuaKH(kh.getMakh()),vnmoney.format(new LOADDULIEU().TongTienHDcuaKH(kh.getMakh()))});
        }
    }
    
    public static void LocAll()
    {
        LocListSP();
        LocListHD();
        LocThongKe();
        LocListKhuyenMai();
        LocListKH();
        LocListCTPN();
        LocListPhieuNhap();
        LocListChucVu();
        LocListTaiKhoan();
        LocListNhanVien();
        LocListTuDo();
        LocListSanPhamLoi();
        LocListKho();
        LocListThongTinKho();
        LocListPhieuXuat();
        LocListCTPX();
        LocListNCC();
        LocListCaLam();
        LocListPhanCongCa();
    }
    public void KhoiTaoGiaoDien()
    {
        day1.setDate(new Date());
        day3.setDate(new Date());
        bienmat.setVisible(false);
        card = (CardLayout)panel.getLayout();
        card.show(panel, "qlsp");
        
        
    }
    public void setThongTinCaNhan()
    {
        String userlogin = LOGIN.txtTaiKhoan.getText();
         ttdn = new LOADDULIEU().NguoiDung(userlogin);
        lbhello.setText("Xin chào : " + ttdn.get(0).getHoten());
        ttdnmanv.setText(ttdn.get(0).getManv());
        ttdnhoten.setText(ttdn.get(0).getHoten());
        try {
            ttdnngaysinh.setText(sdf2.format(sdf1.parse(String.valueOf(ttdn.get(0).getNgaysinh()))));
        } catch (ParseException ex) {
            Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
        }
        ttdndiachi.setText(ttdn.get(0).getDiachi());
        ttdnemail.setText(ttdn.get(0).getEmail());
        ttdnsdt.setText(ttdn.get(0).getSdt());
    }
    public void getModelTable()
    {
        modelqlsp = (DefaultTableModel) tblqlsp.getModel();
        modeldshd = (DefaultTableModel) tbldshd.getModel();
        modelthongke = (DefaultTableModel) tblthongke.getModel();
        modelcttk = (DefaultTableModel) tblcttk.getModel();
        modeltksp = (DefaultTableModel) tbltksp.getModel();
        modelkm = (DefaultTableModel) tblkm.getModel();
        modelkhachhang = (DefaultTableModel) tblkhachhang.getModel();
        modelctkh = (DefaultTableModel) tblctkh.getModel();
        modelctpn = (DefaultTableModel) tblctpn.getModel();
        modelphieunhap = (DefaultTableModel) tblphieunhap.getModel();
        modelchucvu = (DefaultTableModel) tblqlcv.getModel();
        modeltaikhoan = (DefaultTableModel) tbltaikhoan.getModel();
        modelnhanvien = (DefaultTableModel) tblqlnv.getModel();
        modeltudo = (DefaultTableModel) tblqltudo.getModel();
        modelsploi = (DefaultTableModel) tblsploi.getModel();
        modelphieuxuat = (DefaultTableModel) tblphieuxuat.getModel();
        modelctpx = (DefaultTableModel) tblctpx.getModel();
        modelkho = (DefaultTableModel) tblkho.getModel();
        modelncc = (DefaultTableModel) tblncc.getModel();
        modelcalam = (DefaultTableModel) tblcalam.getModel();
        modelphancongca = (DefaultTableModel) tblphancongca.getModel();
        modelthongtinkho = (DefaultTableModel) tblthongtinkho.getModel();
    }
    public void setHeaderTable()
    {
        setHeader(tblqlsp);
        setHeader(tbldshd);
        setHeader(tblthongke);
        setHeader(tblcttk);
        setHeader(tbltksp);
        setHeader(tblkm);
        setHeader(tblkhachhang);
        setHeader(tblctkh);
        setHeader(tblctpn);
        setHeader(tblphieunhap);
        setHeader(tblqlcv);
        setHeader(tbltaikhoan);
        setHeader(tblqlnv);
        setHeader(tblqltudo);
        setHeader(tblsploi);
        setHeader(tblctpx);
        setHeader(tblphieuxuat);
        setHeader(tblkho);
        setHeader(tblncc);
        setHeader(tblcalam);
        setHeader(tblphancongca);
        setHeader(tblthongtinkho);
        setHeader(tbltkln);
    }
    public static void LocListHD()
    {
        modeldshd.setRowCount(0);
        modelthongke.setRowCount(0);
        listhd = new LOADDULIEU().getListHD();
        for(HOADON h : listhd)
        {
            if(h.getTrangThai().equals("1"))
            {
                modeldshd.addRow(new Object[]{h.getMaHD(),h.getMaNV(),h.getMaKH(),h.getNgayTao(),h.getMaKM(),vnmoney.format(h.getTongTienTra()),"Đã thanh toán"});
                //modelthongke.addRow(new Object[]{h.getMaHD(),h.getMaNV(),sdf2.format(sdf1.parse(String.valueOf(h.getNgayTao()))),vnmoney.format(h.getTongCong()),});
            }
            else 
            {
                modeldshd.addRow(new Object[]{h.getMaHD(),h.getMaNV(),h.getMaKH(),h.getNgayTao(),h.getMaKM(),vnmoney.format(h.getTongTienTra()),"Chưa thanh toán"});
                //modelthongke.addRow(new Object[]{h.getMaHD(),h.getMaNV(),sdf2.format(sdf1.parse(String.valueOf(h.getNgayTao()))),vnmoney.format(h.getTongCong()),});
            }
        }
    }
    public static void LocListSP()
    {
        modelqlsp.setRowCount(0);
        //boxpn.removeAllElements();
        list = new LOADDULIEU().getList();
        for(SANPHAM s : list)
        {
            //boxpn.addElement(s.getMaSP() + "   " +s.getTenSP());
            modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
        }
    }
    
    private static void LocListNCC()
    {
        modelncc.setRowCount(0);
        listncc = new LOADDULIEU().getListNCC();
         
         for(NHACUNGCAP ncc : listncc)
         {
             modelncc.addRow(new Object[]{ncc.getMaNSX(),ncc.getTenNSX()});
         }
    }
    
     public static void LocListCaLam()
    {
        modelcalam.setRowCount(0);
        listcalam = new LOADDULIEU().getListCaLam();
         
         for(CALAM ncc : listcalam)
         {
             modelcalam.addRow(new Object[]{ncc.getMaCa(),ncc.getGioBD(),ncc.getGioKT(),ncc.getNgayLam()});
         }
    }
     
     public static void LocListPhanCongCa()
    {
        modelphancongca.setRowCount(0);
        listphancongca = new LOADDULIEU().getListPhanCongCa();
         
         for(PHANCONGCA ncc : listphancongca)
         {
             modelphancongca.addRow(new Object[]{ncc.getMaCa(),ncc.getMaNV()});
         }
    }

     public static void LocListPhieuNhap()
    {
        modelphieunhap.setRowCount(0);
        listphieunhap = new LOADDULIEU().getListPhieuNhap();
        for(PHIEUNHAP pn : listphieunhap)
        {
            if(pn.getTrangThai().equals("1"))
            {
                 modelphieunhap.addRow(new Object[]{pn.getMaPN(),pn.getMaKho(),pn.getMaNV(),pn.getMaNCC(),pn.getNgayNhap(), vnmoney.format(pn.getTongTien()),"Đã nhập kho"});
            
            }
            else
            {
                 modelphieunhap.addRow(new Object[]{pn.getMaPN(),pn.getMaKho(),pn.getMaNV(),pn.getMaNCC(),pn.getNgayNhap(), vnmoney.format(pn.getTongTien()),"Chưa nhập kho"});
            }
           
        }
        
    }
    
    public static void LocListCTPN()
    {
        modelctpn.setRowCount(0);
        listctpn = new LOADDULIEU().getListCTPN();
        for(CTPN ctpn : listctpn)
        {
            modelctpn.addRow(new Object[]{ctpn.getMaPN(),ctpn.getMaSP(),ctpn.getTenSP(),ctpn.getDonViTinh(),ctpn.getSoLuong(),ctpn.getGiaNhap()});
        }
    }
    
    public static void LocListPhieuXuat()
    {
        modelphieuxuat.setRowCount(0);
        listphieuxuat = new LOADDULIEU().getListPhieuXuat();
        for(PHIEUXUAT px : listphieuxuat)
        {
            if(px.getTrangThai().equals("1"))
            {
                 modelphieuxuat.addRow(new Object[]{px.getMaPX(),px.getNgayXuat(),px.getMaKho(),px.getMaNV(), vnmoney.format(px.getTongTien()),"Đã xuất kho"});
            
            }
            else
            {
                 modelphieuxuat.addRow(new Object[]{px.getMaPX(),px.getNgayXuat(),px.getMaKho(),px.getMaNV(), vnmoney.format(px.getTongTien()),"Chưa xuất kho"});
            }
           
        }
        
    }
    
    public static void LocListCTPX()
    {
        modelctpx.setRowCount(0);
        listctpx = new LOADDULIEU().getListCTPX();
        for(CTPX ctpn : listctpx)
        {
            modelctpx.addRow(new Object[]{ctpn.getMaPX(),ctpn.getMaSP(),ctpn.getTenSP(),ctpn.getDonViTinh(),ctpn.getSoluong(),ctpn.getDongia()});
        }
    }
    
    public static void LocListChucVu()
    {
        modelchucvu.setRowCount(0);
        listchucvu = new LOADDULIEU().getListChucVu();
        for(CHUCVU cv : listchucvu)
        {
            modelchucvu.addRow(new Object[]{cv.getMaCV(),cv.getTenCV(),cv.getSoLuong(),vnmoney.format(cv.getLuong())});
        }
        
    }     
    
    public static void LocListTuDo()
    {
        modeltudo.setRowCount(0);
        listtudo = new LOADDULIEU().getListTuDo();
        for(TUDO td : listtudo)
        {
            modeltudo.addRow(new Object[]{td.getSoTu(),td.getMaKH(),td.getTenKH(),td.getSDT()});
        }
        
    } 

    public static void LocListSanPhamLoi()
    {
        modelsploi.setRowCount(0);
        listsploi = new LOADDULIEU().getListSanPhamLoi();
        for(SANPHAMLOI spl : listsploi)
        {
            modelsploi.addRow(new Object[]{spl.getMaSP(),spl.getTenSP(),spl.getSoLuong(),spl.getMaNCC(),spl.getDonGia(),spl.getLyDo(),spl.getThanhTien()});
        }
        
    }
    
    public static void LocListKho()
    {
        modelkho.setRowCount(0);
        listkho = new LOADDULIEU().getListKho();
        for(KHO spl : listkho)
        {
            modelkho.addRow(new Object[]{spl.getMaKho(),spl.getTenKho(),spl.getDiaChi()});
        }
        
    }
    
    public static void LocListThongTinKho()
    {
        modelthongtinkho.setRowCount(0);
        listthongtinkho = new LOADDULIEU().getListThongTinKho();
        for(THONGTINKHO spl : listthongtinkho)
        {
            modelthongtinkho.addRow(new Object[]{spl.getMaKho(),spl.getMaSP(),spl.getTenLoaiSP(),spl.getSoLuong(),spl.getDonViTinh(),spl.getNgayNhap()});
        }
        
    }
    
    public static void LocListTaiKhoan()
    {
        modeltaikhoan.setRowCount(0);
        listtaikhoan = new LOADDULIEU().getListTaiKhoan();
        for(TAIKHOAN tk:listtaikhoan)
        {
            if(tk.getTrangThai().equals("1"))
            {
            modeltaikhoan.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Mở"});
            
            }
            else
            {
                modeltaikhoan.addRow(new Object[]{tk.getTaiKhoan() , tk.getMaNV() , "Khóa"});
            }
        }
    }
    public static void LocListNhanVien()
    {
        modelnhanvien.setRowCount(0);
        listnhanvien = new LOADDULIEU().getListNhanVien();
        for(NHANVIEN nv:listnhanvien)
        {
            if(nv.getGioiTinh().equals("1"))
            {
            modelnhanvien.addRow(new Object[]{nv.getMaNV(),nv.getHoTen(),"Nam",nv.getNgaySinh(),nv.getSDT(),nv.getDiaChi(),nv.getEmail(),nv.getTenChucVu()});
            
            }
            else
            {
                modelnhanvien.addRow(new Object[]{nv.getMaNV(),nv.getHoTen(),"Nữ",nv.getNgaySinh(),nv.getSDT(),nv.getDiaChi(),nv.getEmail(),nv.getTenChucVu()});
            }
        }
    }
    public static void LocThongKe()
    {
        modeltksp.setRowCount(0);
        tktc = new BUS_THONGKE().ThongKeTatCa();
        for(CTHD h : tktc)
        {
            modeltksp.addRow(new Object[]{h.getTenSP(),h.getSoLuong(),vnmoney.format(h.getDongia()),vnmoney.format(h.getThanhTien())});
        }
        lbtksp.setText("Tổng lượng sản phẩm đã bán ra được");
        os = new BUS_THONGKE().TongTatCa();
        lbtongdoanhthu.setText(vnmoney.format(os));
    }
    public static void LocListKhuyenMai()
    {
        modelkm.setRowCount(0);
        listkm = new LOADDULIEU().getListKhuyenMai();
        for(KHUYENMAI km : listkm)
        {
            modelkm.addRow(new Object[]{km.getMaKM(),km.getTenCT(),km.getNgayBatDau(),km.getNgayKetThuc(),km.getPhanTram()});
        }
    }
    
    public static void setHeader(JTable tbl)
    {
        tbl.getTableHeader().setBackground(new Color(8,112,150));
        tbl.getTableHeader().setOpaque(false);
        tbl.getTableHeader().setForeground(Color.WHITE);
    }
    private void hienthingay()
    {
        Date tod = new Date();
        SimpleDateFormat formatcc = new SimpleDateFormat("dd-M-yyyy");
        lbtoday.setText(formatcc.format(tod));
    }
    private void hienthigio()
    {
        new Timer(0,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 Date tod = new Date();
        SimpleDateFormat formatcc = new SimpleDateFormat("hh:mm:ss");
        lbtime.setText(formatcc.format(tod));
            }
        }).start();
       
    }
     public static String taochuoi(int dodai) {
       StringBuilder sb = new StringBuilder();
       Random random = new Random();
       for (int i = 0; i < dodai; i++) {
           sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                   .length())));
       }

       return sb.toString();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        groupthongke = new javax.swing.ButtonGroup();
        fORM_CTHDadmin1 = new GUI.FORM_CTHDadmin();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        chovaokho = new javax.swing.JMenuItem();
        baoloisp = new javax.swing.JMenuItem();
        menu = new javax.swing.JPanel();
        lbdshd = new javax.swing.JLabel();
        lbqlsp = new javax.swing.JLabel();
        lbttcn = new javax.swing.JLabel();
        lbdspn = new javax.swing.JLabel();
        lbthongkedoanhthu = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        lbkm = new javax.swing.JLabel();
        lbkhachhang = new javax.swing.JLabel();
        lbqlcv = new javax.swing.JLabel();
        lbqltk = new javax.swing.JLabel();
        lbqlnv = new javax.swing.JLabel();
        lbqltudo = new javax.swing.JLabel();
        lbsploi = new javax.swing.JLabel();
        lbdspx = new javax.swing.JLabel();
        lbkktk = new javax.swing.JLabel();
        lbcalam = new javax.swing.JLabel();
        lbthongkeloinhuan = new javax.swing.JLabel();
        lbncc = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        dshd = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldshd = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txttimkiem6 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        dspn = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblphieunhap = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblctpn = new javax.swing.JTable();
        combosp = new javax.swing.JComboBox<>();
        slnhap = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        dvtpn = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        ttcn = new javax.swing.JPanel();
        pnttcn = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ttdnmanv = new javax.swing.JTextField();
        ttdnhoten = new javax.swing.JTextField();
        ttdnngaysinh = new javax.swing.JTextField();
        ttdndiachi = new javax.swing.JTextField();
        ttdnsdt = new javax.swing.JTextField();
        ttdnemail = new javax.swing.JTextField();
        bienmat = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        me = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        qlsp = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblqlsp = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        refresh = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        thongke = new javax.swing.JPanel();
        rdo1 = new javax.swing.JRadioButton();
        rdo2 = new javax.swing.JRadioButton();
        rdo3 = new javax.swing.JRadioButton();
        cbthang = new javax.swing.JComboBox<>();
        cbnam = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblthongke = new javax.swing.JTable();
        day3 = new com.toedter.calendar.JDateChooser();
        day1 = new com.toedter.calendar.JDateChooser();
        day2 = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbtongdoanhthu = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblcttk = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbltksp = new javax.swing.JTable();
        lbtksp = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        ctkm = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblkm = new javax.swing.JTable();
        txtTenCT = new javax.swing.JTextField();
        txtNgayBD = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnThemKM = new javax.swing.JLabel();
        btnReset1 = new javax.swing.JLabel();
        txtPhanTram = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtMaCT = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        khachhang = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblkhachhang = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblctkh = new javax.swing.JTable();
        txtsdtkh = new javax.swing.JTextField();
        txttenkh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txttimkiem3 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        qlcv = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblqlcv = new javax.swing.JTable();
        txttimkiem1 = new javax.swing.JTextField();
        refresh1 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        qltk = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tbltaikhoan = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        txtMaNV4 = new javax.swing.JTextField();
        txtMatKhau4 = new javax.swing.JTextField();
        txtTaiKhoan4 = new javax.swing.JTextField();
        lbTaiKhoan4 = new javax.swing.JLabel();
        lbMatKhau4 = new javax.swing.JLabel();
        lbMaNV4 = new javax.swing.JLabel();
        txtTrangThai4 = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        btnReset5 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        btnThem4 = new javax.swing.JLabel();
        btnSua4 = new javax.swing.JLabel();
        dsnv = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblqlnv = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txttimkiem4 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        qltudo = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblqltudo = new javax.swing.JTable();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txttimkiem2 = new javax.swing.JTextField();
        refresh2 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        sploi = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblsploi = new javax.swing.JTable();
        txtTenCT1 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        btnThemKM1 = new javax.swing.JLabel();
        btnXoaKM1 = new javax.swing.JLabel();
        btnReset2 = new javax.swing.JLabel();
        txtPhanTram1 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtMaCT1 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txttimkiem5 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtMaNCC = new javax.swing.JTextField();
        btnSua5 = new javax.swing.JLabel();
        dspx = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblctpx = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblphieuxuat = new javax.swing.JTable();
        combosp1 = new javax.swing.JComboBox<>();
        slnhap1 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        dvtpn1 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        kktk = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblthongtinkho = new javax.swing.JTable();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblkho = new javax.swing.JTable();
        theoquy = new javax.swing.JRadioButton();
        theothang = new javax.swing.JRadioButton();
        theonam = new javax.swing.JRadioButton();
        jComboBoxQuy = new javax.swing.JComboBox<>();
        cbthang2 = new javax.swing.JComboBox<>();
        cbnam3 = new javax.swing.JComboBox<>();
        cbnam4 = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        ncc = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        txtmancc = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        txttenncc = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblncc = new javax.swing.JTable();
        jLabel82 = new javax.swing.JLabel();
        thongkeloinhuan = new javax.swing.JPanel();
        rdo4 = new javax.swing.JRadioButton();
        rdo5 = new javax.swing.JRadioButton();
        rdo6 = new javax.swing.JRadioButton();
        cbthang1 = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tbltkln = new javax.swing.JTable();
        day5 = new com.toedter.calendar.JDateChooser();
        day6 = new com.toedter.calendar.JDateChooser();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        lbtongdoanhthu1 = new javax.swing.JLabel();
        cbquy = new javax.swing.JComboBox<>();
        calam = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tblphancongca = new javax.swing.JTable();
        jLabel91 = new javax.swing.JLabel();
        btnThemKM3 = new javax.swing.JLabel();
        btnXoaKM3 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        tblcalam = new javax.swing.JTable();
        btnThemKM4 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        task = new javax.swing.JPanel();
        lbMini = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        lbtoday = new javax.swing.JLabel();
        lbtime = new javax.swing.JLabel();
        lbhello = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jPopupMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPopupMenu1MouseReleased(evt);
            }
        });

        chovaokho.setText("Cho vào Kho");
        chovaokho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chovaokhoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(chovaokho);

        baoloisp.setText("Báo lỗi sản phẩm");
        baoloisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baoloispActionPerformed(evt);
            }
        });
        jPopupMenu1.add(baoloisp);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));

        menu.setBackground(new java.awt.Color(61, 145, 106));
        menu.setMinimumSize(new java.awt.Dimension(240, 820));
        menu.setPreferredSize(new java.awt.Dimension(240, 820));
        menu.setLayout(null);

        lbdshd.setBackground(new java.awt.Color(255, 255, 255));
        lbdshd.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbdshd.setForeground(new java.awt.Color(255, 255, 255));
        lbdshd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbdshd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconhd.png"))); // NOI18N
        lbdshd.setText("DANH SÁCH HÓA ĐƠN");
        lbdshd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbdshdMousePressed(evt);
            }
        });
        menu.add(lbdshd);
        lbdshd.setBounds(0, 50, 240, 50);

        lbqlsp.setBackground(new java.awt.Color(255, 255, 255));
        lbqlsp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbqlsp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbqlsp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconsanpham.png"))); // NOI18N
        lbqlsp.setText("QUẢN LÝ SẢN PHẨM");
        lbqlsp.setOpaque(true);
        lbqlsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbqlspMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbqlspMousePressed(evt);
            }
        });
        menu.add(lbqlsp);
        lbqlsp.setBounds(0, 0, 240, 50);

        lbttcn.setBackground(new java.awt.Color(255, 255, 255));
        lbttcn.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbttcn.setForeground(new java.awt.Color(255, 255, 255));
        lbttcn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbttcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbttcn.setText("THÔNG TIN CÁ NHÂN");
        lbttcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbttcnMousePressed(evt);
            }
        });
        menu.add(lbttcn);
        lbttcn.setBounds(0, 400, 240, 50);

        lbdspn.setBackground(new java.awt.Color(255, 255, 255));
        lbdspn.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbdspn.setForeground(new java.awt.Color(255, 255, 255));
        lbdspn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbdspn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconphieunhap.png"))); // NOI18N
        lbdspn.setText("PHIẾU NHẬP HÀNG");
        lbdspn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbdspnMousePressed(evt);
            }
        });
        menu.add(lbdspn);
        lbdspn.setBounds(0, 100, 240, 50);

        lbthongkedoanhthu.setBackground(new java.awt.Color(255, 255, 255));
        lbthongkedoanhthu.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbthongkedoanhthu.setForeground(new java.awt.Color(255, 255, 255));
        lbthongkedoanhthu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbthongkedoanhthu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconthongke.png"))); // NOI18N
        lbthongkedoanhthu.setText("THỐNG KÊ DOANH THU");
        lbthongkedoanhthu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbthongkedoanhthuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbthongkedoanhthuMouseReleased(evt);
            }
        });
        menu.add(lbthongkedoanhthu);
        lbthongkedoanhthu.setBounds(0, 200, 240, 50);

        logout.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/logout.png"))); // NOI18N
        logout.setText("ĐĂNG XUẤT");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutMousePressed(evt);
            }
        });
        menu.add(logout);
        logout.setBounds(0, 870, 140, 50);

        lbkm.setBackground(new java.awt.Color(255, 255, 255));
        lbkm.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbkm.setForeground(new java.awt.Color(255, 255, 255));
        lbkm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbkm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/coupon.png"))); // NOI18N
        lbkm.setText("KHUYẾN MÃI");
        lbkm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbkmMousePressed(evt);
            }
        });
        menu.add(lbkm);
        lbkm.setBounds(0, 300, 240, 50);

        lbkhachhang.setBackground(new java.awt.Color(255, 255, 255));
        lbkhachhang.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbkhachhang.setForeground(new java.awt.Color(255, 255, 255));
        lbkhachhang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbkhachhang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/customer.png"))); // NOI18N
        lbkhachhang.setText("KHÁCH HÀNG");
        lbkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbkhachhangMousePressed(evt);
            }
        });
        menu.add(lbkhachhang);
        lbkhachhang.setBounds(0, 350, 240, 50);

        lbqlcv.setBackground(new java.awt.Color(255, 255, 255));
        lbqlcv.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbqlcv.setForeground(new java.awt.Color(255, 255, 255));
        lbqlcv.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbqlcv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbqlcv.setText("QUẢN LÝ CHỨC VỤ");
        lbqlcv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbqlcvMousePressed(evt);
            }
        });
        menu.add(lbqlcv);
        lbqlcv.setBounds(0, 450, 240, 50);

        lbqltk.setBackground(new java.awt.Color(255, 255, 255));
        lbqltk.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbqltk.setForeground(new java.awt.Color(255, 255, 255));
        lbqltk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbqltk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbqltk.setText("QUẢN LÝ TÀI KHOẢN");
        lbqltk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbqltkMousePressed(evt);
            }
        });
        menu.add(lbqltk);
        lbqltk.setBounds(0, 500, 240, 50);

        lbqlnv.setBackground(new java.awt.Color(255, 255, 255));
        lbqlnv.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbqlnv.setForeground(new java.awt.Color(255, 255, 255));
        lbqlnv.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbqlnv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbqlnv.setText("QUẢN LÝ NHÂN VIÊN");
        lbqlnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbqlnvMousePressed(evt);
            }
        });
        menu.add(lbqlnv);
        lbqlnv.setBounds(0, 550, 240, 50);

        lbqltudo.setBackground(new java.awt.Color(255, 255, 255));
        lbqltudo.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbqltudo.setForeground(new java.awt.Color(255, 255, 255));
        lbqltudo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbqltudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbqltudo.setText("QUẢN LÝ TỦ ĐỒ");
        lbqltudo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbqltudoMousePressed(evt);
            }
        });
        menu.add(lbqltudo);
        lbqltudo.setBounds(0, 600, 240, 50);

        lbsploi.setBackground(new java.awt.Color(255, 255, 255));
        lbsploi.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbsploi.setForeground(new java.awt.Color(255, 255, 255));
        lbsploi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbsploi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconsanpham.png"))); // NOI18N
        lbsploi.setText("SẢN PHẨM LỖI");
        lbsploi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbsploiMousePressed(evt);
            }
        });
        menu.add(lbsploi);
        lbsploi.setBounds(0, 650, 240, 50);

        lbdspx.setBackground(new java.awt.Color(255, 255, 255));
        lbdspx.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbdspx.setForeground(new java.awt.Color(255, 255, 255));
        lbdspx.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbdspx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconphieunhap.png"))); // NOI18N
        lbdspx.setText("PHIẾU XUẤT HÀNG");
        lbdspx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbdspxMousePressed(evt);
            }
        });
        menu.add(lbdspx);
        lbdspx.setBounds(0, 150, 240, 50);

        lbkktk.setBackground(new java.awt.Color(255, 255, 255));
        lbkktk.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbkktk.setForeground(new java.awt.Color(255, 255, 255));
        lbkktk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbkktk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbkktk.setText("KIỂM KÊ TỒN KHO");
        lbkktk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbkktkMousePressed(evt);
            }
        });
        menu.add(lbkktk);
        lbkktk.setBounds(0, 700, 240, 50);

        lbcalam.setBackground(new java.awt.Color(255, 255, 255));
        lbcalam.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbcalam.setForeground(new java.awt.Color(255, 255, 255));
        lbcalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbcalam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbcalam.setText("QUẢN LÝ CA LÀM");
        lbcalam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbcalamMousePressed(evt);
            }
        });
        menu.add(lbcalam);
        lbcalam.setBounds(0, 750, 240, 50);

        lbthongkeloinhuan.setBackground(new java.awt.Color(255, 255, 255));
        lbthongkeloinhuan.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbthongkeloinhuan.setForeground(new java.awt.Color(255, 255, 255));
        lbthongkeloinhuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbthongkeloinhuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconthongke.png"))); // NOI18N
        lbthongkeloinhuan.setText("THỐNG KÊ LỢI NHUẬN");
        lbthongkeloinhuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbthongkeloinhuanMousePressed(evt);
            }
        });
        menu.add(lbthongkeloinhuan);
        lbthongkeloinhuan.setBounds(0, 250, 240, 50);

        lbncc.setBackground(new java.awt.Color(255, 255, 255));
        lbncc.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbncc.setForeground(new java.awt.Color(255, 255, 255));
        lbncc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbncc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconnv.png"))); // NOI18N
        lbncc.setText("NHÀ CUNG CẤP");
        lbncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbnccMousePressed(evt);
            }
        });
        menu.add(lbncc);
        lbncc.setBounds(0, 800, 240, 50);

        panel.setBackground(new java.awt.Color(0, 0, 0));
        panel.setLayout(new java.awt.CardLayout());

        dshd.setBackground(new java.awt.Color(47, 48, 48));

        tbldshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày xuất", "Mã khuyến mãi", "Tổng tiền", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldshd.setRowHeight(45);
        tbldshd.setShowVerticalLines(false);
        tbldshd.getTableHeader().setReorderingAllowed(false);
        tbldshd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldshdMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbldshd);

        jLabel1.setBackground(new java.awt.Color(61, 145, 106));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        jLabel1.setText("Tạo hóa đơn");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(61, 145, 106));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Xem chi tiết");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(61, 145, 106));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/pdf.png"))); // NOI18N
        jLabel8.setText("   In PDF");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        txttimkiem6.setToolTipText("");
        txttimkiem6.setBorder(null);
        txttimkiem6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiem6KeyReleased(evt);
            }
        });

        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        jLabel64.setBackground(new java.awt.Color(61, 145, 106));
        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        jLabel64.setText("  Hủy");
        jLabel64.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel64.setOpaque(true);
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel64MousePressed(evt);
            }
        });

        javax.swing.GroupLayout dshdLayout = new javax.swing.GroupLayout(dshd);
        dshd.setLayout(dshdLayout);
        dshdLayout.setHorizontalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dshdLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE))
                    .addGroup(dshdLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel92)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem6, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        dshdLayout.setVerticalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dshdLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dshdLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel92)
                            .addComponent(txttimkiem6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dshdLayout.createSequentialGroup()
                        .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel.add(dshd, "dshd");

        dspn.setBackground(new java.awt.Color(47, 48, 48));
        dspn.setLayout(null);

        tblphieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu nhập", "Mã Kho", "Mã Nhân viên", "Mã NCC", "Ngày nhập", "Tổng tiền", "Trạng Thái"
            }
        ));
        tblphieunhap.setRowHeight(35);
        jScrollPane10.setViewportView(tblphieunhap);
        if (tblphieunhap.getColumnModel().getColumnCount() > 0) {
            tblphieunhap.getColumnModel().getColumn(3).setResizable(false);
        }

        dspn.add(jScrollPane10);
        jScrollPane10.setBounds(15, 379, 1080, 390);

        tblctpn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PN", "Mã sản phẩm", "Tên sản phẩm", "Đơn vị tính", "Số lượng", "Giá nhập"
            }
        ));
        tblctpn.setRowHeight(35);
        jScrollPane9.setViewportView(tblctpn);
        if (tblctpn.getColumnModel().getColumnCount() > 0) {
            tblctpn.getColumnModel().getColumn(0).setHeaderValue("Mã PN");
        }

        dspn.add(jScrollPane9);
        jScrollPane9.setBounds(15, 0, 640, 350);

        combosp.setMaximumRowCount(100);
        combosp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combospItemStateChanged(evt);
            }
        });
        combosp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combospFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                combospFocusLost(evt);
            }
        });
        combosp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combospActionPerformed(evt);
            }
        });
        dspn.add(combosp);
        combosp.setBounds(680, 50, 340, 40);
        dspn.add(slnhap);
        slnhap.setBounds(790, 180, 180, 35);

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Số lượng nhập");
        dspn.add(jLabel44);
        jLabel44.setBounds(680, 190, 110, 16);

        dvtpn.setBackground(new java.awt.Color(255, 255, 255));
        dvtpn.setForeground(new java.awt.Color(255, 255, 255));
        dvtpn.setText("jLabel45");
        dspn.add(dvtpn);
        dvtpn.setBounds(790, 140, 170, 16);

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Đơn vị tính :");
        dspn.add(jLabel45);
        jLabel45.setBounds(680, 140, 100, 16);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Chọn sản phẩm");
        dspn.add(jLabel46);
        jLabel46.setBounds(680, 10, 210, 29);

        jLabel47.setBackground(new java.awt.Color(61, 145, 106));
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        jLabel47.setText("Thêm");
        jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel47.setOpaque(true);
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel47MousePressed(evt);
            }
        });
        dspn.add(jLabel47);
        jLabel47.setBounds(680, 238, 164, 45);

        jLabel48.setBackground(new java.awt.Color(61, 145, 106));
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel48.setText("Tạo phiếu nhập");
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel48.setOpaque(true);
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel48MousePressed(evt);
            }
        });
        dspn.add(jLabel48);
        jLabel48.setBounds(680, 296, 164, 45);

        jLabel49.setBackground(new java.awt.Color(61, 145, 106));
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/xls.png"))); // NOI18N
        jLabel49.setText("Xuất Excel");
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel49.setOpaque(true);
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel49MousePressed(evt);
            }
        });
        dspn.add(jLabel49);
        jLabel49.setBounds(856, 296, 164, 45);

        jLabel50.setBackground(new java.awt.Color(61, 145, 106));
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Reset phiếu nhập");
        jLabel50.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel50.setOpaque(true);
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel50MousePressed(evt);
            }
        });
        dspn.add(jLabel50);
        jLabel50.setBounds(856, 238, 164, 45);

        panel.add(dspn, "dspn");

        ttcn.setBackground(new java.awt.Color(47, 48, 48));

        pnttcn.setBackground(new java.awt.Color(55, 56, 55));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("THÔNG TIN CÁ NHÂN");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("_________________________________");

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("_________________________________");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("_________________________________");

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("_________________________________");

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("_________________________________");

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("_________________________________");

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("EMAIL");

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("MÃ NHÂN VIÊN");

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("HỌ TÊN");

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("NGÀY SINH");

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("ĐỊA CHỈ");

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("SỐ ĐIỆN THOẠI");

        ttdnmanv.setEditable(false);
        ttdnmanv.setForeground(new java.awt.Color(255, 255, 255));
        ttdnmanv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnmanv.setBorder(null);
        ttdnmanv.setOpaque(false);

        ttdnhoten.setEditable(false);
        ttdnhoten.setForeground(new java.awt.Color(255, 255, 255));
        ttdnhoten.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnhoten.setBorder(null);
        ttdnhoten.setOpaque(false);

        ttdnngaysinh.setEditable(false);
        ttdnngaysinh.setForeground(new java.awt.Color(255, 255, 255));
        ttdnngaysinh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnngaysinh.setBorder(null);
        ttdnngaysinh.setOpaque(false);

        ttdndiachi.setEditable(false);
        ttdndiachi.setForeground(new java.awt.Color(255, 255, 255));
        ttdndiachi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdndiachi.setBorder(null);
        ttdndiachi.setOpaque(false);

        ttdnsdt.setEditable(false);
        ttdnsdt.setForeground(new java.awt.Color(255, 255, 255));
        ttdnsdt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnsdt.setBorder(null);
        ttdnsdt.setOpaque(false);

        ttdnemail.setEditable(false);
        ttdnemail.setForeground(new java.awt.Color(255, 255, 255));
        ttdnemail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ttdnemail.setBorder(null);
        ttdnemail.setOpaque(false);

        bienmat.setText("jButton2");
        bienmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bienmatActionPerformed(evt);
            }
        });

        jLabel30.setBackground(new java.awt.Color(61, 145, 106));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Đổi mật khẩu");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel30.setOpaque(true);
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel30MousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnttcnLayout = new javax.swing.GroupLayout(pnttcn);
        pnttcn.setLayout(pnttcnLayout);
        pnttcnLayout.setHorizontalGroup(
            pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnttcnLayout.createSequentialGroup()
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdndiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnttcnLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(ttdnemail, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bienmat)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        pnttcnLayout.setVerticalGroup(
            pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnttcnLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel19)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel23))
                    .addComponent(ttdnhoten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdndiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel21)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel22)))
                .addGap(30, 30, 30)
                .addGroup(pnttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ttdnemail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnttcnLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel20)))
                .addGap(32, 32, 32)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bienmat))
        );

        me.setBackground(new java.awt.Color(153, 153, 153));
        me.setOpaque(true);

        jLabel32.setBackground(new java.awt.Color(61, 145, 106));
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Cập nhật");
        jLabel32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel32.setOpaque(true);
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel32MousePressed(evt);
            }
        });

        jLabel38.setBackground(new java.awt.Color(61, 145, 106));
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Chọn ảnh đại diện");
        jLabel38.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel38.setOpaque(true);
        jLabel38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel38MousePressed(evt);
            }
        });

        javax.swing.GroupLayout ttcnLayout = new javax.swing.GroupLayout(ttcn);
        ttcn.setLayout(ttcnLayout);
        ttcnLayout.setHorizontalGroup(
            ttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ttcnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnttcn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(me, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        ttcnLayout.setVerticalGroup(
            ttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ttcnLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(ttcnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ttcnLayout.createSequentialGroup()
                        .addComponent(me, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnttcn, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        panel.add(ttcn, "ttcn");

        qlsp.setBackground(new java.awt.Color(47, 48, 48));

        tblqlsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", " Loại sản phẩm", "Số lượng", "Đơn vị tính", "Đơn giá bán", "Đơn giá nhập", "Nhà cung cấp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblqlsp.setOpaque(false);
        tblqlsp.setRowHeight(45);
        tblqlsp.setShowVerticalLines(false);
        tblqlsp.getTableHeader().setReorderingAllowed(false);
        tblqlsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblqlspMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblqlsp);
        if (tblqlsp.getColumnModel().getColumnCount() > 0) {
            tblqlsp.getColumnModel().getColumn(5).setResizable(false);
            tblqlsp.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel3.setBackground(new java.awt.Color(61, 145, 106));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel3.setText("Thêm");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(61, 145, 106));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        jLabel4.setText(" Xóa");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(61, 145, 106));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel5.setText(" Sửa");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        txttimkiem.setToolTipText("");
        txttimkiem.setBorder(null);
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                refreshMousePressed(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        jLabel37.setBackground(new java.awt.Color(61, 145, 106));
        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Thông tin khác");
        jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel37.setOpaque(true);
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel37MousePressed(evt);
            }
        });

        javax.swing.GroupLayout qlspLayout = new javax.swing.GroupLayout(qlsp);
        qlsp.setLayout(qlspLayout);
        qlspLayout.setHorizontalGroup(
            qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qlspLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(refresh)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        qlspLayout.setVerticalGroup(
            qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel31)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel.add(qlsp, "qlsp");

        thongke.setBackground(new java.awt.Color(47, 48, 48));

        groupthongke.add(rdo1);
        rdo1.setForeground(new java.awt.Color(255, 255, 255));
        rdo1.setText("Theo khoảng thời gian");
        rdo1.setOpaque(false);

        groupthongke.add(rdo2);
        rdo2.setForeground(new java.awt.Color(255, 255, 255));
        rdo2.setText("Theo tháng");
        rdo2.setOpaque(false);

        groupthongke.add(rdo3);
        rdo3.setForeground(new java.awt.Color(255, 255, 255));
        rdo3.setText("Theo ngày");
        rdo3.setOpaque(false);

        cbthang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        cbnam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021", "2022", "2023" }));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Từ");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Đến");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tháng");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Năm");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ngày");

        jLabel16.setBackground(new java.awt.Color(61, 145, 106));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Lọc");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.setOpaque(true);
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel16MousePressed(evt);
            }
        });

        tblthongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã NV", "Ngày xuất", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblthongke.setRowHeight(40);
        tblthongke.setShowVerticalLines(false);
        tblthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblthongkeMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblthongke);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tổng");

        lbtongdoanhthu.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbtongdoanhthu.setForeground(new java.awt.Color(255, 255, 255));
        lbtongdoanhthu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbtongdoanhthu.setText("0");

        tblcttk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblcttk.setRowHeight(40);
        jScrollPane4.setViewportView(tblcttk);

        tbltksp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Tổng số lượng bán ra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltksp.setRowHeight(40);
        jScrollPane5.setViewportView(tbltksp);

        lbtksp.setForeground(new java.awt.Color(255, 255, 255));
        lbtksp.setText("jLabel2");

        jLabel100.setBackground(new java.awt.Color(61, 145, 106));
        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setText("IN BÁO CÁO");
        jLabel100.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel100.setOpaque(true);
        jLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel100MousePressed(evt);
            }
        });

        javax.swing.GroupLayout thongkeLayout = new javax.swing.GroupLayout(thongke);
        thongke.setLayout(thongkeLayout);
        thongkeLayout.setHorizontalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdo1)
                    .addComponent(rdo2)
                    .addComponent(rdo3)
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbthang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbnam, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(25, 25, 25)
                                .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(day2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(day3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel17)))))
                .addGap(30, 30, 30)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbtongdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbtksp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        thongkeLayout.setVerticalGroup(
            thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkeLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lbtksp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(thongkeLayout.createSequentialGroup()
                                .addComponent(rdo1)
                                .addGap(11, 11, 11)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12)
                                    .addComponent(day2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(rdo2)
                                .addGap(11, 11, 11)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbthang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addComponent(rdo3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(day3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(41, 41, 41)
                                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtongdoanhthu)
                    .addComponent(jLabel18))
                .addGap(28, 28, 28))
        );

        panel.add(thongke, "tk");

        ctkm.setBackground(new java.awt.Color(47, 48, 48));

        tblkm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khuyến Mãi", "Tên CT Khuyến Mãi", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giá trị % "
            }
        ));
        tblkm.setRowHeight(30);
        tblkm.getTableHeader().setReorderingAllowed(false);
        tblkm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblkmMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblkm);

        txtTenCT.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtNgayBD.setDateFormatString("dd/MM/yyyy");
        txtNgayBD.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        txtNgayKT.setDateFormatString("dd/MM/yyyy");
        txtNgayKT.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Tên CTKM ");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Ngày bắt đầu");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Ngày kết thúc");

        btnThemKM.setBackground(new java.awt.Color(61, 145, 106));
        btnThemKM.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThemKM.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        btnThemKM.setText(" Thêm");
        btnThemKM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKM.setOpaque(true);
        btnThemKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemKMMousePressed(evt);
            }
        });

        btnReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        btnReset1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReset1MousePressed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Giá trị (%)");

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("Mã CTKM ");

        txtMaCT.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        jLabel103.setBackground(new java.awt.Color(61, 145, 106));
        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel103.setText(" Sửa");
        jLabel103.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel103.setOpaque(true);
        jLabel103.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel103MousePressed(evt);
            }
        });

        javax.swing.GroupLayout ctkmLayout = new javax.swing.GroupLayout(ctkm);
        ctkm.setLayout(ctkmLayout);
        ctkmLayout.setHorizontalGroup(
            ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctkmLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaCT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ctkmLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhanTram, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ctkmLayout.createSequentialGroup()
                                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset1)))))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        ctkmLayout.setVerticalGroup(
            ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctkmLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ctkmLayout.createSequentialGroup()
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaCT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62))
                        .addGap(29, 29, 29)
                        .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ctkmLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel34)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel35)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel36))
                            .addGroup(ctkmLayout.createSequentialGroup()
                                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33))
                                .addGap(33, 33, 33)
                                .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtPhanTram, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnReset1)))))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        panel.add(ctkm, "ctkm");

        khachhang.setBackground(new java.awt.Color(47, 48, 48));

        tblkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Họ tên", "Số điện thoại", "Tổng hóa đơn", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblkhachhang.setRowHeight(40);
        tblkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblkhachhangMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tblkhachhang);

        tblctkh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày mua", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblctkh.setRowHeight(40);
        tblctkh.setShowVerticalLines(false);
        jScrollPane8.setViewportView(tblctkh);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Số điện thoại");

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Họ tên khách hàng");

        jLabel40.setBackground(new java.awt.Color(61, 145, 106));
        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Thêm");
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel40.setOpaque(true);
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel40MousePressed(evt);
            }
        });

        jLabel41.setBackground(new java.awt.Color(61, 145, 106));
        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Xóa");
        jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel41.setOpaque(true);
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel41MousePressed(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel42MousePressed(evt);
            }
        });

        jLabel43.setBackground(new java.awt.Color(61, 145, 106));
        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Sửa");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel43.setOpaque(true);
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel43MousePressed(evt);
            }
        });

        txttimkiem3.setToolTipText("");
        txttimkiem3.setBorder(null);
        txttimkiem3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiem3KeyReleased(evt);
            }
        });

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        javax.swing.GroupLayout khachhangLayout = new javax.swing.GroupLayout(khachhang);
        khachhang.setLayout(khachhangLayout);
        khachhangLayout.setHorizontalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangLayout.createSequentialGroup()
                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel39))
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addComponent(txtsdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel71)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttimkiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))))
                .addContainerGap())
        );
        khachhangLayout.setVerticalGroup(
            khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(khachhangLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel39)
                .addGap(9, 9, 9)
                .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khachhangLayout.createSequentialGroup()
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtsdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, khachhangLayout.createSequentialGroup()
                                .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71)
                                    .addComponent(txttimkiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                            .addComponent(jScrollPane8)))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panel.add(khachhang, "nsx");

        qlcv.setBackground(new java.awt.Color(47, 48, 48));

        tblqlcv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã chức vụ", "Tên chức vụ", "Số lượng", "Lương"
            }
        ));
        tblqlcv.setOpaque(false);
        tblqlcv.setRowHeight(45);
        tblqlcv.setShowVerticalLines(false);
        tblqlcv.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tblqlcv);

        txttimkiem1.setToolTipText("");
        txttimkiem1.setBorder(null);
        txttimkiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiem1KeyReleased(evt);
            }
        });

        refresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        refresh1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                refresh1MousePressed(evt);
            }
        });

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        jLabel51.setBackground(new java.awt.Color(61, 145, 106));
        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel51.setText("Thêm");
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel51.setOpaque(true);
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel51MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel51MousePressed(evt);
            }
        });

        jLabel52.setBackground(new java.awt.Color(61, 145, 106));
        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        jLabel52.setText(" Xóa");
        jLabel52.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel52.setOpaque(true);
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel52MousePressed(evt);
            }
        });

        jLabel105.setBackground(new java.awt.Color(61, 145, 106));
        jLabel105.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel105.setText(" Sửa");
        jLabel105.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel105.setOpaque(true);
        jLabel105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel105MousePressed(evt);
            }
        });

        javax.swing.GroupLayout qlcvLayout = new javax.swing.GroupLayout(qlcv);
        qlcv.setLayout(qlcvLayout);
        qlcvLayout.setHorizontalGroup(
            qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qlcvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11)
                    .addGroup(qlcvLayout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(refresh1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        qlcvLayout.setVerticalGroup(
            qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlcvLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(refresh1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txttimkiem1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        panel.add(qlcv, "qlcv");

        qltk.setBackground(new java.awt.Color(47, 48, 48));
        qltk.setPreferredSize(new java.awt.Dimension(937, 780));

        tbltaikhoan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbltaikhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tài khoản", "Mã nhân viên", "Tình trạng"
            }
        ));
        tbltaikhoan.setRowHeight(35);
        tbltaikhoan.getTableHeader().setReorderingAllowed(false);
        tbltaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbltaikhoantblqltkMousePressed(evt);
            }
        });
        jScrollPane19.setViewportView(tbltaikhoan);

        jPanel7.setBackground(new java.awt.Color(47, 48, 48));
        jPanel7.setLayout(null);

        txtMaNV4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMaNV4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNV4txtMaNVActionPerformed(evt);
            }
        });
        jPanel7.add(txtMaNV4);
        txtMaNV4.setBounds(0, 190, 158, 40);

        txtMatKhau4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtMatKhau4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatKhau4txtMatKhauActionPerformed(evt);
            }
        });
        jPanel7.add(txtMatKhau4);
        txtMatKhau4.setBounds(0, 110, 159, 40);

        txtTaiKhoan4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jPanel7.add(txtTaiKhoan4);
        txtTaiKhoan4.setBounds(0, 20, 161, 40);

        lbTaiKhoan4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbTaiKhoan4.setForeground(new java.awt.Color(255, 255, 255));
        lbTaiKhoan4.setText("jLabel5");
        jPanel7.add(lbTaiKhoan4);
        lbTaiKhoan4.setBounds(0, 20, 160, 40);

        lbMatKhau4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbMatKhau4.setForeground(new java.awt.Color(255, 255, 255));
        lbMatKhau4.setText("jLabel6");
        jPanel7.add(lbMatKhau4);
        lbMatKhau4.setBounds(0, 110, 160, 40);

        lbMaNV4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbMaNV4.setForeground(new java.awt.Color(255, 255, 255));
        lbMaNV4.setText("jLabel7");
        jPanel7.add(lbMaNV4);
        lbMaNV4.setBounds(0, 190, 160, 40);

        txtTrangThai4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTrangThai4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mở", "Khóa" }));
        txtTrangThai4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel7.add(txtTrangThai4);
        txtTrangThai4.setBounds(0, 280, 100, 40);

        jLabel86.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Tài khoản");

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Mật khẩu");

        jLabel88.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Mã nhân viên");

        btnReset5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        btnReset5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReset5btnResetMousePressed(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Trạng thái");

        btnThem4.setForeground(new java.awt.Color(255, 255, 255));
        btnThem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/them.png"))); // NOI18N
        btnThem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThem4btnThemMousePressed(evt);
            }
        });

        btnSua4.setForeground(new java.awt.Color(255, 255, 255));
        btnSua4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/sua.png"))); // NOI18N
        btnSua4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSua4btnSuaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout qltkLayout = new javax.swing.GroupLayout(qltk);
        qltk.setLayout(qltkLayout);
        qltkLayout.setHorizontalGroup(
            qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qltkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(qltkLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qltkLayout.createSequentialGroup()
                                .addComponent(btnReset5)
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qltkLayout.createSequentialGroup()
                                .addGroup(qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel86)
                                    .addComponent(jLabel89)
                                    .addComponent(jLabel88)
                                    .addComponent(jLabel87))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, qltkLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(btnThem4)
                        .addGap(36, 36, 36)
                        .addComponent(btnSua4)
                        .addContainerGap())))
        );
        qltkLayout.setVerticalGroup(
            qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qltkLayout.createSequentialGroup()
                .addGroup(qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane19)
                    .addGroup(qltkLayout.createSequentialGroup()
                        .addGroup(qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(qltkLayout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel86)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel87)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel88)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel89))
                            .addGroup(qltkLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset5)
                        .addGap(70, 70, 70)
                        .addGroup(qltkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 253, Short.MAX_VALUE)))
                .addGap(42, 42, 42))
        );

        panel.add(qltk, "qltk");

        dsnv.setBackground(new java.awt.Color(47, 48, 48));

        tblqlnv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Địa chỉ", "Email", "Chức vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblqlnv.setRowHeight(35);
        jScrollPane13.setViewportView(tblqlnv);

        jLabel63.setBackground(new java.awt.Color(61, 145, 106));
        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel63.setText("Thêm");
        jLabel63.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel63.setOpaque(true);
        jLabel63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel63MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel63MousePressed(evt);
            }
        });

        jLabel65.setBackground(new java.awt.Color(61, 145, 106));
        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        jLabel65.setText(" Xóa");
        jLabel65.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel65.setOpaque(true);
        jLabel65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel65MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel65MousePressed(evt);
            }
        });

        txttimkiem4.setToolTipText("");
        txttimkiem4.setBorder(null);
        txttimkiem4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiem4KeyReleased(evt);
            }
        });

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        jLabel102.setBackground(new java.awt.Color(61, 145, 106));
        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel102.setText(" Sửa");
        jLabel102.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel102.setOpaque(true);
        jLabel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel102MousePressed(evt);
            }
        });

        javax.swing.GroupLayout dsnvLayout = new javax.swing.GroupLayout(dsnv);
        dsnv.setLayout(dsnvLayout);
        dsnvLayout.setHorizontalGroup(
            dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dsnvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dsnvLayout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dsnvLayout.createSequentialGroup()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        dsnvLayout.setVerticalGroup(
            dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dsnvLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel72)
                        .addComponent(txttimkiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        panel.add(dsnv, "qlnv");

        qltudo.setBackground(new java.awt.Color(47, 48, 48));

        tblqltudo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số tủ", "Mã Khách Hàng", "Tên người gửi", "Số Điện Thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblqltudo.setOpaque(false);
        tblqltudo.setRowHeight(45);
        tblqltudo.setShowVerticalLines(false);
        tblqltudo.getTableHeader().setReorderingAllowed(false);
        jScrollPane12.setViewportView(tblqltudo);

        jLabel53.setBackground(new java.awt.Color(61, 145, 106));
        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel53.setText("Thêm");
        jLabel53.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel53.setOpaque(true);
        jLabel53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel53MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel53MousePressed(evt);
            }
        });

        jLabel55.setBackground(new java.awt.Color(61, 145, 106));
        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        jLabel55.setText(" Xóa");
        jLabel55.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel55.setOpaque(true);
        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel55MousePressed(evt);
            }
        });

        txttimkiem2.setToolTipText("");
        txttimkiem2.setBorder(null);
        txttimkiem2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiem2KeyReleased(evt);
            }
        });

        refresh2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        refresh2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                refresh2MousePressed(evt);
            }
        });

        jLabel60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        jLabel104.setBackground(new java.awt.Color(61, 145, 106));
        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel104.setText(" Sửa");
        jLabel104.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel104.setOpaque(true);
        jLabel104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel104MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel104MousePressed(evt);
            }
        });

        javax.swing.GroupLayout qltudoLayout = new javax.swing.GroupLayout(qltudo);
        qltudo.setLayout(qltudoLayout);
        qltudoLayout.setHorizontalGroup(
            qltudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qltudoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(qltudoLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(refresh2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        qltudoLayout.setVerticalGroup(
            qltudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qltudoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(qltudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(qltudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(qltudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(refresh2))
                    .addGroup(qltudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel60)
                        .addComponent(txttimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel.add(qltudo, "qltudo");

        sploi.setBackground(new java.awt.Color(47, 48, 48));

        tblsploi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Mã NCC", "Đơn giá ", "Lý do", "Thành tiền"
            }
        ));
        tblsploi.setRowHeight(30);
        tblsploi.getTableHeader().setReorderingAllowed(false);
        tblsploi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblsploiMousePressed(evt);
            }
        });
        jScrollPane14.setViewportView(tblsploi);

        txtTenCT1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Tên SP");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("Số lượng");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Mã NCC");

        btnThemKM1.setBackground(new java.awt.Color(61, 145, 106));
        btnThemKM1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThemKM1.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKM1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemKM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        btnThemKM1.setText(" Thêm");
        btnThemKM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKM1.setOpaque(true);
        btnThemKM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemKM1MousePressed(evt);
            }
        });

        btnXoaKM1.setBackground(new java.awt.Color(61, 145, 106));
        btnXoaKM1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnXoaKM1.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKM1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoaKM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        btnXoaKM1.setText(" Xóa");
        btnXoaKM1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaKM1.setOpaque(true);
        btnXoaKM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaKM1MousePressed(evt);
            }
        });

        btnReset2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        btnReset2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReset2MousePressed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel68.setText("Đơn giá");

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Mã SP");

        txtMaCT1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Lý do");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane15.setViewportView(jTextArea1);

        txttimkiem5.setToolTipText("");
        txttimkiem5.setBorder(null);
        txttimkiem5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiem5KeyReleased(evt);
            }
        });

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/search.png"))); // NOI18N

        btnSua5.setForeground(new java.awt.Color(255, 255, 255));
        btnSua5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/sua.png"))); // NOI18N
        btnSua5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSua5btnSuaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout sploiLayout = new javax.swing.GroupLayout(sploi);
        sploi.setLayout(sploiLayout);
        sploiLayout.setHorizontalGroup(
            sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sploiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(sploiLayout.createSequentialGroup()
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhanTram1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(sploiLayout.createSequentialGroup()
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(sploiLayout.createSequentialGroup()
                                .addComponent(btnSua5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReset2))
                            .addGroup(sploiLayout.createSequentialGroup()
                                .addComponent(btnThemKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)))
                .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sploiLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttimkiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        sploiLayout.setVerticalGroup(
            sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sploiLayout.createSequentialGroup()
                .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sploiLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sploiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73)
                            .addComponent(txttimkiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sploiLayout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(sploiLayout.createSequentialGroup()
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sploiLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel66)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel67)
                                    .addComponent(txtMaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTenCT1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel61)))
                        .addGap(28, 28, 28)
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel68)
                            .addComponent(txtPhanTram1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sploiLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel70))
                            .addGroup(sploiLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoaKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemKM1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(sploiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReset2)
                            .addComponent(btnSua5))
                        .addGap(89, 89, 89))))
        );

        panel.add(sploi, "sploi");

        dspx.setBackground(new java.awt.Color(47, 48, 48));
        dspx.setLayout(null);

        tblctpx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PX", "Mã SP", "Tên SP", "Đơn vị tính", "Số lượng", "Đơn giá"
            }
        ));
        tblctpx.setRowHeight(35);
        jScrollPane16.setViewportView(tblctpx);

        dspx.add(jScrollPane16);
        jScrollPane16.setBounds(20, 0, 640, 340);

        tblphieuxuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PX", "Ngày xuất ", "Mã kho", "Nhân viên xuất", "Thành tiền", "Trạng thái"
            }
        ));
        tblphieuxuat.setRowHeight(35);
        jScrollPane17.setViewportView(tblphieuxuat);

        dspx.add(jScrollPane17);
        jScrollPane17.setBounds(20, 370, 1070, 390);

        combosp1.setMaximumRowCount(100);
        combosp1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combosp1ItemStateChanged(evt);
            }
        });
        combosp1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combosp1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                combosp1FocusLost(evt);
            }
        });
        combosp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combosp1ActionPerformed(evt);
            }
        });
        dspx.add(combosp1);
        combosp1.setBounds(680, 70, 340, 40);
        dspx.add(slnhap1);
        slnhap1.setBounds(790, 180, 180, 35);

        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Số lượng xuất");
        dspx.add(jLabel74);
        jLabel74.setBounds(680, 190, 110, 16);

        dvtpn1.setBackground(new java.awt.Color(255, 255, 255));
        dvtpn1.setForeground(new java.awt.Color(255, 255, 255));
        dvtpn1.setText("hienthikhichonSP");
        dspx.add(dvtpn1);
        dvtpn1.setBounds(790, 140, 170, 16);

        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Đơn vị tính :");
        dspx.add(jLabel75);
        jLabel75.setBounds(680, 140, 100, 16);

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Chọn sản phẩm");
        dspx.add(jLabel76);
        jLabel76.setBounds(680, 10, 210, 29);

        jLabel77.setBackground(new java.awt.Color(61, 145, 106));
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        jLabel77.setText("Thêm");
        jLabel77.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel77.setOpaque(true);
        jLabel77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel77MousePressed(evt);
            }
        });
        dspx.add(jLabel77);
        jLabel77.setBounds(680, 238, 164, 45);

        jLabel78.setBackground(new java.awt.Color(61, 145, 106));
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel78.setText("Tạo phiếu xuất");
        jLabel78.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel78.setOpaque(true);
        jLabel78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel78MousePressed(evt);
            }
        });
        dspx.add(jLabel78);
        jLabel78.setBounds(680, 296, 164, 45);

        jLabel79.setBackground(new java.awt.Color(61, 145, 106));
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/xls.png"))); // NOI18N
        jLabel79.setText("Xuất Excel");
        jLabel79.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel79.setOpaque(true);
        jLabel79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel79MousePressed(evt);
            }
        });
        dspx.add(jLabel79);
        jLabel79.setBounds(856, 296, 164, 45);

        jLabel80.setBackground(new java.awt.Color(61, 145, 106));
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("Reset phiếu xuất");
        jLabel80.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel80.setOpaque(true);
        jLabel80.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel80MousePressed(evt);
            }
        });
        dspx.add(jLabel80);
        jLabel80.setBounds(856, 238, 164, 45);

        panel.add(dspx, "dspx");

        kktk.setBackground(new java.awt.Color(47, 48, 48));
        kktk.setLayout(null);

        tblthongtinkho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kho", "Mã SP", "Tên SP", "Số lượng", "Đơn vị tính", "Ngày nhập"
            }
        ));
        tblthongtinkho.setRowHeight(35);
        jScrollPane18.setViewportView(tblthongtinkho);
        if (tblthongtinkho.getColumnModel().getColumnCount() > 0) {
            tblthongtinkho.getColumnModel().getColumn(2).setResizable(false);
            tblthongtinkho.getColumnModel().getColumn(4).setHeaderValue("Đơn vị tính");
            tblthongtinkho.getColumnModel().getColumn(5).setHeaderValue("Ngày nhập");
        }

        kktk.add(jScrollPane18);
        jScrollPane18.setBounds(15, 359, 1080, 410);

        tblkho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Kho", "Tên Kho", "Địa chỉ kho"
            }
        ));
        tblkho.setRowHeight(35);
        jScrollPane21.setViewportView(tblkho);
        if (tblkho.getColumnModel().getColumnCount() > 0) {
            tblkho.getColumnModel().getColumn(2).setHeaderValue("Địa chỉ kho");
        }

        kktk.add(jScrollPane21);
        jScrollPane21.setBounds(20, 10, 640, 340);

        theoquy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        theoquy.setForeground(new java.awt.Color(255, 255, 255));
        theoquy.setText("Theo quý");
        theoquy.setOpaque(false);
        theoquy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theoquyActionPerformed(evt);
            }
        });
        kktk.add(theoquy);
        theoquy.setBounds(690, 120, 130, 31);

        theothang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        theothang.setForeground(new java.awt.Color(255, 255, 255));
        theothang.setText("Theo tháng");
        theothang.setOpaque(false);
        theothang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theothangActionPerformed(evt);
            }
        });
        kktk.add(theothang);
        theothang.setBounds(690, 20, 130, 31);

        theonam.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        theonam.setForeground(new java.awt.Color(255, 255, 255));
        theonam.setText("Theo năm");
        theonam.setOpaque(false);
        theonam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theonamActionPerformed(evt);
            }
        });
        kktk.add(theonam);
        theonam.setBounds(690, 220, 130, 31);

        jComboBoxQuy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quý 1", "Quý 2", "Quý 3", "Quý 4" }));
        jComboBoxQuy.setName(""); // NOI18N
        kktk.add(jComboBoxQuy);
        jComboBoxQuy.setBounds(690, 160, 130, 30);

        cbthang2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        kktk.add(cbthang2);
        cbthang2.setBounds(690, 60, 130, 30);

        cbnam3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017" }));
        cbnam3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnam3ActionPerformed(evt);
            }
        });
        kktk.add(cbnam3);
        cbnam3.setBounds(690, 270, 130, 30);

        cbnam4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017" }));
        cbnam4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnam4ActionPerformed(evt);
            }
        });
        kktk.add(cbnam4);
        cbnam4.setBounds(900, 60, 120, 30);

        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("tongsoluong");
        kktk.add(jLabel58);
        jLabel58.setBounds(790, 326, 100, 20);

        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Tổng số lượng :");
        kktk.add(jLabel93);
        jLabel93.setBounds(680, 330, 120, 16);

        jLabel94.setBackground(new java.awt.Color(61, 145, 106));
        jLabel94.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("Lọc");
        jLabel94.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel94.setOpaque(true);
        jLabel94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel94MousePressed(evt);
            }
        });
        kktk.add(jLabel94);
        jLabel94.setBounds(920, 250, 80, 50);

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel98.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel98MousePressed(evt);
            }
        });
        kktk.add(jLabel98);
        jLabel98.setBounds(1040, 250, 45, 45);

        panel.add(kktk, "kktk");

        ncc.setBackground(new java.awt.Color(47, 48, 48));
        ncc.setLayout(null);

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Mã nhà cung cấp");
        ncc.add(jLabel81);
        jLabel81.setBounds(30, 50, 140, 30);

        txtmancc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmanccActionPerformed(evt);
            }
        });
        ncc.add(txtmancc);
        txtmancc.setBounds(190, 50, 160, 30);

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Tên nhà cung cấp");
        ncc.add(jLabel85);
        jLabel85.setBounds(20, 190, 150, 20);
        ncc.add(txttenncc);
        txttenncc.setBounds(190, 180, 160, 30);

        jLabel90.setBackground(new java.awt.Color(61, 145, 106));
        jLabel90.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("Thêm");
        jLabel90.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel90.setOpaque(true);
        jLabel90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel90MousePressed(evt);
            }
        });
        ncc.add(jLabel90);
        jLabel90.setBounds(500, 70, 100, 50);

        tblncc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NCC", "Tên NCC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblncc.setRowHeight(30);
        jScrollPane23.setViewportView(tblncc);

        ncc.add(jScrollPane23);
        jScrollPane23.setBounds(10, 282, 1090, 550);

        jLabel82.setBackground(new java.awt.Color(61, 145, 106));
        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Sửa");
        jLabel82.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel82.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel82.setOpaque(true);
        jLabel82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel82MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel82MousePressed(evt);
            }
        });
        ncc.add(jLabel82);
        jLabel82.setBounds(700, 70, 90, 50);

        panel.add(ncc, "ncc");

        thongkeloinhuan.setBackground(new java.awt.Color(47, 48, 48));

        groupthongke.add(rdo4);
        rdo4.setForeground(new java.awt.Color(255, 255, 255));
        rdo4.setSelected(true);
        rdo4.setText("Theo khoảng thời gian");
        rdo4.setOpaque(false);

        groupthongke.add(rdo5);
        rdo5.setForeground(new java.awt.Color(255, 255, 255));
        rdo5.setText("Theo tháng");
        rdo5.setOpaque(false);

        groupthongke.add(rdo6);
        rdo6.setForeground(new java.awt.Color(255, 255, 255));
        rdo6.setText("Theo quý");
        rdo6.setOpaque(false);
        rdo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo6ActionPerformed(evt);
            }
        });

        cbthang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbthang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbthang1ActionPerformed(evt);
            }
        });

        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Từ");

        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Đến");

        jLabel95.setBackground(new java.awt.Color(61, 145, 106));
        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("Lọc");
        jLabel95.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel95.setOpaque(true);
        jLabel95.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel95MousePressed(evt);
            }
        });

        tbltkln.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên SP", "Đã bán", "Số lượng tồn", "Giá nhập", "Giá bán", "Chi phí khác", "Đơn vị tính", "Tổng tiền thu", "Tổng tiền chi", "Tiền lời"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbltkln.setRowHeight(40);
        tbltkln.setShowVerticalLines(false);
        tbltkln.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbltklnMousePressed(evt);
            }
        });
        jScrollPane24.setViewportView(tbltkln);

        jLabel96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/refre.png"))); // NOI18N
        jLabel96.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel96.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel96MousePressed(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Tổng");

        lbtongdoanhthu1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbtongdoanhthu1.setForeground(new java.awt.Color(255, 255, 255));
        lbtongdoanhthu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbtongdoanhthu1.setText("0");

        cbquy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        cbquy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbquyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout thongkeloinhuanLayout = new javax.swing.GroupLayout(thongkeloinhuan);
        thongkeloinhuan.setLayout(thongkeloinhuanLayout);
        thongkeloinhuanLayout.setHorizontalGroup(
            thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel97)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE)
                        .addComponent(lbtongdoanhthu1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(409, 409, 409))
                    .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdo5)
                            .addComponent(rdo6))
                        .addGap(18, 18, 18)
                        .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbthang1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbquy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(143, 143, 143)
                        .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdo4)
                            .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                                        .addComponent(jLabel83)
                                        .addGap(25, 25, 25)
                                        .addComponent(day5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                                        .addComponent(jLabel84)
                                        .addGap(18, 18, 18)
                                        .addComponent(day6, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(146, 146, 146)
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel96)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thongkeloinhuanLayout.createSequentialGroup()
                        .addComponent(jScrollPane24)
                        .addContainerGap())))
        );
        thongkeloinhuanLayout.setVerticalGroup(
            thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96)))
                    .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdo5)
                                    .addComponent(cbthang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdo6)
                                    .addComponent(cbquy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(thongkeloinhuanLayout.createSequentialGroup()
                                .addComponent(rdo4)
                                .addGap(11, 11, 11)
                                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel83)
                                    .addComponent(day5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel84)
                                    .addComponent(day6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(69, 69, 69)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(thongkeloinhuanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtongdoanhthu1)
                    .addComponent(jLabel97))
                .addGap(28, 28, 28))
        );

        panel.add(thongkeloinhuan, "tkln");

        calam.setBackground(new java.awt.Color(47, 48, 48));
        calam.setLayout(null);

        tblphancongca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Ca", "Mã NV"
            }
        ));
        tblphancongca.setRowHeight(35);
        jScrollPane25.setViewportView(tblphancongca);

        calam.add(jScrollPane25);
        jScrollPane25.setBounds(10, 10, 480, 380);

        jLabel91.setBackground(new java.awt.Color(61, 145, 106));
        jLabel91.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel91.setText(" Sửa ca");
        jLabel91.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel91.setOpaque(true);
        jLabel91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel91MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel91MousePressed(evt);
            }
        });
        calam.add(jLabel91);
        jLabel91.setBounds(710, 340, 170, 50);

        btnThemKM3.setBackground(new java.awt.Color(61, 145, 106));
        btnThemKM3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThemKM3.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKM3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemKM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        btnThemKM3.setText(" Thêm ca");
        btnThemKM3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKM3.setOpaque(true);
        btnThemKM3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemKM3MousePressed(evt);
            }
        });
        calam.add(btnThemKM3);
        btnThemKM3.setBounds(530, 340, 170, 50);

        btnXoaKM3.setBackground(new java.awt.Color(61, 145, 106));
        btnXoaKM3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnXoaKM3.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKM3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoaKM3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        btnXoaKM3.setText(" Xóa phân công ca");
        btnXoaKM3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaKM3.setOpaque(true);
        btnXoaKM3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaKM3MousePressed(evt);
            }
        });
        calam.add(btnXoaKM3);
        btnXoaKM3.setBounds(800, 40, 220, 50);

        tblcalam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ca", "Giờ bắt đầu", "Giờ kết thúc", "Ngày làm"
            }
        ));
        tblcalam.setRowHeight(35);
        jScrollPane26.setViewportView(tblcalam);
        if (tblcalam.getColumnModel().getColumnCount() > 0) {
            tblcalam.getColumnModel().getColumn(2).setResizable(false);
        }

        calam.add(jScrollPane26);
        jScrollPane26.setBounds(10, 400, 1090, 490);

        btnThemKM4.setBackground(new java.awt.Color(61, 145, 106));
        btnThemKM4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnThemKM4.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKM4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThemKM4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/plus.png"))); // NOI18N
        btnThemKM4.setText(" Thêm phân công ca");
        btnThemKM4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemKM4.setOpaque(true);
        btnThemKM4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemKM4MousePressed(evt);
            }
        });
        calam.add(btnThemKM4);
        btnThemKM4.setBounds(550, 40, 230, 50);

        jLabel99.setBackground(new java.awt.Color(61, 145, 106));
        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel99.setText(" Sửa phân công ca");
        jLabel99.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel99.setOpaque(true);
        jLabel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel99MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel99MousePressed(evt);
            }
        });
        calam.add(jLabel99);
        jLabel99.setBounds(550, 140, 230, 50);

        panel.add(calam, "calam");

        task.setBackground(new java.awt.Color(47, 48, 48));
        task.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                taskMouseDragged(evt);
            }
        });
        task.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taskMousePressed(evt);
            }
        });

        lbMini.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbMini.setForeground(new java.awt.Color(255, 255, 255));
        lbMini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMini.setText("-");
        lbMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMiniMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbMiniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbMiniMouseExited(evt);
            }
        });

        lbClose.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lbClose.setForeground(new java.awt.Color(255, 255, 255));
        lbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbClose.setText("X");
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbCloseMouseExited(evt);
            }
        });

        lbtoday.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbtoday.setForeground(new java.awt.Color(255, 255, 255));
        lbtoday.setText("date");

        lbtime.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbtime.setForeground(new java.awt.Color(255, 255, 255));
        lbtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtime.setText("time");

        lbhello.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbhello.setForeground(new java.awt.Color(255, 255, 255));
        lbhello.setText("Xin chào : ");

        javax.swing.GroupLayout taskLayout = new javax.swing.GroupLayout(task);
        task.setLayout(taskLayout);
        taskLayout.setHorizontalGroup(
            taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbhello, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbtoday, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbtime, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbMini, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        taskLayout.setVerticalGroup(
            taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbMini, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(taskLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(taskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbhello, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtoday, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtime, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(task, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(task, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
      
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       
    }//GEN-LAST:event_jLabel5MouseClicked
   
    private void lbqlspMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlspMousePressed

        card.show(panel,"qlsp");
        
        lbqlsp.setBackground(Color.WHITE);
        lbqlsp.setOpaque(true);
        lbqlsp.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

       

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbqlspMousePressed

    private void lbdshdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdshdMousePressed
        card.show(panel,"dshd");
        lbdshd.setBackground(Color.WHITE);
        lbdshd.setOpaque(true);
        lbdshd.setForeground(Color.BLACK);
  
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

       

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbdshdMousePressed

    private void lbdspnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdspnMousePressed
        card.show(panel,"dspn");
        lbdspn.setBackground(Color.WHITE);
        lbdspn.setOpaque(true);
        lbdspn.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbdspnMousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
       FORM_THEM t = new FORM_THEM();
       t.setVisible(true);
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        int i = tblqlsp.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modelqlsp.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(SANPHAM s : list)
       {
           if(masua.equals(s.getMaSP()))
           {
               vitri = list.indexOf(s);
               mp = s.getMaNCC();
               ml = s.getMaLoai();
               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
        FORM_SUA r = new FORM_SUA();
        r.txtmasp.setText(list.get(vitri).getMaSP());
        r.txttensp.setText(list.get(vitri).getTenSP());
        
        r.txtsoluong.setText(String.valueOf(list.get(vitri).getSoLuong()));
        r.txtdvt.setText(list.get(vitri).getDonViTinh());
        r.txtgiaban.setText(String.valueOf(list.get(vitri).getGiaBan()).replace(".0", ""));
        r.txtgianhap.setText(String.valueOf(list.get(vitri).getGiaNhap()).replace(".0", ""));
        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
        {
            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
            {
                r.comboncc.setSelectedIndex(j);
                break;
            }
        }
        for(int j=0;j<FORM_SUA.boxml.getSize();j++)
        {
            if(String.valueOf(FORM_SUA.boxml.getElementAt(j)).contains(ml))
            {
                r.combomaloai.setSelectedIndex(j);
                break;
            }
        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
       else
       {
           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
       }
       
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
       int ma = tblqlsp.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             String maxoa = String.valueOf(modelqlsp.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa" , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(j==JOptionPane.YES_OPTION)
            {
            if(new BUS_XOA().xoaSP(maxoa))
            {

                modelqlsp.setRowCount(0);
               list = new LOADDULIEU().getList();
        for(SANPHAM s : list)
        {
            modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
        }
            }
            }
           
         
            
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
         }
    }//GEN-LAST:event_jLabel4MousePressed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
      
        String at = txttimkiem.getText();
        
            at=at.trim();
          
            modelqlsp.setRowCount(0);
         
            for(SANPHAM s : list)
            {
                if(s.getTenSP().contains(at)||s.getMaNCC().contains(at)||s.getDonViTinh().contains(at)||s.getMaSP().contains(at)||s.getMaLoai().contains(at))
                {
                                modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});

                }
            }
           
            
         
          
            
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void refreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMousePressed
        list = new LOADDULIEU().getList();
        modelqlsp.setRowCount(0);
        for(SANPHAM s : list)
        {
            modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
        }
    }//GEN-LAST:event_refreshMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        new GDBH().setVisible(true); 
        
        int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
         String rd2= String.valueOf(rd);
           
        String a = taochuoi(3) + rd2;
        HOADON h = new HOADON();
        h.setMaHD(a);
        h.setMaNV(ttdn.get(0).getManv());
        Date da = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        String now = f.format(da);
        h.setNgayTao(now);
        h.setTongTienTra(0.0);
        if(new BUS_THEM().themHD(h))
        {
            JOptionPane.showMessageDialog(rootPane,"Đã tạo hóa đơn ! ","Thông báo",JOptionPane.DEFAULT_OPTION);
            listhd.add(h);
            listhd = new LOADDULIEU().getListHD();
            modeldshd.setRowCount(0);
        for(HOADON hc : listhd)
        {
            try {
                String dayhd = sdf2.format(sdf1.parse(String.valueOf(hc.getNgayTao())));
                  modeldshd.addRow(new Object[]{hc.getMaHD(),hc.getMaNV(),dayhd,vnmoney.format(hc.getTongTienTra()),hc.getMaKM()});
            
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Them that bai");
        }
        
    }//GEN-LAST:event_jLabel1MousePressed

    private void lbthongkedoanhthuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbthongkedoanhthuMousePressed
       card.show(panel,"tk");
        
        lbthongkedoanhthu.setBackground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(true);
        lbthongkedoanhthu.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbthongkedoanhthuMousePressed

    private void lbttcnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbttcnMousePressed
        card.show(panel,"ttcn");
        lbttcn.setBackground(Color.WHITE);
        lbttcn.setOpaque(true);
        lbttcn.setForeground(Color.BLACK);

        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
       

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbttcnMousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        LocListHD();
        
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed
        
        
        if(rdo1.isSelected())
        {
            SimpleDateFormat ngay = new SimpleDateFormat("yyyy/MM/dd");
        String tu = ngay.format(day1.getDate());
        String den = ngay.format(day2.getDate());
       
        try {
            Date truoc = ngay.parse(tu);
            Date sau = ngay.parse(den);
            System.out.println(sau.after(truoc));
            if(sau.after(truoc))
            {
                
                System.out.println("ngay truoc la " + tu);
                System.out.println("ngay sau la " + den);
                 ArrayList<HOADON> listtk = new ArrayList<>();
                listtk = new BUS_THONGKE().LocHDTheoKhoangThoiGian(tu, den);
           modelthongke.setRowCount(0);
           for(HOADON h : listtk)
           {
                  
                
               modelthongke.addRow(new Object[]{h.getMaHD(),sdf2.format(sdf3.parse(String.valueOf(h.getNgayTao()))),vnmoney.format(h.getTongTienTra())});
                    
           }
            ArrayList<CTHD> thongkesp = new ArrayList<>();
           thongkesp = new BUS_THONGKE().ThongKeSPTheoKhoangThoiGian(tu, den);
           modeltksp.setRowCount(0);
           for(CTHD h : thongkesp)
           {
               modeltksp.addRow(new Object[]{h.getTenSP(),h.getSoLuong(),vnmoney.format(h.getDongia()),vnmoney.format(h.getThanhTien())});
           }
           lbtksp.setText("Lượng sản phẩm bán ra từ " + sdf2.format(sdf1.parse(tu)) + " đến " + sdf2.format(sdf1.parse(den)));
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Vui long nhap khoang thoi gian hop le");
            }
        } catch (ParseException ex) {
            Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        }
        else if(rdo2.isSelected())
        {
           String a = (String) cbthang.getSelectedItem();
           String b = (String) cbnam.getSelectedItem();
           //System.out.println("Thang " + a +" Nam " + b);
           String chuoi = "/"+a+"/"+b;
           ArrayList<HOADON> listtk = new ArrayList<>();
           listtk = new BUS_THONGKE().LocHDTheoThangNam(a,b);
           modelcttk.setRowCount(0);
           modelthongke.setRowCount(0);
           for(HOADON h : listtk)
           {
                  
                   try {
                    modelthongke.addRow(new Object[]{h.getMaHD(),sdf2.format(sdf1.parse(String.valueOf(h.getNgayTao()))),vnmoney.format(h.getTongTienTra())});
                } catch (ParseException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
           }
           ArrayList<CTHD> thongkesp = new ArrayList<>();
           thongkesp = new BUS_THONGKE().ThongKeSPTheoThangNam(a,b);
           modeltksp.setRowCount(0);
           for(CTHD h : thongkesp)
           {
               modeltksp.addRow(new Object[]{h.getTenSP(),h.getSoLuong(),vnmoney.format(h.getDongia()),vnmoney.format(h.getThanhTien())});
           }
           lbtksp.setText("Lượng sản phẩm bán ra trong tháng " + a +" năm " + b);
        }
        else if(rdo3.isSelected())
        {
            SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
            String chonngay = fm.format(day3.getDate());

            //System.out.println(fmngay.format(day3.getDate()));
            //System.out.println(fmthang.format(day3.getDate()));
            //System.out.println(fmnam.format(day3.getDate()));
           
            //System.out.println("ngay la " + chonngay);
            ArrayList<HOADON> listtk = new ArrayList<>();
            listtk = new BUS_THONGKE().LocHDTheoNgay(chonngay);
            modelthongke.setRowCount(0);
            for(HOADON h : listtk)
            {
               
                modelthongke.addRow(new Object[]{h.getMaHD(),h.getNgayTao(),vnmoney.format(h.getTongTienTra())});
                
            }
             ArrayList<CTHD> thongkesp = new ArrayList<>();
           thongkesp = new BUS_THONGKE().ThongKeSPTheoNgay(chonngay);
           modeltksp.setRowCount(0);
           for(CTHD h : thongkesp)
           {
               modeltksp.addRow(new Object[]{h.getTenSP(),h.getSoLuong(),vnmoney.format(h.getDongia()),vnmoney.format(h.getThanhTien())});
           }
            try {
                lbtksp.setText("Lượng sản phẩm bán ra trong ngày " + sdf2.format(sdf1.parse(chonngay)));
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel16MousePressed

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed
         
        ArrayList<HOADON> listtk = new ArrayList<>();
            listtk = new LOADDULIEU().getListHD();
            modelthongke.setRowCount(0);
            modelcttk.setRowCount(0);
            for(HOADON h : listtk)
            {
               
                try {
                    modelthongke.addRow(new Object[]{h.getMaHD(),sdf2.format(sdf1.parse(String.valueOf(h.getNgayTao()))),vnmoney.format(h.getTongTienTra())});
                } catch (ParseException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            
            
            
            modeltksp.setRowCount(0);
            tktc = new BUS_THONGKE().ThongKeTatCa();
             for(CTHD h : tktc)
        {
           modeltksp.addRow(new Object[]{h.getTenSP(),h.getSoLuong(),vnmoney.format(h.getDongia()),vnmoney.format(h.getThanhTien())});
        }
             lbtksp.setText("Tổng lượng sản phẩm đã bán ra được");
             os = new BUS_THONGKE().TongTatCa();
             lbtongdoanhthu.setText(vnmoney.format(os));
    }//GEN-LAST:event_jLabel17MousePressed

    private void tblthongkeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblthongkeMousePressed
        int i = tblthongke.getSelectedRow();
        String ma = (String) modelthongke.getValueAt(i,0);
       
        ArrayList<CTHD> tklist = new ArrayList<>();
        tklist = new LOADDULIEU().getListCTHD(ma);
            Double sum = new BUS_SUA().TongTien(listhd.get(i).getMaHD());
            modelcttk.setRowCount(0);
            for(CTHD c : tklist)
            {
                modelcttk.addRow(new Object[]{c.getTenSP(),c.getSoLuong(),vnmoney.format(c.getDongia()),vnmoney.format(c.getThanhTien())});
            }
    }//GEN-LAST:event_tblthongkeMousePressed

    private void logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMousePressed
        int a = JOptionPane.showConfirmDialog(rootPane, "Xác nhận đăng xuất", "Đăng xuất", JOptionPane.YES_NO_CANCEL_OPTION);
       
        if(a==0)
        {
            this.dispose();
           
            new LOGIN().setVisible(true);
        }
    }//GEN-LAST:event_logoutMousePressed

    private void tbldshdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldshdMouseClicked
       if (evt.getClickCount() == 2) {
     int i = tbldshd.getSelectedRow();
        if(i!=-1)
        {
            FORM_CTHD chitiet = new FORM_CTHD();
            chitiet.setVisible(true);
            chitiet.ma.setText(listhd.get(i).getMaHD());
            modelcthd = (DefaultTableModel) chitiet.tblcthd.getModel();
            chitiet.tblcthd.getTableHeader().setBackground(new Color(8,112,150));
            chitiet.tblcthd.getTableHeader().setOpaque(false);
            chitiet.tblcthd.getTableHeader().setForeground(Color.WHITE);
            String mahd = listhd.get(i).getMaHD();
            ArrayList<CTHD> concac = new ArrayList<>();
            concac =new LOADDULIEU().getListCTHD(mahd);
//            JOptionPane.showMessageDialog(null, listhd.get(i).getMaHD());
//            Double sum = new BUS_SUA().TongTien(listhd.get(i).getMaHD());
//            chitiet.tongtien.setText(vnmoney.format(sum));
//            chitiet.lbngayxuat.setText(String.valueOf(modeldshd.getValueAt(i,2)));
//            if(String.valueOf(modeldshd.getValueAt(i,4)).contains("%")==false)
//            {
//                chitiet.lbphantramkm.setText("Không có");
//            }
//            else
//            {
//            chitiet.lbphantramkm.setText(String.valueOf(modeldshd.getValueAt(i,4)));
//            }
            for(CTHD cc : concac)
            {
                modelcthd.addRow(new Object[]{cc.getTenSP(),cc.getSoLuong(),cc.getDongia(),cc.getThanhTien()});
//                JOptionPane.showMessageDialog(null, cc.getTenSP());
            }
            String tenkh = String.valueOf(modeldshd.getValueAt(i,5));
            for(KHACHHANG kh : listkh)
            {
                if(kh.getMakh().equalsIgnoreCase(tenkh))
                {
                    chitiet.lbtenkh.setText(kh.getTenkh());
                    break;
                }
            }
            ArrayList<NGUOIDUNG> listnd = new ArrayList<>();
            listnd = new LOADDULIEU().getListNguoiDung();
            for(NGUOIDUNG ng : listnd)
            {
                if(ng.getManv().equalsIgnoreCase(String.valueOf(modeldshd.getValueAt(i, 1))))
                {
                    chitiet.lbtennv.setText(ng.getHoten());
                    break;
                }
            }
//            for(CTHD c : listcthd)
//            {
//                modelcthd.addRow(new Object[]{c.getMasp(),c.getTensp(),c.getSoluong(),vnmoney.format(c.getDongia()),vnmoney.format(c.getThanhtien())});
//            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Hãy chọn dòng cần xem !");
        }
        
        
  }
    }//GEN-LAST:event_tbldshdMouseClicked

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        int i = tbldshd.getSelectedRow();
        if(i!=-1)
        {
            FORM_CTHD chitiet = new FORM_CTHD();
            chitiet.setVisible(true);
            chitiet.ma.setText(listhd.get(i).getMaHD());
            modelcthd = (DefaultTableModel) chitiet.tblcthd.getModel();
            chitiet.tblcthd.getTableHeader().setBackground(new Color(8,112,150));
            chitiet.tblcthd.getTableHeader().setOpaque(false);
            chitiet.tblcthd.getTableHeader().setForeground(Color.WHITE);
            listcthd =new LOADDULIEU().getListCTHD(listhd.get(i).getMaHD());
            Double sum = new BUS_SUA().TongTien(listhd.get(i).getMaHD());
            chitiet.tongtien.setText(vnmoney.format(sum));
            chitiet.lbngayxuat.setText(String.valueOf(modeldshd.getValueAt(i,2)));
            if(String.valueOf(modeldshd.getValueAt(i,4)).contains("%")==false)
            {
                chitiet.lbphantramkm.setText("Không có");
            }
            else
            {
            chitiet.lbphantramkm.setText(String.valueOf(modeldshd.getValueAt(i,4)));
            }
            String tenkh = String.valueOf(modeldshd.getValueAt(i,5));
            for(KHACHHANG kh : listkh)
            {
                if(kh.getMakh().equalsIgnoreCase(tenkh))
                {
                    chitiet.lbtenkh.setText(kh.getTenkh());
                    break;
                }
            }
//            ArrayList<NGUOIDUNG> listnd = new ArrayList<>();
//            listnd = new LOADDULIEU().getListNguoiDung();
//            for(NGUOIDUNG ng : listnd)
//            {
//                if(ng.getManv().equalsIgnoreCase(String.valueOf(modeldshd.getValueAt(i, 1))))
//                {
//                    chitiet.lbtennv.setText(ng.getHoten());
//                    break;
//                }
//            }
            for(CTHD c : listcthd)
            {
                modelcthd.addRow(new Object[]{c.getMaSP(),c.getSoLuong(),/*vnmoney.format(c.getDongia()),*/vnmoney.format(c.getThanhTien())});
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Hãy chọn dòng cần xem !");
        }
    }//GEN-LAST:event_jLabel6MousePressed
    
    public static void closeform()
    {
        bienmat.doClick();
    }
    private void jLabel30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MousePressed
       new DMK_NV().setVisible(true);
    }//GEN-LAST:event_jLabel30MousePressed

    private void bienmatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bienmatActionPerformed
        dispose();
    }//GEN-LAST:event_bienmatActionPerformed

    private void tblkmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkmMousePressed
        // TODO add your handling code here:

        int i = tblkm.getSelectedRow();

        try {
            Date bd=new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(modelkm.getValueAt(i, 2)));
            Date kt=new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(modelkm.getValueAt(i, 3)));
            if(i >= 0)
            {
                for(KHUYENMAI km : listkm)
                {
                    if(km.getMaKM().equalsIgnoreCase(String.valueOf(modelkm.getValueAt(i, 0))))
                    {
                       
                        txtTenCT.setText(km.getTenCT());
                        txtNgayBD.setDate(bd);
                        txtNgayKT.setDate(kt);
                        txtPhanTram.setText(String.valueOf(km.getPhanTram()));
                    }
                }

            }
        } catch (ParseException ex) {
         
        }

    }//GEN-LAST:event_tblkmMousePressed

    private void btnThemKMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKMMousePressed
        // TODO add your handling code here:
        if(txtTenCT.getText().isEmpty() || txtNgayBD.getDate() == null || txtNgayKT.getDate() == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập đầy đủ thông tin");
        }
        else
        {
             int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
         String rd2= String.valueOf(rd);
           
        String a = taochuoi(5) + rd2;
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            String bd = f.format(txtNgayBD.getDate());
            String kt = f.format(txtNgayKT.getDate());

            Date now = new Date();
            now.setDate(now.getDate()-1);
           
            String hq = f.format(now);
            try {
                Date truoc = f.parse(bd);
                Date sau = f.parse(kt);
                Date homqua = f.parse(hq);
               
                if((truoc.after(homqua)&&(sau.after(truoc)||bd.equals(kt))))
                {
                    KHUYENMAI km = new KHUYENMAI();
                    km.setMaKM(a);
                    km.setTenCT(txtTenCT.getText());
                    km.setNgayBatDau(bd);
                    km.setNgayKetThuc(kt);
                    km.setPhanTram(Float.parseFloat(txtPhanTram.getText()));
                    int conf = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thêm Chương Trình Khuyến Mãi này");
                    if(conf==0)
                    {
                        if(new BUS_THEM().themKM(km))
                        {
                            listkm.add(km);
                            modelkm.addRow(new Object[] {km.getMaKM() , km.getTenCT() , km.getNgayBatDau() , km.getNgayKetThuc() , km.getPhanTram()});
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(rootPane, "Thêm thất bại");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập ngày hợp lệ.");
                }
            } catch (ParseException ex) {
               
            }

        }
    }//GEN-LAST:event_btnThemKMMousePressed

    private void btnReset1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReset1MousePressed
        // TODO add your handling code here:
     
        txtTenCT.setText("");
        txtNgayBD.setDate(null);
        txtNgayKT.setDate(null);
        txtPhanTram.setText("");
    }//GEN-LAST:event_btnReset1MousePressed

    private void lbkmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkmMousePressed
        card.show(panel, "ctkm");
        lbkm.setBackground(Color.WHITE);
        lbkm.setOpaque(true);
        lbkm.setForeground(Color.BLACK);

        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbkmMousePressed

    private void lbthongkedoanhthuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbthongkedoanhthuMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbthongkedoanhthuMouseReleased

    private void lbkhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkhachhangMousePressed
        card.show(panel, "nsx");lbkm.setBackground(Color.WHITE);
        lbkhachhang.setOpaque(true);
        lbkhachhang.setForeground(Color.BLACK);

        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbkhachhangMousePressed

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jLabel37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MousePressed
        new TTKHAC().setVisible(true);
    }//GEN-LAST:event_jLabel37MousePressed
    String path=null;    
    public void setAvatar(String user)
    {
        me.setIcon(Resize("..\\DemoDoAn\\src\\Image\\AVT"+user+".jpg", me.getWidth(), me.getHeight()));
    }
    private void jLabel32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MousePressed
         if(path==null)
       {
           JOptionPane.showMessageDialog(rootPane, "Hãy chọn ảnh trước !");
       }
       else
       {
           String ten = "AVT"+ttdn.get(0).getUser();
           if(ten==null)
           {}
           else
           {
           ThemAnh(ten);
           }
           
       
           
       }
    }//GEN-LAST:event_jLabel32MousePressed

    private void jLabel38MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel38MousePressed
         JFileChooser filechooser = new JFileChooser();
    filechooser.setDialogTitle("Choose Your File");
    filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png");
        filechooser.addChoosableFileFilter(filter);
        int result = filechooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File select = filechooser.getSelectedFile();
            path = select.getAbsolutePath();
            me.setIcon(Resize(path,me.getWidth(),me.getHeight()));
           
        }
    }//GEN-LAST:event_jLabel38MousePressed

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MousePressed
        String a = txttenkh.getText();
        String b = txtsdtkh.getText();
        if(a.isEmpty()||b.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đầy đủ thông tin !");
        }
        else if(new KiemTraLoi().KT_SDT(b) == false)
        {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ !");
        }
        else
        {
            int xn= JOptionPane.showConfirmDialog(rootPane, "Xác nhận thêm khách hàng này ?");
            
            if(xn==0)
            {
                KHACHHANG kh = new KHACHHANG();
                 int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
                 String ma= "KH" + String.valueOf(rd);
                kh.setTenkh(a);
                kh.setSdt(b);
                kh.setMakh(ma);
                if(new BUS_THEM().themKH(kh)){JOptionPane.showMessageDialog(rootPane, "Đã thêm khách hàng");
                LocListKH();
                }
            }
        }
    }//GEN-LAST:event_jLabel40MousePressed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel41MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MousePressed
      int i = tblkhachhang.getSelectedRow();
      if(i>=0)
      {
      int xn = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khách hàng này ?", "Confirm", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
      if(xn==JOptionPane.YES_OPTION)
      {
          
          if(new BUS_XOA().xoaKH(String.valueOf(modelkhachhang.getValueAt(i, 0))))
          {
              JOptionPane.showMessageDialog(rootPane, "Đã xóa");
              LocListKH();
          }
          else
          {
              JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
          }
      }
      }
      else
      {
          JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa");
      }
      
    }//GEN-LAST:event_jLabel41MousePressed

    private void tblkhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhachhangMousePressed
        int i = tblkhachhang.getSelectedRow();
        if(i>=0)
        {
            txttenkh.setText(String.valueOf(modelkhachhang.getValueAt(i,1)));
            txtsdtkh.setText(String.valueOf(modelkhachhang.getValueAt(i,2)));
            modelctkh.setRowCount(0);
            ArrayList<HOADON> listctkh =new ArrayList<>();
            listctkh = new LOADDULIEU().HD_KHACHHANG(String.valueOf(modelkhachhang.getValueAt(i,0)));
            for(HOADON h : listctkh)
            {
                try {
                    modelctkh.addRow(new Object[]{h.getMaHD(),sdf2.format(sdf1.parse(String.valueOf(h.getNgayTao()))),vnmoney.format(h.getNgayTao())});
                } catch (ParseException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_tblkhachhangMousePressed

    private void jLabel42MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MousePressed
        LocListKH();
    }//GEN-LAST:event_jLabel42MousePressed

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel43MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MousePressed
       int i = tblkhachhang.getSelectedRow();
       if(i>=0)
       {
           String ma = String.valueOf(modelkhachhang.getValueAt(i,0));
           String ten = txttenkh.getText();
           String sdt = txtsdtkh.getText();
          
        if(ten.isEmpty()||sdt.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Hãy nhập đầy đủ thông tin !");
        }
        else if(new KiemTraLoi().KT_SDT(sdt) == false)
        {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ !");
        }
        else
        {
           if(ten.isEmpty()||sdt.isEmpty())
           {
                JOptionPane.showMessageDialog(rootPane, "Hãy nhập đầy đủ thông tin trước !");
           }
           else
           {
               if(new BUS_SUA().suaKH(ma,ten,sdt))
               {
                   JOptionPane.showMessageDialog(rootPane, "Đã sửa");
                   LocListKH();
               }
               else
               {
                   JOptionPane.showMessageDialog(rootPane, "Có lỗi xảy ra");
               }
           }
       }
       }
       else
       {
           JOptionPane.showMessageDialog(rootPane, "Chọn dòng cần sửa !");
       }
       
    }//GEN-LAST:event_jLabel43MousePressed

    private void combospFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combospFocusLost
     
    }//GEN-LAST:event_combospFocusLost

    private void combospActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combospActionPerformed
       
    }//GEN-LAST:event_combospActionPerformed

    private void combospItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combospItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            Object item = evt.getItem();
           
            String a = String.valueOf(item);
        String masp = FORM_THEM.tachphai(a);
        list = new LOADDULIEU().getList();              
        for(SANPHAM s : list)
        {
            if(a.contains(s.getMaSP()))
            {
                dvtpn.setText(s.getTenSP());
                break;
            }
        }
        }
    }//GEN-LAST:event_combospItemStateChanged

    private void combospFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combospFocusGained
        
    }//GEN-LAST:event_combospFocusGained

    private void jLabel47MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MousePressed
        if(new KiemTraLoi().KT_SoLuong(String.valueOf(slnhap.getText()))==false||slnhap.getText()==null)
        {
            JOptionPane.showMessageDialog(rootPane, "Số lượng chứa ký tự hoặc rỗng ");
        }
        else if(Integer.parseInt(slnhap.getText())<=0)
        {
            JOptionPane.showMessageDialog(rootPane, "Số lượng không hợp lệ ");
        }
        else{
        CTPN pn = new CTPN();
        String masp = FORM_THEM.tachtrai(String.valueOf(boxpn.getSelectedItem()));
        pn.setMaSP(masp);
        pn.setSoLuong(Integer.parseInt(slnhap.getText()));
        list = new LOADDULIEU().getList();              
        for(SANPHAM s : list)
        {
            if(masp.equals(s.getMaSP()))
            {
                pn.setGiaNhap(s.getGiaNhap()*60/100);
                pn.setThanhTien(pn.getGiaNhap()*pn.getSoLuong());
                break;
            }
        }
        if(new BUS_THEM().ThemCTPN(pn))
        {
            JOptionPane.showMessageDialog(rootPane, "Đã thêm sản phẩm ");
            LocAll();
        }
        else
        {
            System.out.println("error");
        }
        }
    }//GEN-LAST:event_jLabel47MousePressed

    private void jLabel48MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MousePressed
        int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
         String rd2= String.valueOf(rd);
           
        String a = "PNSP" + rd2;
        double tong = 0;
        for(CTPN pn : listctpn)
        {
            tong+=pn.getThanhTien();
        }
        
        PHIEUNHAP p = new PHIEUNHAP();
        Date g = new Date();
        String nay = sdf1.format(g);
        p.setMaNV(ttdn.get(0).getManv());
        p.setTongTien(tong);
        p.setNgayNhap(nay);
        p.setMaPN(a);
        int xn = JOptionPane.showConfirmDialog(rootPane, "Hãy xác nhận tạo phiếu nhập !");
        if(xn==0)
        {
        if(new BUS_THEM().ThemPN(p))
        {
            
            for(CTPN pa : listctpn)
            {
                int hientai = 0;
                for(SANPHAM s : list)
                {
                    if(s.getMaSP().equals(pa.getMaSP()))
                    {
                        hientai = s.getSoLuong()+pa.getSoLuong();
                        break;
                    }
                }   
                 
                pa.setMaPN(a);
                if(new BUS_THEM().ThemCTPN_CT(pa))
                {
                   if(new BUS_SUA().suasoluongSP(pa.getMaSP(), hientai)){}
                }
            }
             new BUS_XOA().resetCTPN();
        }
        JOptionPane.showMessageDialog(rootPane, "Đã tạo phiếu nhập mã " + a);
        LocAll();
        }
    }//GEN-LAST:event_jLabel48MousePressed

    private void jLabel49MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MousePressed
        FileWriter out = null;
        
        int i = tblphieunhap.getSelectedRow();
        if(i>=0)
        {
            String mapn = String.valueOf(modelphieunhap.getValueAt(i, 0));
            int xn = JOptionPane.showConfirmDialog(rootPane, "Xác nhận in phiếu nhập mã " + mapn + " ? ");
            if(xn==0)
            {
        try {
            LocListPhieuNhap();
            out = new FileWriter("C:\\HoaDonPDF\\PhieuNhap"+mapn+".xls");
            BufferedWriter bw = new BufferedWriter(out);
            
            bw.write("Mã phiếu : " + "\t");
            bw.write(mapn + "\t");
            bw.write("\n");
            bw.write("Ngày nhập : " + "\t");
            bw.write(String.valueOf(modelphieunhap.getValueAt(i,1)) + "\t");
            bw.write("\n");
            bw.write("Mã sản phẩm" + "\t");
            bw.write("Số lượng" + "\t");
            bw.write("Giá nhập" + "\t");
            bw.write("Thành tiền" + "\t");
            
            bw.write("\n");
            listctpn = new LOADDULIEU().getListCTPN(mapn);
            Double tong = 0.0;
            for(CTPN pn : listctpn)
            {
                bw.write(pn.getMaSP() + "\t");
                bw.write(pn.getSoLuong()+ "\t");
                bw.write(pn.getGiaNhap() + "\t");
                bw.write(pn.getThanhTien() + "\t");
                bw.write("\n");
                tong+=pn.getThanhTien();
            }
            bw.write("Tổng" + "\t");
                bw.write(" "+ "\t");
                bw.write(" "+ "\t");
                bw.write(tong + "\t");
            bw.close();
            JOptionPane.showMessageDialog(rootPane, "Đã xuất phiếu nhập");
        } catch (IOException ex) {
            Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane, "Lỗi khi xuất");
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
       
        }
         else
        {
            JOptionPane.showMessageDialog(rootPane, "Chọn mã phiếu nhập cần xuất !");
        }
    }//GEN-LAST:event_jLabel49MousePressed

    private void jLabel50MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MousePressed
       new BUS_XOA().resetCTPN();
       LocListCTPN();
    }//GEN-LAST:event_jLabel50MousePressed

    private void lbqlcvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlcvMousePressed
        card.show(panel,"qlcv");
        lbqlcv.setBackground(Color.WHITE);
        lbqlcv.setOpaque(true);
        lbqlcv.setForeground(Color.BLACK);
        
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbqlcvMousePressed

    private void txttimkiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiem1KeyReleased

    private void refresh1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_refresh1MousePressed

    private void lbqlspMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlspMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lbqlspMouseExited

    private void jLabel51MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MousePressed
        FORM_THEMCHUCVU cv = new FORM_THEMCHUCVU();
        cv.setVisible(true);
    }//GEN-LAST:event_jLabel51MousePressed

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel52MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MousePressed
       int ma = tblqlcv.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             String maxoa = String.valueOf(modelchucvu.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa" , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(j==JOptionPane.YES_OPTION)
            {
//            if(new BUS_XOA().xoaSP(maxoa))
//            {
//
//                modeldshd.setRowCount(0);
//               list = new LOADDULIEU().getList();
//        for(SANPHAM s : list)
//        {
//            modeldshd.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
//        }
//            }
            }
           
         
            
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
         }
    }//GEN-LAST:event_jLabel52MousePressed

    private void lbqltkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqltkMousePressed
        card.show(panel,"qltk");
        
        lbqltk.setBackground(Color.WHITE);
        lbqltk.setOpaque(true);
        lbqltk.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbqltkMousePressed

    private void tbltaikhoantblqltkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltaikhoantblqltkMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbltaikhoantblqltkMousePressed

    private void txtMaNV4txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNV4txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNV4txtMaNVActionPerformed

    private void txtMatKhau4txtMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatKhau4txtMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatKhau4txtMatKhauActionPerformed

    private void btnReset5btnResetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReset5btnResetMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReset5btnResetMousePressed

    private void btnThem4btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem4btnThemMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem4btnThemMousePressed

    private void btnSua4btnSuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSua4btnSuaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSua4btnSuaMousePressed

    private void lbqlnvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlnvMousePressed
        card.show(panel,"qlnv");
        
        lbqlnv.setBackground(Color.WHITE);
        lbqlnv.setOpaque(true);
        lbqlnv.setForeground(Color.BLACK);
        
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
       
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbqlnvMousePressed

    private void jLabel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel63MouseClicked

    private void jLabel63MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MousePressed
        FORM_ADDNHANVIEN t = new FORM_ADDNHANVIEN();
       t.setVisible(true);
    }//GEN-LAST:event_jLabel63MousePressed

    private void jLabel65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel65MouseClicked

    private void jLabel65MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MousePressed
        int ma = tblqlnv.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             String maxoa = String.valueOf(modelnhanvien.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa" , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(j==JOptionPane.YES_OPTION)
            {
//            if(new BUS_XOA().xoaSP(maxoa))
//            {
//
//                modeldshd.setRowCount(0);
//               list = new LOADDULIEU().getList();
//        for(SANPHAM s : list)
//        {
//            modeldshd.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
//        }
//            }
            }
           
         
            
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
         }
    }//GEN-LAST:event_jLabel65MousePressed

    private void lbqltudoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqltudoMousePressed
        card.show(panel,"qltudo");
        
        lbqltudo.setBackground(Color.WHITE);
        lbqltudo.setOpaque(true);
        lbqltudo.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
       
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbqltudoMousePressed

    private void jLabel53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel53MouseClicked

    private void jLabel53MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel53MousePressed
        FORM_THEMGUIDO t = new FORM_THEMGUIDO();
        t.setVisible(true);
    }//GEN-LAST:event_jLabel53MousePressed

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel55MouseClicked

    private void jLabel55MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MousePressed
        int ma = tblqltudo.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             String maxoa = String.valueOf(modeltudo.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa" , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(j==JOptionPane.YES_OPTION)
            {
//            if(new BUS_XOA().xoaSP(maxoa))
//            {
//
//                modeldshd.setRowCount(0);
//               list = new LOADDULIEU().getList();
//        for(SANPHAM s : list)
//        {
//            modeldshd.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
//        }
//            }
            }
           
         
            
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
         }
    }//GEN-LAST:event_jLabel55MousePressed

    private void txttimkiem2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiem2KeyReleased

    private void refresh2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_refresh2MousePressed

    private void taskMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskMousePressed
        mx = evt.getX();
        my = evt.getY();
    }//GEN-LAST:event_taskMousePressed

    private void taskMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-mx-250, y-my);
    }//GEN-LAST:event_taskMouseDragged

    private void lbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseExited
        lbClose.setForeground(Color.WHITE);
        lbClose.setOpaque(false);
        lbClose.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbCloseMouseExited

    private void lbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseEntered
        lbClose.setForeground(Color.BLACK);
        lbClose.setOpaque(true);
        lbClose.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbCloseMouseEntered

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        int a = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thoát chương trình ?");
        if(a==0)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_lbCloseMouseClicked

    private void lbMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseExited
        lbMini.setForeground(Color.WHITE);
        lbMini.setOpaque(false);
        lbMini.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbMiniMouseExited

    private void lbMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseEntered
        lbMini.setForeground(Color.BLACK);
        lbMini.setOpaque(true);
        lbMini.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbMiniMouseEntered

    private void lbMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseClicked
        this.setExtendedState(GIAODIENCHINH.ICONIFIED);
    }//GEN-LAST:event_lbMiniMouseClicked

    private void lbsploiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbsploiMousePressed
        card.show(panel,"sploi");
        
        lbsploi.setBackground(Color.WHITE);
        lbsploi.setOpaque(true);
        lbsploi.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
       
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbsploiMousePressed

    private void tblsploiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsploiMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblsploiMousePressed

    private void btnThemKM1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKM1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemKM1MousePressed

    private void btnXoaKM1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKM1MousePressed
        int ma = tblsploi.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             String maxoa = String.valueOf(modelsploi.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa" , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(j==JOptionPane.YES_OPTION)
            {
//            if(new BUS_XOA().xoaSP(maxoa))
//            {
//
//                modeldshd.setRowCount(0);
//               list = new LOADDULIEU().getList();
//        for(SANPHAM s : list)
//        {
//            modeldshd.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
//        }
//            }
            }
           
         
            
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
         }
    }//GEN-LAST:event_btnXoaKM1MousePressed

    private void btnReset2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReset2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReset2MousePressed

    private void txttimkiem3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiem3KeyReleased

    private void txttimkiem4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiem4KeyReleased

    private void txttimkiem5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiem5KeyReleased

    private void btnSua5btnSuaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSua5btnSuaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSua5btnSuaMousePressed

    private void combosp1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combosp1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_combosp1ItemStateChanged

    private void combosp1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combosp1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_combosp1FocusGained

    private void combosp1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combosp1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_combosp1FocusLost

    private void combosp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combosp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combosp1ActionPerformed

    private void jLabel77MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel77MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel77MousePressed

    private void jLabel78MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel78MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel78MousePressed

    private void jLabel79MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel79MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel79MousePressed

    private void jLabel80MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel80MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel80MousePressed

    private void lbdspxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdspxMousePressed
        card.show(panel,"dspx");
        
        lbdspx.setBackground(Color.WHITE);
        lbdspx.setOpaque(true);
        lbdspx.setForeground(Color.BLACK);
        
       lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        
        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
       
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbdspxMousePressed

    private void lbkktkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkktkMousePressed
        card.show(panel,"kktk");
        
        lbkktk.setBackground(Color.WHITE);
        lbkktk.setOpaque(true);
        lbkktk.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbkktkMousePressed

    private void txttimkiem6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiem6KeyReleased

    private void theoquyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_theoquyActionPerformed
        if(theoquy.isSelected())
        {
            theothang.setSelected(false);
            theonam.setSelected(false);
        }
    }//GEN-LAST:event_theoquyActionPerformed

    private void theothangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_theothangActionPerformed
        if(theothang.isSelected())
        {
            theoquy.setSelected(false);
            theonam.setSelected(false);
        }
    }//GEN-LAST:event_theothangActionPerformed

    private void theonamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_theonamActionPerformed
        if(theonam.isSelected())
        {
            theoquy.setSelected(false);
            theothang.setSelected(false);
        }
    }//GEN-LAST:event_theonamActionPerformed

    private void lbcalamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbcalamMousePressed
        card.show(panel,"calam");
        
        lbcalam.setBackground(Color.WHITE);
        lbcalam.setOpaque(true);
        lbcalam.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
        
        
        
        
        
        
       
    }//GEN-LAST:event_lbcalamMousePressed

    private void lbthongkeloinhuanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbthongkeloinhuanMousePressed
        card.show(panel,"tkln");
        
        lbthongkeloinhuan.setBackground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(true);
        lbthongkeloinhuan.setForeground(Color.BLACK);
        
        lbncc.setOpaque(false);
        lbncc.setForeground(Color.WHITE);
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
      
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
        
       
    }//GEN-LAST:event_lbthongkeloinhuanMousePressed

    private void lbnccMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbnccMousePressed
        card.show(panel,"ncc");
        
        lbncc.setBackground(Color.WHITE);
        lbncc.setOpaque(true);
        lbncc.setForeground(Color.BLACK);
        
        
        lbkktk.setOpaque(false);
        lbkktk.setForeground(Color.WHITE);
        
        lbsploi.setOpaque(false);
        lbsploi.setForeground(Color.WHITE);
        
        lbqltudo.setOpaque(false);
        lbqltudo.setForeground(Color.WHITE);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
        lbqltk.setOpaque(false);
        lbqltk.setForeground(Color.WHITE);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbdspx.setForeground(Color.WHITE);
        lbdspx.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongkedoanhthu.setForeground(Color.WHITE);
        lbthongkedoanhthu.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbthongkeloinhuan.setForeground(Color.WHITE);
        lbthongkeloinhuan.setOpaque(false);
        
       
        
        lbcalam.setForeground(Color.WHITE);
        lbcalam.setOpaque(false);
    }//GEN-LAST:event_lbnccMousePressed

    private void jLabel90MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel90MousePressed
        String ma = txtmancc.getText();
        String ten = txttenncc.getText();
        if(ma.isEmpty()||ten.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin !");
        }
        else
        {
            if(new BUS_THEM().ThemThongTinKhac("NHACUNGCAP",ma,ten))
            {
                txtmancc.setText("");
                txttenncc.setText("");
                LocListNCC();
            }
        }
    }//GEN-LAST:event_jLabel90MousePressed

    private void txtmanccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmanccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmanccActionPerformed

    private void jLabel82MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel82MouseClicked

    private void jLabel82MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel82MousePressed

    private void jLabel95MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel95MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel95MousePressed

    private void tbltklnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltklnMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbltklnMousePressed

    private void jLabel96MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel96MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel96MousePressed

    private void cbnam3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnam3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbnam3ActionPerformed

    private void cbnam4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnam4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbnam4ActionPerformed

    private void rdo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo6ActionPerformed

    private void cbthang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbthang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbthang1ActionPerformed

    private void cbquyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbquyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbquyActionPerformed

    private void jLabel91MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel91MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel91MouseClicked

    private void jLabel91MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel91MousePressed
        int i = tblcalam.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modelcalam.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(CALAM s : listcalam)
       {
           if(masua.equals(s.getMaCa()))
           {
               vitri = listcalam.indexOf(s);
               mp = s.getMaCa();
             //  ml = s.getMaLoai();
               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
        FORM_SUACALAM r = new FORM_SUACALAM();
        r.txtmaca.setText(listcalam.get(vitri).getMaCa());
        r.txtgiobd.setText(listcalam.get(vitri).getGioKT());
        r.txtgiokt.setText(String.valueOf(listcalam.get(vitri).getGioBD()));
        
//        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
//            {
//                r.comboncc.setSelectedIndex(j);
//                break;
//            }
//        }
//        for(int j=0;j<FORM_SUA.boxml.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxml.getElementAt(j)).contains(ml))
//            {
//                r.combomaloai.setSelectedIndex(j);
//                break;
//            }
//        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
       else
       {
           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
       }
    }//GEN-LAST:event_jLabel91MousePressed

    private void btnThemKM3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKM3MousePressed
        FORM_ADDCALAM calam = new FORM_ADDCALAM();
        calam.setVisible(true);
    }//GEN-LAST:event_btnThemKM3MousePressed

    private void btnXoaKM3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKM3MousePressed
        int ma = tblphancongca.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             String maxoa = String.valueOf(modelphancongca.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa" , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(j==JOptionPane.YES_OPTION)
            {
//            if(new BUS_XOA().xoaSP(maxoa))
//            {
//
//                modeldshd.setRowCount(0);
//               list = new LOADDULIEU().getList();
//        for(SANPHAM s : list)
//        {
//            modeldshd.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
//        }
//            }
            }
           
         
            
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
         }
    }//GEN-LAST:event_btnXoaKM3MousePressed

    private void jLabel94MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel94MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel94MousePressed

    private void jLabel98MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel98MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel98MousePressed

    private void btnThemKM4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemKM4MousePressed
        FORM_ADDPHANCONGCA pc = new FORM_ADDPHANCONGCA();
        pc.setVisible(true);
    }//GEN-LAST:event_btnThemKM4MousePressed

    private void jLabel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel99MouseClicked

    private void jLabel99MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel99MousePressed
     int i = tblphancongca.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modelphancongca.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(PHANCONGCA s : listphancongca)
       {
           if(masua.equals(s.getMaNV()))
           {
               vitri = listphancongca.indexOf(s);
               mp = s.getMaCa();
               ml = s.getMaNV();
               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
        FORM_SUAPHANCONGCA r = new FORM_SUAPHANCONGCA();
        r.txtmaca.setText(listnhanvien.get(vitri).getMaNV());
        r.txtmanv.setText(listnhanvien.get(vitri).getHoTen());
        
        
//        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
//            {
//                r.comboncc.setSelectedIndex(j);
//                break;
//            }
//        }
//        for(int j=0;j<FORM_SUANHANVIEN.boxml.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUANHANVIEN.boxml.getElementAt(j)).contains(ml))
//            {
//                r.combochucvu.setSelectedIndex(j);
//                break;
//            }
//        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
       else
       {
           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
       }
    }//GEN-LAST:event_jLabel99MousePressed

    private void jLabel100MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel100MousePressed

    private void jLabel102MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MousePressed
        int i = tblqlnv.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modelnhanvien.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(NHANVIEN s : listnhanvien)
       {
           if(masua.equals(s.getMaNV()))
           {
               vitri = listnhanvien.indexOf(s);
               mp = s.getGioiTinh();
               ml = s.getTenChucVu();
               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
        FORM_SUANHANVIEN r = new FORM_SUANHANVIEN();
        r.txtmanv.setText(listnhanvien.get(vitri).getMaNV());
        r.txttennv.setText(listnhanvien.get(vitri).getHoTen());
        
        
        r.txtsdt.setText(listnhanvien.get(vitri).getSDT());
        r.txtdiachi.setText(listnhanvien.get(vitri).getDiaChi());
        r.txtemail.setText(listnhanvien.get(vitri).getEmail());
//        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
//            {
//                r.comboncc.setSelectedIndex(j);
//                break;
//            }
//        }
//        for(int j=0;j<FORM_SUANHANVIEN.boxml.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUANHANVIEN.boxml.getElementAt(j)).contains(ml))
//            {
//                r.combochucvu.setSelectedIndex(j);
//                break;
//            }
//        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
       else
       {
           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
       }
    }//GEN-LAST:event_jLabel102MousePressed

    private void jLabel103MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel103MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel103MousePressed

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel64MouseClicked

    private void jLabel64MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MousePressed
        int ma = tbldshd.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             String maxoa = String.valueOf(modeldshd.getValueAt(ma, 0));
            int j = JOptionPane.showConfirmDialog(this,"Bạn có chắc chắn muốn xóa" , "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(j==JOptionPane.YES_OPTION)
            {
//            if(new BUS_XOA().xoaSP(maxoa))
//            {
//
//                modeldshd.setRowCount(0);
//               list = new LOADDULIEU().getList();
//        for(SANPHAM s : list)
//        {
//            modeldshd.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
//        }
//            }
            }
           
         
            
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane, "Hãy chọn dòng cần xóa !");
         }
       
    }//GEN-LAST:event_jLabel64MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        int i = tbldshd.getSelectedRow();
        if(i!=-1)
        {
            String ma = String.valueOf(modeldshd.getValueAt(i,0));
            int a = JOptionPane.showConfirmDialog(rootPane,"Xác nhận in hóa đơn mã " + ma + " ? ");
            if(a==0)
            {
                String dex="";
                for(HOADON hh : listhd)
                {
                    if(hh.getMaHD().equalsIgnoreCase(ma))
                    {
                        try {
                            dex = sdf2.format(sdf1.parse(String.valueOf(hh.getNgayTao())));
                        } catch (ParseException ex) {
                            Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
                File file = new File("C:\\HoaDonPDF");
                if (!file.exists()) {
                    if (file.mkdir()) {

                    }
                }
                String path="C:\\HoaDonPDF\\"+"MaHD_"+ma+".pdf";

                try {
                    if(d.InHD(ma,path,dex))
                    {
                        JOptionPane.showMessageDialog(rootPane,"In thành công");
                    }
                    else JOptionPane.showMessageDialog(rootPane,"In thất bại");
                } catch (IOException ex) {
                    Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Chọn mã cần in !");
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel104MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel104MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel104MouseClicked

    private void jLabel104MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel104MousePressed
        int i = tblqltudo.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modeltudo.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(TUDO s : listtudo)
       {
           if(masua.equals(s.getSoTu()))
           {
               vitri = listtudo.indexOf(s);
               mp = s.getSoTu();
             //  ml = s.getMaLoai();
               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
        FORM_SUATUDO r = new FORM_SUATUDO();
        r.txtsotu.setText(listtudo.get(vitri).getSoTu());
        r.txtmakh.setText(listtudo.get(vitri).getMaKH());
        r.txttenkh.setText(String.valueOf(listtudo.get(vitri).getTenKH()));
        r.txtsdt.setText(String.valueOf(listtudo.get(vitri).getSDT()));
//        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
//            {
//                r.comboncc.setSelectedIndex(j);
//                break;
//            }
//        }
//        for(int j=0;j<FORM_SUA.boxml.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxml.getElementAt(j)).contains(ml))
//            {
//                r.combomaloai.setSelectedIndex(j);
//                break;
//            }
//        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
       else
       {
           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
       }
    }//GEN-LAST:event_jLabel104MousePressed

    private void jLabel51MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel51MouseEntered

    private void jLabel105MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel105MousePressed
        int i = tblqlcv.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modelchucvu.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(CHUCVU s : listchucvu)
       {
           if(masua.equals(s.getMaCV()))
           {
               vitri = listchucvu.indexOf(s);
               mp = s.getMaCV();
             //  ml = s.getMaLoai();
               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
        FORM_SUACHUCVU r = new FORM_SUACHUCVU();
        r.txtmacv.setText(listchucvu.get(vitri).getMaCV());
        r.txttencv.setText(listchucvu.get(vitri).getTenCV());
        r.txtsoluong.setText(String.valueOf(listchucvu.get(vitri).getSoLuong()));
        r.txtluong.setText(String.valueOf(listchucvu.get(vitri).getLuong()));
//        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
//            {
//                r.comboncc.setSelectedIndex(j);
//                break;
//            }
//        }
//        for(int j=0;j<FORM_SUA.boxml.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxml.getElementAt(j)).contains(ml))
//            {
//                r.combomaloai.setSelectedIndex(j);
//                break;
//            }
//        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
       else
       {
           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
       }
       
       
    }//GEN-LAST:event_jLabel105MousePressed

    private void jPopupMenu1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenu1MouseReleased
        if(evt.isPopupTrigger()) {
    jPopupMenu1.show(this, evt.getX(), evt.getY());
 }
    }//GEN-LAST:event_jPopupMenu1MouseReleased

    private void chovaokhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chovaokhoActionPerformed
        
        
        int i = tblqlsp.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modelqlsp.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(SANPHAM s : list)
       {
           if(masua.equals(s.getMaSP()))
           {
               vitri = list.indexOf(s);
               mp = s.getMaSP();
//               ml = s.getMaLoai();
//               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
           FORM_CHOSPVAOKHO r = new FORM_CHOSPVAOKHO();
        r.txtmasp.setText(list.get(vitri).getMaSP());
        r.txttensp.setText(list.get(vitri).getTenSP());
        
//        r.txtsoluong.setText(String.valueOf(list.get(vitri).getSoLuong()));
//        r.txtdvt.setText(list.get(vitri).getDonViTinh());
//        r.txtgiaban.setText(String.valueOf(list.get(vitri).getGiaBan()).replace(".0", ""));
//        r.txtgianhap.setText(String.valueOf(list.get(vitri).getGiaNhap()).replace(".0", ""));
//        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
//            {
//                r.comboncc.setSelectedIndex(j);
//                break;
//            }
//        }
//        for(int j=0;j<FORM_SUA.boxml.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxml.getElementAt(j)).contains(ml))
//            {
//                r.combomaloai.setSelectedIndex(j);
//                break;
//            }
//        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
//       else
//       {
//           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
//       }
    }//GEN-LAST:event_chovaokhoActionPerformed

    private void tblqlspMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblqlspMousePressed
        int ma = tblqlsp.getSelectedRow();
     
      
       /*for(int i  = 0;i<list.size();i++)
       {
         System.out.println("model ma sp o vi tri "+i+" la " + modelqlsp.getValueAt(i, 0));
         System.out.println("list ma sp o vi tri "+i+" la " + list.get(i).getMaSP());
       }*/
       
         if(ma!=-1)
        {
             if(SwingUtilities.isRightMouseButton(evt))
             {
                 //String maxoa = String.valueOf(modelqlsp.getValueAt(ma, 0));
//             new FORM_CHOSPVAOKHO().setVisible(true);
                jPopupMenu1.show(this, evt.getX()+250, evt.getY()+200);
//            {
//            if(new BUS_XOA().xoaSP(maxoa))
//            {
//
//                modeldshd.setRowCount(0);
//               list = new LOADDULIEU().getList();
//        for(SANPHAM s : list)
//        {
//            modeldshd.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
//        }
//            }
//            }
             }
           
         
            
        }
        else
       {
           JOptionPane.showMessageDialog(rootPane,"Hãy chọn thông tin cần sửa");
       }
       
//    }                          
    }//GEN-LAST:event_tblqlspMousePressed

    private void baoloispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baoloispActionPerformed
       int i = tblqlsp.getSelectedRow();
        
       if(i!=-1)
       {
           String masua = String.valueOf(modelqlsp.getValueAt(i,0));
        int vitri=0;
        String mp="",ml="";
       for(SANPHAM s : list)
       {
           if(masua.equals(s.getMaSP()))
           {
               vitri = list.indexOf(s);
               mp = s.getMaSP();
//               ml = s.getMaLoai();
//               System.out.println("Ma nsx la " + mp);
               break;
           }
       }
           FORM_BAOLOISANPHAM r = new FORM_BAOLOISANPHAM();
        r.txtmasp.setText(list.get(vitri).getMaSP());
        r.txttensp.setText(list.get(vitri).getTenSP());
        
//        r.txtsoluong.setText(String.valueOf(list.get(vitri).getSoLuong()));
//        r.txtdvt.setText(list.get(vitri).getDonViTinh());
//        r.txtgiaban.setText(String.valueOf(list.get(vitri).getGiaBan()).replace(".0", ""));
//        r.txtgianhap.setText(String.valueOf(list.get(vitri).getGiaNhap()).replace(".0", ""));
//        for(int j=0;j<FORM_SUA.boxncc.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxncc.getElementAt(j)).contains(mp))
//            {
//                r.comboncc.setSelectedIndex(j);
//                break;
//            }
//        }
//        for(int j=0;j<FORM_SUA.boxml.getSize();j++)
//        {
//            if(String.valueOf(FORM_SUA.boxml.getElementAt(j)).contains(ml))
//            {
//                r.combomaloai.setSelectedIndex(j);
//                break;
//            }
//        }
        r.dongcansua = vitri;
        r.macansua = masua;
        r.setVisible(true);
       }
    }//GEN-LAST:event_baoloispActionPerformed

    public void ThemAnh(String name)
    {
           int conf = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thêm ảnh ?");
        if(conf==0)
        {
        File destination = new File("..\\DemoDoAn\\src\\Image\\"+name+".jpg");
        File browser = new File(path);
         try {
            Files.copy(browser.toPath(),destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(rootPane, "Đã thêm ảnh");
             setAvatar(ttdn.get(0).getUser());
             path = null;
             
            
        
        } catch (IOException ex) {
           
            JOptionPane.showMessageDialog(rootPane, "Lỗi");
        }
        }
        else
        {
            
        }
    }
    public ImageIcon Resize(String name,int rong,int cao)
    {
        ImageIcon myimg = new ImageIcon(name);
        Image img = myimg.getImage();
        Image newimg = img.getScaledInstance(rong,cao, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(newimg);
        return image;
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GIAODIENCHINH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GIAODIENCHINH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem baoloisp;
    public static javax.swing.JButton bienmat;
    private javax.swing.JLabel btnReset1;
    private javax.swing.JLabel btnReset2;
    private javax.swing.JLabel btnReset5;
    private javax.swing.JLabel btnSua4;
    private javax.swing.JLabel btnSua5;
    private javax.swing.JLabel btnThem4;
    private javax.swing.JLabel btnThemKM;
    private javax.swing.JLabel btnThemKM1;
    private javax.swing.JLabel btnThemKM3;
    private javax.swing.JLabel btnThemKM4;
    private javax.swing.JLabel btnXoaKM1;
    private javax.swing.JLabel btnXoaKM3;
    private javax.swing.JPanel calam;
    private javax.swing.JComboBox<String> cbnam;
    private javax.swing.JComboBox<String> cbnam3;
    private javax.swing.JComboBox<String> cbnam4;
    private javax.swing.JComboBox<String> cbquy;
    private javax.swing.JComboBox<String> cbthang;
    private javax.swing.JComboBox<String> cbthang1;
    private javax.swing.JComboBox<String> cbthang2;
    private javax.swing.JMenuItem chovaokho;
    private javax.swing.JComboBox<String> combosp;
    private javax.swing.JComboBox<String> combosp1;
    private javax.swing.JPanel ctkm;
    private com.toedter.calendar.JDateChooser day1;
    private com.toedter.calendar.JDateChooser day2;
    private com.toedter.calendar.JDateChooser day3;
    private com.toedter.calendar.JDateChooser day5;
    private com.toedter.calendar.JDateChooser day6;
    private javax.swing.JPanel dshd;
    private javax.swing.JPanel dsnv;
    private javax.swing.JPanel dspn;
    private javax.swing.JPanel dspx;
    private javax.swing.JLabel dvtpn;
    private javax.swing.JLabel dvtpn1;
    private GUI.FORM_CTHDadmin fORM_CTHDadmin1;
    private javax.swing.ButtonGroup groupthongke;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxQuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    public javax.swing.JLabel jLabel102;
    public javax.swing.JLabel jLabel103;
    public javax.swing.JLabel jLabel104;
    public javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    public javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    public javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel khachhang;
    private javax.swing.JPanel kktk;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMaNV4;
    private javax.swing.JLabel lbMatKhau4;
    private javax.swing.JLabel lbMini;
    private javax.swing.JLabel lbTaiKhoan4;
    private javax.swing.JLabel lbcalam;
    private javax.swing.JLabel lbdshd;
    private javax.swing.JLabel lbdspn;
    private javax.swing.JLabel lbdspx;
    public static javax.swing.JLabel lbhello;
    private javax.swing.JLabel lbkhachhang;
    private javax.swing.JLabel lbkktk;
    private javax.swing.JLabel lbkm;
    private javax.swing.JLabel lbncc;
    private javax.swing.JLabel lbqlcv;
    private javax.swing.JLabel lbqlnv;
    private javax.swing.JLabel lbqlsp;
    private javax.swing.JLabel lbqltk;
    private javax.swing.JLabel lbqltudo;
    private javax.swing.JLabel lbsploi;
    private javax.swing.JLabel lbthongkedoanhthu;
    private javax.swing.JLabel lbthongkeloinhuan;
    private javax.swing.JLabel lbtime;
    public static javax.swing.JLabel lbtksp;
    private javax.swing.JLabel lbtoday;
    public static javax.swing.JLabel lbtongdoanhthu;
    public static javax.swing.JLabel lbtongdoanhthu1;
    private javax.swing.JLabel lbttcn;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel me;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel ncc;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnttcn;
    private javax.swing.JPanel qlcv;
    private javax.swing.JPanel qlsp;
    private javax.swing.JPanel qltk;
    private javax.swing.JPanel qltudo;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JRadioButton rdo2;
    private javax.swing.JRadioButton rdo3;
    private javax.swing.JRadioButton rdo4;
    private javax.swing.JRadioButton rdo5;
    private javax.swing.JRadioButton rdo6;
    public javax.swing.JLabel refresh;
    public javax.swing.JLabel refresh1;
    public javax.swing.JLabel refresh2;
    private javax.swing.JTextField slnhap;
    private javax.swing.JTextField slnhap1;
    private javax.swing.JPanel sploi;
    private javax.swing.JPanel task;
    private javax.swing.JTable tblcalam;
    private javax.swing.JTable tblctkh;
    private javax.swing.JTable tblctpn;
    private javax.swing.JTable tblctpx;
    private javax.swing.JTable tblcttk;
    private javax.swing.JTable tbldshd;
    private javax.swing.JTable tblkhachhang;
    private javax.swing.JTable tblkho;
    private javax.swing.JTable tblkm;
    private javax.swing.JTable tblncc;
    private javax.swing.JTable tblphancongca;
    private javax.swing.JTable tblphieunhap;
    private javax.swing.JTable tblphieuxuat;
    public static javax.swing.JTable tblqlcv;
    private javax.swing.JTable tblqlnv;
    public static javax.swing.JTable tblqlsp;
    public static javax.swing.JTable tblqltudo;
    private javax.swing.JTable tblsploi;
    private javax.swing.JTable tbltaikhoan;
    private javax.swing.JTable tblthongke;
    private javax.swing.JTable tblthongtinkho;
    private javax.swing.JTable tbltkln;
    private javax.swing.JTable tbltksp;
    private javax.swing.JRadioButton theonam;
    private javax.swing.JRadioButton theoquy;
    private javax.swing.JRadioButton theothang;
    private javax.swing.JPanel thongke;
    private javax.swing.JPanel thongkeloinhuan;
    private javax.swing.JPanel ttcn;
    private javax.swing.JTextField ttdndiachi;
    private javax.swing.JTextField ttdnemail;
    private javax.swing.JTextField ttdnhoten;
    private javax.swing.JTextField ttdnmanv;
    private javax.swing.JTextField ttdnngaysinh;
    private javax.swing.JTextField ttdnsdt;
    private javax.swing.JTextField txtMaCT;
    private javax.swing.JTextField txtMaCT1;
    private javax.swing.JTextField txtMaNCC;
    private javax.swing.JTextField txtMaNV4;
    private javax.swing.JTextField txtMatKhau4;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtPhanTram;
    private javax.swing.JTextField txtPhanTram1;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTaiKhoan4;
    private javax.swing.JTextField txtTenCT;
    private javax.swing.JTextField txtTenCT1;
    private javax.swing.JComboBox<String> txtTrangThai4;
    private javax.swing.JTextField txtmancc;
    private javax.swing.JTextField txtsdtkh;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttenncc;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txttimkiem1;
    private javax.swing.JTextField txttimkiem2;
    private javax.swing.JTextField txttimkiem3;
    private javax.swing.JTextField txttimkiem4;
    private javax.swing.JTextField txttimkiem5;
    private javax.swing.JTextField txttimkiem6;
    // End of variables declaration//GEN-END:variables
}
