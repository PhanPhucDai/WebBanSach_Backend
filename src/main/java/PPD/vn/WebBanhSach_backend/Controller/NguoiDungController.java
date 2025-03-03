package PPD.vn.WebBanhSach_backend.Controller;

import PPD.vn.WebBanhSach_backend.DTO.DiaChiGiaoHangDTO;
import PPD.vn.WebBanhSach_backend.DTO.NguoiDungDTO;
import PPD.vn.WebBanhSach_backend.Entity.DiaChiGiaoHang;
import PPD.vn.WebBanhSach_backend.Entity.NguoiDung;
import PPD.vn.WebBanhSach_backend.Rest.DiaChiGiaoHangRepository;
import PPD.vn.WebBanhSach_backend.Rest.NguoiDungRespository;
import PPD.vn.WebBanhSach_backend.Service.DiaChiGiaoHangService;
import PPD.vn.WebBanhSach_backend.Service.NguoiDungImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class NguoiDungController {
    @Autowired
    private NguoiDungRespository nguoiDungRespository;

    @Autowired
    private NguoiDungImplement nguoiDungImplement;

    @Autowired
    private DiaChiGiaoHangRepository diaChiGiaoHangRepository;

    @Autowired
    private DiaChiGiaoHangService diaChiGiaoHangService;

    //Cập nhật người dùng
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody NguoiDungDTO nguoiDungDTO){
        try {
            Optional<NguoiDung> nguoiDung = nguoiDungRespository.findById(nguoiDungDTO.getIdUser());
            nguoiDung.orElseThrow(() -> new RuntimeException("Người dùng không tìm thấy"));

            nguoiDung.get().setEmail(nguoiDungDTO.getEmail());
            nguoiDung.get().setSoDienThoai(nguoiDungDTO.getSdt());
            nguoiDung.get().setHoDem(nguoiDungDTO.getHo());
            nguoiDung.get().setTen(nguoiDungDTO.getTen());
            nguoiDung.get().setGioiTinh(nguoiDungDTO.getGioiTinh());
            nguoiDungRespository.save(nguoiDung.get());
        } catch (RuntimeException e) {
             return ResponseEntity.badRequest().body("Đã chỉnh sữa không thành công"+e.getMessage());
        }
        return ResponseEntity.ok().body("Đã chỉnh sữa thành công");
    }

    //Đổi mật khẩu
    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody NguoiDungDTO nguoiDungDTO){
        int result=nguoiDungImplement.doiMatkhau(nguoiDungDTO.getIdUser() , nguoiDungDTO.getMatKhauMoi() , nguoiDungDTO.getMatKhauCu());

        if(result == 0){
           return ResponseEntity.badRequest().body("Đã chỉnh đổi mật thành công");
      }else if(result == -1){
          return ResponseEntity.badRequest().body("Mật khẩu cũ không đúng");
      }
         return ResponseEntity.ok().body("Đã chỉnh đổi mật thành công");
    }

    //Cập nhật địa chỉ
    @PutMapping("/update-dia-chi")
    public ResponseEntity<?> updateAddress(@RequestBody DiaChiGiaoHang diaChiGiaoHang){
        Optional<DiaChiGiaoHang> diaChiGiaoHang1 = diaChiGiaoHangRepository.findById(diaChiGiaoHang.getMaDiaChiGiaoHang());
        if(!diaChiGiaoHang1.isPresent()){
            return ResponseEntity.badRequest().body("Đã chỉnh địa chỉ thành công");
        }
        try {
            diaChiGiaoHangService.updateDiaChi(diaChiGiaoHang);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Đã chỉnh địa chỉ không thành công"+e.getMessage());
        }
        return ResponseEntity.ok().body("Đã chỉnh địa chỉ  thành công");
    }

    //Lưu địa chỉ
    @PostMapping("/save-dia-chi")
    public ResponseEntity<?> saveAddress(@RequestBody DiaChiGiaoHangDTO diaChiGiaoHang){
        try {

            diaChiGiaoHangService.saveDiaChi(diaChiGiaoHang);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Đã lưu không thành công" + e.getMessage());
        }
        return ResponseEntity.ok().body("Đã lưu thành công");
    }

    //Xóa địa chỉ
    @DeleteMapping("/xoa-dia-chi")
    public ResponseEntity<?> deleteAddress(@RequestBody DiaChiGiaoHang diaChiGiaoHang){
        try {
            diaChiGiaoHangService.deleteDiaChi(diaChiGiaoHang);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Đã Xóa không thành công" + e.getMessage());
        }
        return ResponseEntity.ok().body("Đã xóa thành công");
    }
}
