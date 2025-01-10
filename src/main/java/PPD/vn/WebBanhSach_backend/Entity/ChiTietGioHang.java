package PPD.vn.WebBanhSach_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "chi_tiet_gio_hang")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietGioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ma_chi_tiet_gio_hang")
    private int maChiTietGioHang;
    @Column(name = "so_luong")
    private int soluong;
    @Column(name = "gia_san_pham")
    private double giaSanPham;
    @Column(name = "tong_gia_san_pham")
    private double tongGiaSanPham;


    @ManyToOne(
            fetch =  FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private GioHang gioHang;
    @ManyToOne(
            fetch =  FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Sach sach;
}
