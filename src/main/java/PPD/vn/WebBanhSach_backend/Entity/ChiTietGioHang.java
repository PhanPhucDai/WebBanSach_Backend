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
    @Column(name = "isSelected")
    private int isSelected;
    @ManyToOne(
            fetch =  FetchType.EAGER
    )
    private GioHang gioHang;
    @ManyToOne(
            fetch =  FetchType.EAGER
    )
    private Sach sach;
}
