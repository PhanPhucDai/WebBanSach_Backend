package PPD.vn.WebBanhSach_backend.Controller;

import PPD.vn.WebBanhSach_backend.DTO.ChiTietGioHangDTo;
import PPD.vn.WebBanhSach_backend.Entity.ChiTietGioHang;
import PPD.vn.WebBanhSach_backend.Service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/gio-hang")
public class GioHangController {
    @Autowired
    private GioHangService gioHangService;

    @PutMapping("/them-so-luong")
    public ResponseEntity<?> themSoLuong(@Validated @RequestBody ChiTietGioHangDTo chiTietGioHang){
        System.out.println("ma sach"+chiTietGioHang);
       if(gioHangService.addItemInCart(chiTietGioHang)==1){
           return ResponseEntity.ok().body("");
       }
        return ResponseEntity.badRequest().body("Số lượng không đủ để đáp ứng");
    }

    @PutMapping("/xoa-so-luong")
    public ResponseEntity<?> xoaSoLuong(@Validated @RequestBody ChiTietGioHangDTo chiTietGioHang){
        System.out.println("ma sach"+chiTietGioHang);
        if(gioHangService.removeItemInCart(chiTietGioHang)==1){
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.badRequest().body("Số lượng đã đạt giới hạn");
    }
    @DeleteMapping("/xoa-so-luong")
    public ResponseEntity<?> xoaChiTietGioHang(@Validated @RequestBody ChiTietGioHangDTo chiTietGioHang){
        System.out.println("ma sach"+chiTietGioHang);
        if(gioHangService.removeItemInCart(chiTietGioHang)==1){
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.badRequest().body("Số lượng đã đạt giới hạn");
    }
}
