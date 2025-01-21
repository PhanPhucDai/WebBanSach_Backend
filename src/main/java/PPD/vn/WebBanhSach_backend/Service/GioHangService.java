package PPD.vn.WebBanhSach_backend.Service;

import PPD.vn.WebBanhSach_backend.DTO.ChiTietGioHangDTO;
import PPD.vn.WebBanhSach_backend.Entity.ChiTietGioHang;
import PPD.vn.WebBanhSach_backend.Entity.GioHang;
import PPD.vn.WebBanhSach_backend.Entity.Sach;
import PPD.vn.WebBanhSach_backend.Rest.ChiTietGioHangRepository;
import PPD.vn.WebBanhSach_backend.Rest.GioHangRespository;
import PPD.vn.WebBanhSach_backend.Rest.SachRespository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GioHangService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ChiTietGioHangRepository chiTietGioHangRepository;
    @Autowired
    private SachRespository sachRespository;
    @Autowired
    private GioHangRespository gioHangRespository;
    @Transactional
    public int addItemInCart(ChiTietGioHangDTO chiTietGioHang){
        int rs=0;
        System.out.println("ma sach"+chiTietGioHang.getSach());

        Optional<ChiTietGioHang> chiTietGioHangOptional =chiTietGioHangRepository.findById(chiTietGioHang.getMaChiTietGioHang());
        ChiTietGioHang  newChiTietGioHang = null;

        if(chiTietGioHangOptional.isPresent()){
            newChiTietGioHang=chiTietGioHangOptional.get();
        }

        Optional<Sach> sachOptional= sachRespository.findById(chiTietGioHang.getSach() );
         if(sachOptional.isPresent()){
             Sach sach = sachOptional.get();
             int soLuongchiTietGioHang = chiTietGioHang.getSoluong();
             int soLuong = sach.getSoLuong();
             if(chiTietGioHang.getSoluong() < soLuong){
                 newChiTietGioHang.setSoluong(soLuongchiTietGioHang+1);
                 chiTietGioHangRepository.save(newChiTietGioHang);
                 rs=1;
             }
         }
        return rs;
    }
    @Transactional
    public int removeItemInCart(ChiTietGioHangDTO chiTietGioHang){
        int rs=0;
        Optional<ChiTietGioHang> chiTietGioHangOptional =chiTietGioHangRepository.findById(chiTietGioHang.getMaChiTietGioHang());
        ChiTietGioHang  newChiTietGioHang = null;
        if(chiTietGioHangOptional.isPresent()){
            newChiTietGioHang=chiTietGioHangOptional.get();
        }
        Optional<Sach> sachOptional= sachRespository.findById(chiTietGioHang.getSach() );
        if(sachOptional.isPresent()){
            Sach sach = sachOptional.get();
            int soLuongchiTietGioHang = chiTietGioHang.getSoluong();
            int soLuong = sach.getSoLuong();
            if(chiTietGioHang.getSoluong() > 0){
                newChiTietGioHang.setSoluong(soLuongchiTietGioHang-1);
                chiTietGioHangRepository.save(newChiTietGioHang);
                rs=1;
            }
        }
        return rs;
    }
    @Transactional
    public int xoaSanPhamGioHang(ChiTietGioHang chiTietGioHang) {
        try {
            boolean exists = chiTietGioHangRepository.existsById(chiTietGioHang.getMaChiTietGioHang());
            if (!exists) {
                 return 0;
            }
            chiTietGioHangRepository.deleteById(chiTietGioHang.getMaChiTietGioHang());
             return 1;
    } catch (Exception exception) {
             exception.printStackTrace();
            return 0;
        }
    }
    @Transactional
    public int themSanPhamGioHang(ChiTietGioHang chiTietGioHang) {
        try {
             GioHang gioHang = gioHangRespository.findById(chiTietGioHang.getGioHang().getMaGioHang())
                    .orElseThrow(() -> new RuntimeException("Không thể tìm thấy giỏ hàng"));
            Sach sach = sachRespository.findById(chiTietGioHang.getSach().getMaSach())
                    .orElseThrow(() -> new RuntimeException("Không thể tìm thấy sách"));
             Optional<ChiTietGioHang> existingItem = chiTietGioHangRepository.findByGioHangAndSach(gioHang, sach);

            if (existingItem.isPresent()) {
                 ChiTietGioHang item = existingItem.get();
                item.setSoluong(item.getSoluong() + chiTietGioHang.getSoluong());
                chiTietGioHangRepository.save(item);
            } else {
                 chiTietGioHangRepository.save(chiTietGioHang);
            }

            return 1; // Thành công
        } catch (Exception exception){
            return 0; // Lỗi
        }
    }


}
