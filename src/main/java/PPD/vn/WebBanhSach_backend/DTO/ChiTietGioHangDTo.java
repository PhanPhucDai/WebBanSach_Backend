package PPD.vn.WebBanhSach_backend.DTO;

import PPD.vn.WebBanhSach_backend.Entity.GioHang;
import PPD.vn.WebBanhSach_backend.Entity.Sach;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietGioHangDTo {
    private int maChiTietGioHang;
    private int soluong;
    private int gioHang;
    private int sach;
}
