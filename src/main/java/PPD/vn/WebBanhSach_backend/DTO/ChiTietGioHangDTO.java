package PPD.vn.WebBanhSach_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class ChiTietGioHangDTO {
    private int maChiTietGioHang;
    private int soluong;
    private int gioHang;
    private int sach;

    public ChiTietGioHangDTO(int maChiTietGioHang, int sach, int gioHang, int soluong) {
        this.maChiTietGioHang = maChiTietGioHang;
        this.sach = sach;
        this.gioHang = gioHang;
        this.soluong = soluong;
    }
}
