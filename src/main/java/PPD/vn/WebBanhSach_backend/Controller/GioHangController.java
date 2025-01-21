package PPD.vn.WebBanhSach_backend.Controller;

import PPD.vn.WebBanhSach_backend.DTO.ChiTietGioHangDTO;
import PPD.vn.WebBanhSach_backend.Entity.ChiTietGioHang;
import PPD.vn.WebBanhSach_backend.Entity.GioHang;
import PPD.vn.WebBanhSach_backend.Entity.NguoiDung;
import PPD.vn.WebBanhSach_backend.Rest.ChiTietGioHangRepository;
import PPD.vn.WebBanhSach_backend.Rest.GioHangRespository;
import PPD.vn.WebBanhSach_backend.Rest.NguoiDungRespository;
import PPD.vn.WebBanhSach_backend.Service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/gio-hang")
public class GioHangController {
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private ChiTietGioHangRepository chiTietGioHangRepository;
    @Autowired
    private NguoiDungRespository nguoiDungRespository;
    @Autowired
    private GioHangRespository gioHangRespository;

    @PutMapping("/them-so-luong")
    public ResponseEntity<?> themSoLuong(@Validated @RequestBody ChiTietGioHangDTO chiTietGioHang){
        if(gioHangService.addItemInCart(chiTietGioHang)==1){
           return ResponseEntity.ok().body("");
       }
        return ResponseEntity.badRequest().body("Số lượng không đủ để đáp ứng");
    }

    @PutMapping("/xoa-so-luong")
    public ResponseEntity<?> xoaSoLuong(@Validated @RequestBody ChiTietGioHangDTO chiTietGioHang){
        if(gioHangService.removeItemInCart(chiTietGioHang)==1){
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.badRequest().body("Số lượng đã đạt giới hạn");
    }
    @DeleteMapping("/xoa-so-luong")
    public ResponseEntity<?> xoaChiTietGioHang(@Validated @RequestBody ChiTietGioHangDTO chiTietGioHang){
        System.out.println("ma sach"+chiTietGioHang);
        if(gioHangService.removeItemInCart(chiTietGioHang)==1){
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.badRequest().body("Số lượng đã đạt giới hạn");
    }

    @DeleteMapping("/xoa-san-pham-gio-hang")
    public ResponseEntity<?> xoaSanPhamGioHang(@Validated @RequestBody ChiTietGioHangDTO chiTietGioHangDTO){
        System.out.println("Chi tiết giỏ hàng nhận được: " + chiTietGioHangDTO);
        Optional<ChiTietGioHang> chiTietGioHang = chiTietGioHangRepository.findById(chiTietGioHangDTO.getMaChiTietGioHang());
        if(chiTietGioHang.isPresent()){
            if( gioHangService.xoaSanPhamGioHang(chiTietGioHang.get())==1){
                System.out.println("Đã xóa thành công");
                return ResponseEntity.ok().body("Đã xóa thành công");
            }
        }
        System.out.println("Không thể xóa sản phẩm này");
         return ResponseEntity.badRequest().body("Không thể xóa sản phẩm này");
    }

    @PostMapping("/them-san-pham-gio-hang")
    public ResponseEntity<?> themSanPhamGioHang(@Validated @RequestBody ChiTietGioHang chiTietGioHang){
        if(gioHangService.themSanPhamGioHang(chiTietGioHang)==1){
            return ResponseEntity.ok().body("Đã thêm thành công");
        }
        return ResponseEntity.badRequest().body("Không thể thêm sản phẩm này");
    }

    @PostMapping("/San-pham-gio-hang")
    public ResponseEntity<?> themSanPhamGioHang(@Validated @RequestBody Map<String, Integer> data){
        int maNguoiDung = data.get("maNguoiDung");
        Optional<NguoiDung> nguoiDung= nguoiDungRespository.findById(maNguoiDung);
        GioHang gioHang = nguoiDung.get().getGioHang();
        List<ChiTietGioHang> chiTietGioHang = chiTietGioHangRepository.findByGioHang(gioHang);
        try {
            List<ChiTietGioHangDTO> listChiTietGioHangDTO = new ArrayList<>();
            for(ChiTietGioHang chiTietGioHang1: chiTietGioHang) {
                ChiTietGioHangDTO chiTietGioHangDTO
                        = new ChiTietGioHangDTO(
                        chiTietGioHang1.getMaChiTietGioHang()
                        , chiTietGioHang1.getSach().getMaSach()
                        , chiTietGioHang1.getGioHang().getMaGioHang()
                        , chiTietGioHang1.getSoluong());
                listChiTietGioHangDTO.add(chiTietGioHangDTO);

            }

            return ResponseEntity.ok(listChiTietGioHangDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Không thể thêm sản phẩm này");
    }
}
