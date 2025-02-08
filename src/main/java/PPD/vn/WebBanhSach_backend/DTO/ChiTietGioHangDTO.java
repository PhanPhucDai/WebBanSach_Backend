package PPD.vn.WebBanhSach_backend.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class ChiTietGioHangDTO {
    private int maChiTietGioHang;
    private int soluong;
    private int isSelected;
    private int gioHang;
    private int sach;

    public ChiTietGioHangDTO(int maChiTietGioHang, int sach, int gioHang, int soluong,int isSelected) {
        this.maChiTietGioHang = maChiTietGioHang;
        this.sach = sach;
        this.gioHang = gioHang;
        this.soluong = soluong;
        this.isSelected= isSelected;
    }
}
