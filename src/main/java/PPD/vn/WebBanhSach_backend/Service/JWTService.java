package PPD.vn.WebBanhSach_backend.Service;


import PPD.vn.WebBanhSach_backend.Entity.NguoiDung;
import PPD.vn.WebBanhSach_backend.Entity.Quyen;
import PPD.vn.WebBanhSach_backend.Rest.NguoiDungRespository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

    public static final String select="938IHH2HWE0FFH08EWCB8WCWEH8T2E9VSVN00384GBP9Q38VBKFDR0BVEV8E989V85BLSBG485GRG0ABF23REF4W6345324TEWAF3F ";
    @Autowired
    private NguoiDungRespository nguoiDungService;
    //tao JWT dựa trên tên đăng nhập
    public String generateToken(String tenDangNhap) {
        Map<String, Object> claims = new HashMap<>();
        NguoiDung nguoiDung = nguoiDungService.findByTenDangNhap(tenDangNhap);
        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isUser = false;

        if(nguoiDung != null  && nguoiDung.getDanhSachQuyen().size()>0){
           List<Quyen> list = nguoiDung.getDanhSachQuyen();
           for(Quyen q : list){
               if(q.getTenQuyen().equals("Admin")){
                   isAdmin = true;
               }
               if(q.getTenQuyen().equals("Staff")){
                   isStaff = true;
               }
               if(q.getTenQuyen().equals("User")){

                   isUser = true;
               }
           }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        claims.put("isUser", isUser);
        claims.put("nameUser", nguoiDung.getTen());
        claims.put("idUser", nguoiDung.getMaNguoiDung());

        return createToken(claims, tenDangNhap);
    }

    //tạo JWT với các claim đã chọn
    private String createToken(Map<String, Object> claims, String tenDangnhap) {
        return Jwts.builder()
                .setClaims(claims) // Đảm bảo đây là Map<String, Object>
                .setSubject(tenDangnhap) // Tên người dùng
                .setIssuedAt(new Date(System.currentTimeMillis())) // Thời gian phát hành
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // Thời gian hết hạn
                .signWith(SignatureAlgorithm.HS256, getSignKey()) // Khóa ký hợp lệ
                .compact();
    }

    //lay select key
    private Key getSignKey(){
        byte[] keyBytes= Decoders.BASE64.decode(select);
        //hmacShaKeyFor chuyeenr sang kieudu lieu key
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Trich xuat thong tin
    private Claims extracAllClaims(String token){
        return Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token).getBody();
    }

    //trich xuat thong tin cho 1 claim dac biet nao do
    public <T> T extractionClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims= extracAllClaims(token);
        return  claimsTFunction.apply(claims);
    }
    //kiem tra toi thoi gia het han
    public Date extracExpiration(String token){
        return  extractionClaim(token, Claims::getExpiration);
    }

    //kiem tra toi thoi gia het han
    public String extracUsername(String token){
        return  extractionClaim(token, Claims::getSubject);
    }

    //Kiem tra jwt da het han
    private Boolean isTokenExpired(String token) {
        return extracExpiration(token).before(new Date());
    }
    //kiem tra token có hop le hay khong
    public Boolean validateToken(String token, UserDetails  userDetails){
        final String tenDangNhap = extracUsername(token);
        return (tenDangNhap.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }


}
