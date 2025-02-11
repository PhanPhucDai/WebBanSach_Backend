package PPD.vn.WebBanhSach_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DiaChiGiaoHang")
public class DiaChiGiaoHang {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int maDiaChiGiaoHang;
    @Column(name = "tenTinh")
    private String tenTinh;
    @Column(name = "tenHuyen")
    private String tenHuyen;
    @Column(name = "tenXa")
    private String tenXa;
    @Column(name = "chiTietDiachi")
    private String chiTietDiachi;
    @Column(name = "isSelected")
    private String isSelected;
    @ManyToOne(
            fetch = FetchType.EAGER
    )
    private NguoiDung nguoiDung;

}
