package PPD.vn.WebBanhSach_backend.Controller;

import PPD.vn.WebBanhSach_backend.Entity.Sach;
import PPD.vn.WebBanhSach_backend.Service.SachServide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sach")
public class SachController {
    @Autowired
    private SachServide sachServide;

    @PostMapping("/them-sach")
    public ResponseEntity<?> themSach(@Validated @RequestBody Sach sach){
        if(!sachServide.themSach(sach)){
            return ResponseEntity.badRequest().body("Không thể thêm Sách mới");
        }
        return ResponseEntity.ok().body("Đã thêm Sách mới");
    }
}
