package PPD.vn.WebBanhSach_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "chi_tiet_don_hang")
@Data
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chi_tiet_don_hang")
    private int maChiTietDonHang;
    @Column(name = "so_luong")
    private int soLuong ;
    @Column(name = "gia_ban")
    private double giaBan;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "ma_sach", nullable = true)
    private Sach sach;
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "ma_don_hang", nullable = true)
    private DonHang donHang;


}
