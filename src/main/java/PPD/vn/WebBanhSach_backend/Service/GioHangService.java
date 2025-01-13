package PPD.vn.WebBanhSach_backend.Service;

import PPD.vn.WebBanhSach_backend.DTO.ChiTietGioHangDTo;
import PPD.vn.WebBanhSach_backend.Entity.ChiTietGioHang;
import PPD.vn.WebBanhSach_backend.Entity.Sach;
import PPD.vn.WebBanhSach_backend.Rest.ChiTietGioHangRepository;
import PPD.vn.WebBanhSach_backend.Rest.SachRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GioHangService {
    @Autowired
    private ChiTietGioHangRepository chiTietGioHangRepository;
    @Autowired
    private SachRespository sachRespository;

    public int addItemInCart(ChiTietGioHangDTo chiTietGioHang){
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

    public int removeItemInCart(ChiTietGioHangDTo chiTietGioHang){
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
            if(chiTietGioHang.getSoluong() > 0){
                newChiTietGioHang.setSoluong(soLuongchiTietGioHang-1);
                chiTietGioHangRepository.save(newChiTietGioHang);
                rs=1;
            }
        }
        return rs;
    }
}
