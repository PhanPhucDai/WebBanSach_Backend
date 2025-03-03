package PPD.vn.WebBanhSach_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DiaChiGiaoHangDTO {
     private String tenTinh;
     private String tenHuyen;
     private String tenXa;
     private String chiTietDiachi;
     private String isSelected ;
     private int maNguoiDung ;
}
