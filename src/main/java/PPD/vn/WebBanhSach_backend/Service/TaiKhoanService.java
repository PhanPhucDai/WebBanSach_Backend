package PPD.vn.WebBanhSach_backend.Service;


import PPD.vn.WebBanhSach_backend.Entity.GioHang;
import PPD.vn.WebBanhSach_backend.Entity.NguoiDung;
import PPD.vn.WebBanhSach_backend.Entity.ThongBaoLoi;
import PPD.vn.WebBanhSach_backend.Rest.GioHangRespository;
import PPD.vn.WebBanhSach_backend.Rest.NguoiDungRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaiKhoanService {
    @Autowired
    private NguoiDungRespository nguoiDungRespository;
    @Autowired
    private EmailServiceImp emailServiceImp;
    @Autowired
    private GioHangRespository gioHangRespository;


    public ResponseEntity<?> dangKiNguoiDung(NguoiDung nguoiDung){
        if(nguoiDungRespository.existsByTenDangNhap(nguoiDung.getTenDangNhap())){
            return ResponseEntity.badRequest().body(new ThongBaoLoi("Tên đăng nhập đã tòn tại"));
        }
        //kiểm tra email tồn tại
        if(nguoiDungRespository.existsByEmail(nguoiDung.getEmail())){
            return ResponseEntity.badRequest().body(new ThongBaoLoi("Email đã tòn tại"));
        }
        //ma hoa mat khau
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String enscryptPassword = passwordEncoder.encode(nguoiDung.getMatKhau());
        nguoiDung.setMatKhau(enscryptPassword);
        //Gán và gửi thông tin kích hoạt
        nguoiDung.setMaKichHoat(taoMaKichHoat());
        nguoiDung.setIsKichHoat(false);
        //lưu người dùng vào cơ sở dũ liệu
        NguoiDung nguoiDung_daDangKi = nguoiDungRespository.save(nguoiDung);
        GioHang gioHang = new GioHang();
        gioHang.setNguoiDung(nguoiDung_daDangKi);
        gioHangRespository.save(gioHang);
        //Gửi email cho người dùng kích hoạt
        guiEmailKichHoat(nguoiDung.getEmail(), nguoiDung.getMaKichHoat());
        return ResponseEntity.ok("Đăng kí thành công");
    }

    public ResponseEntity<?> kichHoatTaiKhoan(String  emai, String maKichHoat){
        NguoiDung nguoiDung= nguoiDungRespository.findByEmail(emai);
        if(nguoiDung == null){
            return ResponseEntity.badRequest().body(new ThongBaoLoi("Nguoi dùng không tồn tại"));
        }
        if(nguoiDung.getIsKichHoat()){
            return ResponseEntity.badRequest().body(new ThongBaoLoi("Tài khoản đã đuwocj kích hoạt"));
        }
        System.out.println("nguoi dung"+ nguoiDung.getTen());
        if(maKichHoat.equals(maKichHoat)){
            nguoiDung.setIsKichHoat(true);
            nguoiDungRespository.save(nguoiDung);
            return ResponseEntity.ok().body("Đã kích hoạt thành công");
        }else{
            return ResponseEntity.badRequest().body("kích hoạt tài khoản thành công");
        }
    }

    private String taoMaKichHoat(){
        //tạo mã ngẫu nhiên
        return UUID.randomUUID().toString();
    }

    private void guiEmailKichHoat(String email, String maKichHoat){
        String subject="Kích hoạt tài khoản của bạn tại WEB Bán sách";
        String text = " Vui lòng sử dụng mã sau để kích hoạt cho tài khoản <"
                +email+">: </br><h1>"+maKichHoat+"</h1>";
                text+="</br>Click vào đường link để kích hoạt";
                String url = "http://localhost:3000/kich-hoat/"+email+"/"+maKichHoat;
                text+="</br><a href="+url+">"+url+"</a>";
        emailServiceImp.sendMessage("dai582005@gmail.com", email, subject, text);
    }
}
