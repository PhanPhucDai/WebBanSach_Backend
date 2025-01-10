package PPD.vn.WebBanhSach_backend.Service;

import PPD.vn.WebBanhSach_backend.Entity.HinhAnh;
import PPD.vn.WebBanhSach_backend.Entity.Sach;
import PPD.vn.WebBanhSach_backend.Rest.HinhAnhRespository;
import PPD.vn.WebBanhSach_backend.Rest.SachRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@Transactional
public class SachServide {
    @Autowired
    private SachRespository sachRespository;
    @Autowired
    private HinhAnhRespository hinhAnhRespository;


    public boolean themSach(Sach sach){
        try {
            sachRespository.save(sach);
            HinhAnh hinhAnh=new HinhAnh(sach.getTenSach(),true,"",sach,"data:image/webp;base64,"+hashPicture("D:\\Springboot_react\\WebBanSach_Backend\\src\\main\\java\\PPD\\vn\\WebBanhSach_backend\\img\\img.png"));
            hinhAnhRespository.save(hinhAnh);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String hashPicture(String path){
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);
            return base64Encoded;
        } catch (IOException  e) {
            throw new RuntimeException(e);
        }
    }
}
