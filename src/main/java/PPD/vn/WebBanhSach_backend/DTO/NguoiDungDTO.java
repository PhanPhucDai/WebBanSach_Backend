package PPD.vn.WebBanhSach_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NguoiDungDTO {
    private Integer idUser;
    private String email;
    private String ho;
    private String sdt;
    private String ten;
    private String gioiTinh;
    private String matKhauCu;
    private String matKhauMoi;
}
