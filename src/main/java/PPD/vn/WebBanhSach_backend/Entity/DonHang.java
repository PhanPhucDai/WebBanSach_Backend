package PPD.vn.WebBanhSach_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
@Entity
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_hang")
    private int maDonHang;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "dia_chi_mua_hang")
    private String diaChiMuaHang;
    @Column(name = "dia_chi_nhan_hang")
    private String diaChiNhanHang;
    @Column(name = "tong_tien_san_pham")
    private double tongTienSanPham;
    @OneToMany(mappedBy = "donHang", fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private List<ChiTietDonHang> danhSachChiTietDonHang;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "ma_nguoi_dung", nullable = false)
    private NguoiDung nguoiDung;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})

    @JoinColumn(name = "ma_hinh_thuc_thanh_toan", nullable = true)
    private HinhThucThanhToan hinhThucThanhToan;
    @ManyToOne(
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "ma_hinh_thuc_giao_hang", nullable = true)
    private HinhThucGiaoHang hinhThucGiaoHang ;

}
