package PPD.vn.WebBanhSach_backend.Controller;

import PPD.vn.WebBanhSach_backend.Entity.NguoiDung;
import PPD.vn.WebBanhSach_backend.Security.JwtResponse;
import PPD.vn.WebBanhSach_backend.Security.LoginRequest;
import PPD.vn.WebBanhSach_backend.Service.JWTService;
import PPD.vn.WebBanhSach_backend.Service.NguoiDungService;
import PPD.vn.WebBanhSach_backend.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
@CrossOrigin("*")
@RequestMapping("tai-khoan")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/dang-ki")
    public ResponseEntity<?> dangKiTaiKhoan(@Validated @RequestBody  NguoiDung nguoiDung){
        System.out.println("email");
        ResponseEntity<?> response = taiKhoanService.dangKiNguoiDung(nguoiDung);
        return  response;
    }

    @GetMapping("/kich-hoat")
    public ResponseEntity<?> kichHoatTaiKHoan(@RequestParam  String email, @RequestParam String maKichHoat){
        System.out.println("email"+email);
        System.out.println("maKichHoat"+maKichHoat);
        ResponseEntity<?> response = taiKhoanService.kichHoatTaiKhoan(email, maKichHoat);
        return  response;
    }

    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
                    );
            if(authentication.isAuthenticated()){
                final String jwt= jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không chính xác.");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công.");
    }
}
