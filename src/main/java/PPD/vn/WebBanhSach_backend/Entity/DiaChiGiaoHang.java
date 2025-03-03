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
    private String isSelected = "F";
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private NguoiDung nguoiDung;

    public DiaChiGiaoHang(String tenTinh, String tenHuyen, String tenXa, String chiTietDiachi, String isSelected, NguoiDung nguoiDung) {
        this.tenTinh = tenTinh;
        this.tenHuyen = tenHuyen;
        this.tenXa = tenXa;
        this.chiTietDiachi = chiTietDiachi;
        this.isSelected = isSelected;
        this.nguoiDung = nguoiDung;
    }
}
