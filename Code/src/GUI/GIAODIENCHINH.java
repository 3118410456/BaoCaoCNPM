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
import DTO.CHUCVU;
import DTO.CTPN;
import DTO.KHACHHANG;
import DTO.NHANVIEN;
import DTO.PHIEUNHAP;
import DTO.TAIKHOAN;
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
    public static DefaultTableModel modelqlsp,modeldshd,modelcthd,modelthongke,modelcttk,modeltksp
            ,modelkm,modelkhachhang,modelctkh,modelctpn,modelphieunhap,modelchucvu,modeltaikhoan,modelnhanvien;
   int mx,my;
   
    public GIAODIENCHINH() {
       
        
        initComponents();
        boxpn = (DefaultComboBoxModel) combosp.getModel();
        KhoiTaoGiaoDien();
        this.setSize(1310,727);
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
    }
    public static void LocListHD()
    {
        modeldshd.setRowCount(0);
        modelthongke.setRowCount(0);
        listhd = new LOADDULIEU().getListHD();
        for(HOADON h : listhd)
        {
            modeldshd.addRow(new Object[]{h.getMaHD(),h.getMaNV(),h.getMaKH(),h.getMaKM(),h.getNgayTao(),vnmoney.format(h.getTongCong()),h.getPhanTramKM(),vnmoney.format(h.getTongTienTra())});
            //modelthongke.addRow(new Object[]{h.getMaHD(),h.getMaNV(),sdf2.format(sdf1.parse(String.valueOf(h.getNgayTao()))),vnmoney.format(h.getTongCong()),});
          
        }
    }
    public static void LocListSP()
    {
        modelqlsp.setRowCount(0);
        boxpn.removeAllElements();
        list = new LOADDULIEU().getList();
        for(SANPHAM s : list)
        {
            boxpn.addElement(s.getMaSP() + "   " +s.getTenSP());
            modelqlsp.addRow(new Object[]{s.getMaSP(),s.getTenSP(),s.getMaLoai(),s.getSoLuong(),s.getDonViTinh(),vnmoney.format(s.getGiaBan()),vnmoney.format(s.getGiaNhap()),s.getMaNCC()});
        }
    }
    public static void LocListPhieuNhap()
    {
        modelphieunhap.setRowCount(0);
        listphieunhap = new LOADDULIEU().getListPhieuNhap();
        for(PHIEUNHAP pn : listphieunhap)
        {
            modelphieunhap.addRow(new Object[]{pn.getMaPN(),pn.getMaKho(),pn.getMaNV(),pn.getMaNCC(),pn.getNgayNhap(), vnmoney.format(pn.getTongTien())});
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
    public static void LocListCTPN()
    {
        modelctpn.setRowCount(0);
        listctpn = new LOADDULIEU().getListCTPN();
        for(CTPN ctpn : listctpn)
        {
            modelctpn.addRow(new Object[]{ctpn.getMaSP(),ctpn.getTenSP(),ctpn.getTenLoaiSP(),ctpn.getSoLuong(),ctpn.getDonViTinh(),vnmoney.format(ctpn.getGiaNhap()),/*vnmoney.format(ctpn.getThanhTien())*/});
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
        menu = new javax.swing.JPanel();
        lbdshd = new javax.swing.JLabel();
        lbqlsp = new javax.swing.JLabel();
        lbttcn = new javax.swing.JLabel();
        lbdspn = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        lbmenu = new javax.swing.JLabel();
        lbthongke = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        lbkm = new javax.swing.JLabel();
        lbkhachhang = new javax.swing.JLabel();
        lbqlcv = new javax.swing.JLabel();
        lbqltk = new javax.swing.JLabel();
        lbqlnv = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        dshd = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldshd = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
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
        combokho = new javax.swing.JComboBox<>();
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
        btnXoaKM = new javax.swing.JLabel();
        btnReset1 = new javax.swing.JLabel();
        txtPhanTram = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        txtMaCT = new javax.swing.JTextField();
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
        qlcv = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblqlcv = new javax.swing.JTable();
        txttimkiem1 = new javax.swing.JTextField();
        refresh1 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
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
        btnChiTiet4 = new javax.swing.JLabel();
        btnThem4 = new javax.swing.JLabel();
        btnSua4 = new javax.swing.JLabel();
        dsnv = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblqlnv = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        task = new javax.swing.JPanel();
        lbMini = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        lbtoday = new javax.swing.JLabel();
        lbtime = new javax.swing.JLabel();
        lbhello = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(null);

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
        lbdshd.setBounds(0, 230, 240, 50);

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
        lbqlsp.setBounds(0, 180, 240, 50);

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
        lbttcn.setBounds(0, 480, 240, 50);

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
        lbdspn.setBounds(0, 280, 240, 50);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/logo.png"))); // NOI18N
        menu.add(logo);
        logo.setBounds(30, 10, 190, 160);

        lbmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/menu.png"))); // NOI18N
        lbmenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbmenuMousePressed(evt);
            }
        });
        menu.add(lbmenu);
        lbmenu.setBounds(190, 680, 50, 50);

        lbthongke.setBackground(new java.awt.Color(255, 255, 255));
        lbthongke.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lbthongke.setForeground(new java.awt.Color(255, 255, 255));
        lbthongke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbthongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/iconthongke.png"))); // NOI18N
        lbthongke.setText("THỐNG KÊ DOANH THU");
        lbthongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbthongkeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbthongkeMouseReleased(evt);
            }
        });
        menu.add(lbthongke);
        lbthongke.setBounds(0, 330, 240, 50);

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
        logout.setBounds(10, 680, 140, 50);

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
        lbkm.setBounds(0, 380, 240, 50);

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
        lbkhachhang.setBounds(0, 430, 240, 50);

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
        lbqlcv.setBounds(0, 530, 240, 50);

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
        lbqltk.setBounds(0, 580, 240, 50);

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
        lbqlnv.setBounds(0, 630, 240, 50);

        getContentPane().add(menu);
        menu.setBounds(0, 0, 240, 730);

        panel.setBackground(new java.awt.Color(0, 0, 0));
        panel.setLayout(new java.awt.CardLayout());

        dshd.setBackground(new java.awt.Color(47, 48, 48));

        tbldshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Mã nhân viên", "Khách hàng", "Mã khuyến mãi", "Ngày xuất", "Tổng tiền", "Phần trăm KM", "Tổng tiền trả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        javax.swing.GroupLayout dshdLayout = new javax.swing.GroupLayout(dshd);
        dshd.setLayout(dshdLayout);
        dshdLayout.setHorizontalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1043, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(dshdLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dshdLayout.setVerticalGroup(
            dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dshdLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dshdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panel.add(dshd, "dshd");

        dspn.setBackground(new java.awt.Color(47, 48, 48));
        dspn.setLayout(null);

        tblphieunhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu nhập", "Mã Kho", "Mã Nhân viên", "Mã NCC", "Ngày nhập", "Tổng tiền"
            }
        ));
        tblphieunhap.setRowHeight(35);
        jScrollPane10.setViewportView(tblphieunhap);

        dspn.add(jScrollPane10);
        jScrollPane10.setBounds(15, 359, 1043, 295);

        tblctpn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Giá nhập", "Số lượng", "Thành tiền"
            }
        ));
        tblctpn.setRowHeight(35);
        jScrollPane9.setViewportView(tblctpn);

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
        slnhap.setBounds(790, 190, 180, 35);

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Số lượng nhập");
        dspn.add(jLabel44);
        jLabel44.setBounds(680, 200, 110, 16);

        dvtpn.setBackground(new java.awt.Color(255, 255, 255));
        dvtpn.setForeground(new java.awt.Color(255, 255, 255));
        dvtpn.setText("jLabel45");
        dspn.add(dvtpn);
        dvtpn.setBounds(790, 160, 170, 16);

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Đơn vị tính :");
        dspn.add(jLabel45);
        jLabel45.setBounds(680, 160, 100, 16);

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

        combokho.setMaximumRowCount(100);
        combokho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combokhoItemStateChanged(evt);
            }
        });
        combokho.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combokhoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                combokhoFocusLost(evt);
            }
        });
        combokho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combokhoActionPerformed(evt);
            }
        });
        dspn.add(combokho);
        combokho.setBounds(680, 110, 340, 40);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(57, Short.MAX_VALUE))
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
        jScrollPane1.setViewportView(tblqlsp);
        if (tblqlsp.getColumnModel().getColumnCount() > 0) {
            tblqlsp.getColumnModel().getColumn(0).setHeaderValue("Mã sản phẩm");
            tblqlsp.getColumnModel().getColumn(1).setHeaderValue("Tên sản phẩm");
            tblqlsp.getColumnModel().getColumn(2).setHeaderValue(" Loại sản phẩm");
            tblqlsp.getColumnModel().getColumn(3).setHeaderValue("Số lượng");
            tblqlsp.getColumnModel().getColumn(4).setHeaderValue("Đơn vị tính");
            tblqlsp.getColumnModel().getColumn(5).setHeaderValue("Đơn giá bán");
            tblqlsp.getColumnModel().getColumn(6).setHeaderValue("Đơn giá nhập");
            tblqlsp.getColumnModel().getColumn(7).setHeaderValue("Nhà cung cấp");
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlspLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel.add(qlsp, "qlsp");

        thongke.setBackground(new java.awt.Color(47, 48, 48));

        groupthongke.add(rdo1);
        rdo1.setForeground(new java.awt.Color(255, 255, 255));
        rdo1.setSelected(true);
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
                        .addGap(28, 28, 28)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel17))
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
                        .addComponent(day3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addGroup(thongkeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(thongkeLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbtongdoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
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
                            .addComponent(jLabel17)))
                    .addComponent(jScrollPane3))
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

        btnXoaKM.setBackground(new java.awt.Color(61, 145, 106));
        btnXoaKM.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        btnXoaKM.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/bin.png"))); // NOI18N
        btnXoaKM.setText(" Xóa");
        btnXoaKM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaKM.setOpaque(true);
        btnXoaKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaKMMousePressed(evt);
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
                                .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset1)))))
                .addContainerGap(62, Short.MAX_VALUE))
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
                                .addGap(30, 30, 30)
                                .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(ctkmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnXoaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnReset1)))))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                .addComponent(txtsdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel42))
                            .addGroup(khachhangLayout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)))))
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
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsdtkh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(khachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                            .addComponent(jScrollPane7)))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Mã chức vụ");

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Mã chức vụ");

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Lương");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel51.setBackground(new java.awt.Color(61, 145, 106));
        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/add.png"))); // NOI18N
        jLabel51.setText("Thêm");
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel51.setOpaque(true);
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
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

        javax.swing.GroupLayout qlcvLayout = new javax.swing.GroupLayout(qlcv);
        qlcv.setLayout(qlcvLayout);
        qlcvLayout.setHorizontalGroup(
            qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(qlcvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11)
                    .addGroup(qlcvLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(qlcvLayout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(qlcvLayout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(refresh1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        qlcvLayout.setVerticalGroup(
            qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, qlcvLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel54)
                        .addComponent(txttimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refresh1)
                        .addGroup(qlcvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnChiTiet4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/chitiet.png"))); // NOI18N
        btnChiTiet4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnChiTiet4btnChiTietMousePressed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThem4)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua4)
                        .addGap(18, 18, 18)
                        .addComponent(btnChiTiet4)
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
                            .addComponent(btnChiTiet4)
                            .addComponent(btnThem4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 43, Short.MAX_VALUE)))
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

        jLabel64.setBackground(new java.awt.Color(61, 145, 106));
        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn/edit.png"))); // NOI18N
        jLabel64.setText(" Sửa");
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

        javax.swing.GroupLayout dsnvLayout = new javax.swing.GroupLayout(dsnv);
        dsnv.setLayout(dsnvLayout);
        dsnvLayout.setHorizontalGroup(
            dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dsnvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(dsnvLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dsnvLayout.setVerticalGroup(
            dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dsnvLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(dsnvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        panel.add(dsnv, "qlnv");

        getContentPane().add(panel);
        panel.setBounds(240, 60, 1070, 670);

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
        task.setLayout(null);

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
        task.add(lbMini);
        lbMini.setBounds(1150, 0, 80, 60);

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
        task.add(lbClose);
        lbClose.setBounds(1230, 0, 80, 60);

        lbtoday.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbtoday.setForeground(new java.awt.Color(255, 255, 255));
        lbtoday.setText("date");
        task.add(lbtoday);
        lbtoday.setBounds(860, 0, 150, 50);

        lbtime.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbtime.setForeground(new java.awt.Color(255, 255, 255));
        lbtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbtime.setText("time");
        task.add(lbtime);
        lbtime.setBounds(1020, 0, 120, 50);

        lbhello.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lbhello.setForeground(new java.awt.Color(255, 255, 255));
        lbhello.setText("Xin chào : ");
        task.add(lbhello);
        lbhello.setBounds(260, 5, 510, 40);

        getContentPane().add(task);
        task.setBounds(0, 0, 1310, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
      
    }//GEN-LAST:event_jLabel3MouseClicked

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
       int a = JOptionPane.showConfirmDialog(rootPane, "Xác nhận thoát chương trình ?");
         if(a==0)
         {
        System.exit(0);
         }
    }//GEN-LAST:event_lbCloseMouseClicked

    private void lbMiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseClicked
        this.setExtendedState(GIAODIENCHINH.ICONIFIED);
    }//GEN-LAST:event_lbMiniMouseClicked

    private void lbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseEntered
        lbClose.setForeground(Color.BLACK);
        lbClose.setOpaque(true);
        lbClose.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbCloseMouseEntered

    private void lbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseExited
         lbClose.setForeground(Color.WHITE);
        lbClose.setOpaque(false);
        lbClose.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbCloseMouseExited

    private void lbMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseEntered
        lbMini.setForeground(Color.BLACK);
        lbMini.setOpaque(true);
        lbMini.setBackground(Color.WHITE);
    }//GEN-LAST:event_lbMiniMouseEntered

    private void lbMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMiniMouseExited
         lbMini.setForeground(Color.WHITE);
        lbMini.setOpaque(false);
        lbMini.setBackground(new Color(61,145,106));
    }//GEN-LAST:event_lbMiniMouseExited

    private void taskMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x-mx, y-my);
    }//GEN-LAST:event_taskMouseDragged

    private void taskMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskMousePressed
        mx = evt.getX();
        my = evt.getY();
    }//GEN-LAST:event_taskMousePressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
       
    }//GEN-LAST:event_jLabel5MouseClicked
   
    private void lbqlspMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlspMousePressed

        card.show(panel,"qlsp");
        
        lbqlsp.setBackground(Color.WHITE);
        lbqlsp.setOpaque(true);
        lbqlsp.setForeground(Color.BLACK);
        
        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);
        
        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);
        
        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqlcv.setForeground(Color.WHITE);
        lbqlcv.setOpaque(false);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbqlspMousePressed

    private void lbdshdMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdshdMousePressed
        card.show(panel,"dshd");
        lbdshd.setBackground(Color.WHITE);
        lbdshd.setOpaque(true);
        lbdshd.setForeground(Color.BLACK);
  
        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);
        
        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);
        
        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqlcv.setForeground(Color.WHITE);
        lbqlcv.setOpaque(false);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbdshdMousePressed

    private void lbdspnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdspnMousePressed
        card.show(panel,"dspn");
        lbdspn.setBackground(Color.WHITE);
        lbdspn.setOpaque(true);
        lbdspn.setForeground(Color.BLACK);
        
        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);
        
        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);
        
        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);
        
        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqlcv.setForeground(Color.WHITE);
        lbqlcv.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
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
            int j = JOptionPane.showConfirmDialog(rootPane,"Bạn có chắc chắn muốn xóa ?");
            if(j==0)
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

    private void lbmenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbmenuMousePressed
        if(lbmenu.getX()==180)
        {
        Thread t = new Thread(){
            public void run()
            {
                try {
                    logo.setVisible(false);
        lbqlsp.setVisible(false);
        lbdshd.setVisible(false);
        lbdspn.setVisible(false);
        lbttcn.setVisible(false);
        lbkm.setVisible(false);
        logout.setVisible(false);
        lbthongke.setVisible(false);
        lbkhachhang.setVisible(false);
        lbqlcv.setVisible(false);
        lbqltk.setVisible(false);
        lbqlnv.setVisible(false);
     
        panel.setBounds(75,60, 1235, 670);
                    for(int i = 240;i>=75;i--)
                    {   sleep(1);
                        menu.setSize(i, 727);
                        
                    }
                    lbhello.setBounds(95, 5, 510, 40);
                        lbmenu.setBounds(10,670,50,50);
                  
                } catch (Exception e) {
                    
                }
            }
       
    };
        t.start();
        
        }
        
        //mo
        if(lbmenu.getX()==10)
        {
        Thread tx = new Thread(){
            public void run()
            {
                try {
                
                    for(int i = 75;i<=240;i++)
                    {   sleep(1);
                        menu.setSize(i, 727);
                    }
                     panel.setBounds(240,60, 1070, 670);
                  lbhello.setBounds(260, 5, 510, 40);
                       logo.setVisible(true);
        lbqlsp.setVisible(true);
        lbdshd.setVisible(true);
        lbdspn.setVisible(true);
        lbttcn.setVisible(true);
        lbkm.setVisible(true);
        logout.setVisible(true);
        lbthongke.setVisible(true);
        lbkhachhang.setVisible(true);
        lbqlcv.setVisible(true);
        lbqltk.setVisible(true);
        lbqlnv.setVisible(true);
         lbmenu.setBounds(180,670,50,50);
                } catch (Exception e) {
                    
                }
            }
       
    };
        tx.start();
        
        }
    }//GEN-LAST:event_lbmenuMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        /* int rd = (int) (Math.random() * ((999999 - 100000) + 1)) + 100000;
         String rd2= String.valueOf(rd);
           
        String a = taochuoi(3) + rd2;
        HOADON h = new HOADON();
        h.setMahd(a);
        h.setManv(ttdn.get(0).getManv());
        Date da = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        String now = f.format(da);
        h.setNgayxuat(now);
        h.setTongtien(0.0);
        if(new BUS_THEM().themHD(h))
        {
            JOptionPane.showMessageDialog(rootPane,"Đã tạo hóa đơn ! ","Thông báo",JOptionPane.DEFAULT_OPTION);
            listhd.add(h);
            listhd = new LOADDULIEU().getListHD();
            modeldshd.setRowCount(0);
        for(HOADON hc : listhd)
        {
            try {
                String dayhd = sdf2.format(sdf1.parse(String.valueOf(hc.getNgayxuat())));
                  modeldshd.addRow(new Object[]{hc.getMahd(),hc.getManv(),dayhd,vnmoney.format(hc.getTongtien()),hc.getMakm()});
            
            } catch (ParseException ex) {
                Logger.getLogger(GIAODIENCHINH.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Them that bai");
        }*/
        new GDBH().setVisible(true);
    }//GEN-LAST:event_jLabel1MousePressed

    private void lbthongkeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbthongkeMousePressed
       card.show(panel,"tk");
        
        lbthongke.setBackground(Color.WHITE);
        lbthongke.setOpaque(true);
        lbthongke.setForeground(Color.BLACK);
        
        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);
        
        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);
        
        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);
        
        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqlcv.setForeground(Color.WHITE);
        lbqlcv.setOpaque(false);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbthongkeMousePressed

    private void lbttcnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbttcnMousePressed
        card.show(panel,"ttcn");
        lbttcn.setBackground(Color.WHITE);
        lbttcn.setOpaque(true);
        lbttcn.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqlcv.setOpaque(false);
        lbqlcv.setForeground(Color.WHITE);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbttcnMousePressed

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

    private void btnXoaKMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaKMMousePressed
        // TODO add your handling code here:
        int i = tblkm.getSelectedRow();
        if( i >= 0)
        {
            KHUYENMAI km = new KHUYENMAI();
            String ma = listkm.get(i).getMaKM();
            System.out.println(ma);
            int conf = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa");
            if(new BUS_XOA().xoaKM(ma) && conf == 0 )
            {
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
                modelkm.setRowCount(0);
                listkm = new LOADDULIEU().getListKhuyenMai();

                for(KHUYENMAI ttkm : listkm)
                {
                    modelkm.addRow(new Object[] {ttkm.getMaKM() , ttkm.getTenCT() , ttkm.getNgayBatDau() , ttkm.getNgayKetThuc() , ttkm.getPhanTram()});
                }
            }

        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng cần xóa");
        }
    }//GEN-LAST:event_btnXoaKMMousePressed

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

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqlcv.setForeground(Color.WHITE);
        lbqlcv.setOpaque(false);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbkmMousePressed

    private void lbthongkeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbthongkeMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbthongkeMouseReleased

    private void lbkhachhangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbkhachhangMousePressed
        card.show(panel, "nsx");lbkm.setBackground(Color.WHITE);
        lbkhachhang.setOpaque(true);
        lbkhachhang.setForeground(Color.BLACK);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbttcn.setForeground(Color.WHITE);
        lbttcn.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbqlcv.setForeground(Color.WHITE);
        lbqlcv.setOpaque(false);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
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
      int xn = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa khách hàng này ?");
      if(xn==0)
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
                dvtpn.setText(s.getDonViTinh());
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

    private void combokhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combokhoItemStateChanged
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
                    dvtpn.setText(s.getDonViTinh());
                    break;
                }
            }
        }       
    }//GEN-LAST:event_combokhoItemStateChanged

    private void combokhoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combokhoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_combokhoFocusGained

    private void combokhoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combokhoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_combokhoFocusLost

    private void combokhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combokhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combokhoActionPerformed

    private void lbqlcvMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlcvMousePressed
        card.show(panel,"qlcv");
        lbqlcv.setBackground(Color.WHITE);
        lbqlcv.setOpaque(true);
        lbqlcv.setForeground(Color.BLACK);
        
        
        lbttcn.setOpaque(false);
        lbttcn.setForeground(Color.WHITE);

        lbdshd.setForeground(Color.WHITE);
        lbdshd.setOpaque(false);

        lbdspn.setForeground(Color.WHITE);
        lbdspn.setOpaque(false);

        lbqlsp.setForeground(Color.WHITE);
        lbqlsp.setOpaque(false);

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqltk.setForeground(Color.WHITE);
        lbqltk.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
    }//GEN-LAST:event_lbqlcvMousePressed

    private void txttimkiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiem1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiem1KeyReleased

    private void refresh1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refresh1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_refresh1MousePressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void lbqlspMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqlspMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lbqlspMouseExited

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel51MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel51MousePressed

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel52MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel52MousePressed

    private void lbqltkMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbqltkMousePressed
        card.show(panel,"qltk");
        
        lbqltk.setBackground(Color.WHITE);
        lbqltk.setOpaque(true);
        lbqltk.setForeground(Color.BLACK);
        
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

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
        lbqlnv.setOpaque(false);
        lbqlnv.setForeground(Color.WHITE);
        
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

    private void btnChiTiet4btnChiTietMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiTiet4btnChiTietMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChiTiet4btnChiTietMousePressed

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

        lbthongke.setForeground(Color.WHITE);
        lbthongke.setOpaque(false);
        
        lbkm.setForeground(Color.WHITE);
        lbkm.setOpaque(false);
        
        lbkhachhang.setForeground(Color.WHITE);
        lbkhachhang.setOpaque(false);
        
    }//GEN-LAST:event_lbqlnvMousePressed

    private void jLabel63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel63MouseClicked

    private void jLabel63MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel63MousePressed
        FORM_ADDNHANVIEN t = new FORM_ADDNHANVIEN();
       t.setVisible(true);
    }//GEN-LAST:event_jLabel63MousePressed

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel64MouseClicked

    private void jLabel64MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel64MousePressed

    private void jLabel65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel65MouseClicked

    private void jLabel65MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel65MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel65MousePressed

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
    public static javax.swing.JButton bienmat;
    private javax.swing.JLabel btnChiTiet4;
    private javax.swing.JLabel btnReset1;
    private javax.swing.JLabel btnReset5;
    private javax.swing.JLabel btnSua4;
    private javax.swing.JLabel btnThem4;
    private javax.swing.JLabel btnThemKM;
    private javax.swing.JLabel btnXoaKM;
    private javax.swing.JComboBox<String> cbnam;
    private javax.swing.JComboBox<String> cbthang;
    private javax.swing.JComboBox<String> combokho;
    private javax.swing.JComboBox<String> combosp;
    private javax.swing.JPanel ctkm;
    private com.toedter.calendar.JDateChooser day1;
    private com.toedter.calendar.JDateChooser day2;
    private com.toedter.calendar.JDateChooser day3;
    private javax.swing.JPanel dshd;
    private javax.swing.JPanel dsnv;
    private javax.swing.JPanel dspn;
    private javax.swing.JLabel dvtpn;
    private GUI.FORM_CTHDadmin fORM_CTHDadmin1;
    private javax.swing.ButtonGroup groupthongke;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    public javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JPanel khachhang;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbMaNV4;
    private javax.swing.JLabel lbMatKhau4;
    private javax.swing.JLabel lbMini;
    private javax.swing.JLabel lbTaiKhoan4;
    private javax.swing.JLabel lbdshd;
    private javax.swing.JLabel lbdspn;
    public static javax.swing.JLabel lbhello;
    private javax.swing.JLabel lbkhachhang;
    private javax.swing.JLabel lbkm;
    private javax.swing.JLabel lbmenu;
    private javax.swing.JLabel lbqlcv;
    private javax.swing.JLabel lbqlnv;
    private javax.swing.JLabel lbqlsp;
    private javax.swing.JLabel lbqltk;
    private javax.swing.JLabel lbthongke;
    private javax.swing.JLabel lbtime;
    public static javax.swing.JLabel lbtksp;
    private javax.swing.JLabel lbtoday;
    public static javax.swing.JLabel lbtongdoanhthu;
    private javax.swing.JLabel lbttcn;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logout;
    private javax.swing.JLabel me;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnttcn;
    private javax.swing.JPanel qlcv;
    private javax.swing.JPanel qlsp;
    private javax.swing.JPanel qltk;
    private javax.swing.JRadioButton rdo1;
    private javax.swing.JRadioButton rdo2;
    private javax.swing.JRadioButton rdo3;
    public javax.swing.JLabel refresh;
    public javax.swing.JLabel refresh1;
    private javax.swing.JTextField slnhap;
    private javax.swing.JPanel task;
    private javax.swing.JTable tblctkh;
    private javax.swing.JTable tblctpn;
    private javax.swing.JTable tblcttk;
    private javax.swing.JTable tbldshd;
    private javax.swing.JTable tblkhachhang;
    private javax.swing.JTable tblkm;
    private javax.swing.JTable tblphieunhap;
    public static javax.swing.JTable tblqlcv;
    private javax.swing.JTable tblqlnv;
    public static javax.swing.JTable tblqlsp;
    private javax.swing.JTable tbltaikhoan;
    private javax.swing.JTable tblthongke;
    private javax.swing.JTable tbltksp;
    private javax.swing.JPanel thongke;
    private javax.swing.JPanel ttcn;
    private javax.swing.JTextField ttdndiachi;
    private javax.swing.JTextField ttdnemail;
    private javax.swing.JTextField ttdnhoten;
    private javax.swing.JTextField ttdnmanv;
    private javax.swing.JTextField ttdnngaysinh;
    private javax.swing.JTextField ttdnsdt;
    private javax.swing.JTextField txtMaCT;
    private javax.swing.JTextField txtMaNV4;
    private javax.swing.JTextField txtMatKhau4;
    private com.toedter.calendar.JDateChooser txtNgayBD;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtPhanTram;
    private javax.swing.JTextField txtTaiKhoan4;
    private javax.swing.JTextField txtTenCT;
    private javax.swing.JComboBox<String> txtTrangThai4;
    private javax.swing.JTextField txtsdtkh;
    private javax.swing.JTextField txttenkh;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txttimkiem1;
    // End of variables declaration//GEN-END:variables
}
