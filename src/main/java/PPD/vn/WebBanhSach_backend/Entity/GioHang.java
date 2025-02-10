package PPD.vn.WebBanhSach_backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ma_gio_hang")
    private int maGioHang;


    @OneToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "ma_nguoi_dung")
     private NguoiDung nguoiDung;

    @OneToMany(cascade = {CascadeType.MERGE},mappedBy = "gioHang", fetch =  FetchType.LAZY)
    private List<ChiTietGioHang> chiTietGioHang;


}
