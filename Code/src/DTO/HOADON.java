/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author MINH TUAN
 */
public class HOADON {
    String mahd,manv,ngaytao,makm,makh,TrangThai;
    Double tongcong,tongtientra,thongke;
    int phantramkm;
    
    public String getMaKH() {
        return makh;
    }

    public void setMaKH(String makh) {
        this.makh = makh;
    }
    
    

    public Double getThongke() {
        return thongke;
    }

    public String getMaKM() {
        return makm;
    }

    public void setMaKM(String makm) {
        this.makm = makm;
    }

    public void setThongke(Double thongke) {
        this.thongke = thongke;
    }
    
    public HOADON(){}

   

    public String getMaHD() {
        return mahd;
    }

    public void setMaHD(String mahd) {
        this.mahd = mahd;
    }

    public String getMaNV() {
        return manv;
    }

    public void setMaNV(String manv) {
        this.manv = manv;
    }

    public String getNgayTao() {
        return ngaytao;
    }

    public void setNgayTao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Double getTongCong() {
        return tongcong;
    }

    public void setTongCong(Double tongcong) {
        this.tongcong = tongcong;
    }
    
    public Double getTongTienTra() {
        return tongtientra;
    }

    public void setTongTienTra(Double tongtientra) {
        this.tongtientra = tongtientra;
    }
    
    public int getPhanTramKM() {
        return phantramkm;
    }

    public void setPhanTramKM(int phantramkm) {
        this.phantramkm = phantramkm;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
    
}
