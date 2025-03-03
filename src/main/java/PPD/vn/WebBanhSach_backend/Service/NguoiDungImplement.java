package PPD.vn.WebBanhSach_backend.Service;

import PPD.vn.WebBanhSach_backend.Entity.DiaChiGiaoHang;
import PPD.vn.WebBanhSach_backend.Entity.NguoiDung;
import PPD.vn.WebBanhSach_backend.Entity.Quyen;
import PPD.vn.WebBanhSach_backend.Rest.DiaChiGiaoHangRepository;
import PPD.vn.WebBanhSach_backend.Rest.NguoiDungRespository;
import PPD.vn.WebBanhSach_backend.Rest.QuyenRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
 import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NguoiDungImplement implements NguoiDungService{
    @Autowired
    private NguoiDungRespository nguoiDungRespository;
    @Autowired
    private QuyenRespository quyenRespository;
    @Autowired
    private DiaChiGiaoHangRepository diaChiGiaoHangRepository;

    @Autowired
    public NguoiDungImplement(NguoiDungRespository nguoiDungRespository, QuyenRespository quyenRespository) {
        this.nguoiDungRespository = nguoiDungRespository;
        this.quyenRespository = quyenRespository;
    }

    @Override
    public NguoiDung findByUsername(String tenDangNhap) {
        return nguoiDungRespository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRespository.findByTenDangNhap(username);
        if(nguoiDung==null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }
        User user = new User(nguoiDung.getTenDangNhap(), nguoiDung.getMatKhau(), rolesToAuthorities(nguoiDung.getDanhSachQuyen()) );
        return user;
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Quyen> quyens ) {
        return quyens.stream().map(quyen->new SimpleGrantedAuthority(quyen.getTenQuyen())).collect(Collectors.toList());

    }

    public int doiMatkhau(int id, String matKhauMoi, String matKhauCu){
        try {
            //lấy ngươi dùng hiện tại
            Optional<NguoiDung> nguoiDung = nguoiDungRespository.findById(id);
            nguoiDung.orElseThrow(()-> new RuntimeException("Không tìm thấy người dùng"));

          BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(!passwordEncoder.matches(matKhauCu.trim(),nguoiDung.get().getMatKhau())){

                return -1;}
            //mã hóa mật khẩu mới
             String enscryptPassword = passwordEncoder.encode(matKhauMoi);
             // lưu mật khẩu
            nguoiDung.get().setMatKhau(enscryptPassword);
            nguoiDungRespository.save(nguoiDung.get());
        } catch (Exception e) {
             return 0;
         }
        return 1;
    }




}
