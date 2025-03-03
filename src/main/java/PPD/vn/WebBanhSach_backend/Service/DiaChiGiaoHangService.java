package PPD.vn.WebBanhSach_backend.Service;

import PPD.vn.WebBanhSach_backend.DTO.DiaChiGiaoHangDTO;
import PPD.vn.WebBanhSach_backend.Entity.DiaChiGiaoHang;
import PPD.vn.WebBanhSach_backend.Entity.NguoiDung;
import PPD.vn.WebBanhSach_backend.Rest.DiaChiGiaoHangRepository;
import PPD.vn.WebBanhSach_backend.Rest.NguoiDungRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaChiGiaoHangService {
    @Autowired
    private NguoiDungRespository nguoiDungRespository;

    @Autowired
    private DiaChiGiaoHangRepository diaChiGiaoHangRepository;
    public int luuDiaChi(DiaChiGiaoHang diaChiGiaoHang){
        diaChiGiaoHangRepository.save(diaChiGiaoHang);
        return 1;
    }

    public int updateDiaChi(DiaChiGiaoHang diaChiGiaoHangCu){
        try {
            Optional<DiaChiGiaoHang> diaChiGiaoHang = diaChiGiaoHangRepository.findById(diaChiGiaoHangCu.getMaDiaChiGiaoHang());
            DiaChiGiaoHang diaChiCurent = diaChiGiaoHang.orElseThrow(()-> new RuntimeException("Không tìm thấy địa chỉ giao hàng"));

            diaChiCurent.setChiTietDiachi(diaChiGiaoHangCu.getChiTietDiachi());
            diaChiCurent.setTenTinh(diaChiGiaoHangCu.getTenTinh());
            diaChiCurent.setTenHuyen(diaChiGiaoHangCu.getTenHuyen());
            diaChiCurent.setTenXa(diaChiGiaoHangCu.getTenXa());

            if(diaChiGiaoHangCu.getIsSelected().equals("T")) {
                List<DiaChiGiaoHang> diaChiGiaoHangs = diaChiGiaoHangRepository.findAll();
                for (DiaChiGiaoHang diaChi : diaChiGiaoHangs) {
                    diaChi.setIsSelected("F");
                    diaChiGiaoHangRepository.save(diaChi);
                }
                diaChiCurent.setIsSelected(diaChiGiaoHangCu.getIsSelected());
            }
            diaChiGiaoHangRepository.save(diaChiCurent);
        } catch (RuntimeException e) {
            return 0;
        }
        return 1;
    }

    public int saveDiaChi(DiaChiGiaoHangDTO diaChiGiaoHang){
        if(diaChiGiaoHang.getTenTinh().isEmpty()
                || diaChiGiaoHang.getTenHuyen().isEmpty()
                ||diaChiGiaoHang.getTenXa().isEmpty()
                ||diaChiGiaoHang.getChiTietDiachi().isEmpty()
                ||diaChiGiaoHang.getIsSelected().isEmpty()
                ){
            System.out.print(diaChiGiaoHang.getTenTinh());
            System.out.print(diaChiGiaoHang.getTenXa());
            System.out.print(diaChiGiaoHang.getTenHuyen());

            return 0;
        }
        Optional<NguoiDung> nguoiDung = nguoiDungRespository.findById(diaChiGiaoHang.getMaNguoiDung());

        DiaChiGiaoHang diaChiGiaoHang1 = new DiaChiGiaoHang(diaChiGiaoHang.getTenTinh(),
                diaChiGiaoHang.getTenHuyen()
                ,diaChiGiaoHang.getTenXa()
                ,diaChiGiaoHang.getChiTietDiachi()
                ,diaChiGiaoHang.getIsSelected()
                ,nguoiDung.get());
        diaChiGiaoHangRepository.save(diaChiGiaoHang1);
        return 1;
    }

    public int deleteDiaChi(DiaChiGiaoHang diaChiGiaoHang){
        Optional<DiaChiGiaoHang> diaChiGiaoHang1 = diaChiGiaoHangRepository.findById(diaChiGiaoHang.getMaDiaChiGiaoHang());
        if(!diaChiGiaoHang1.isPresent()){
            return 0;
        }
        diaChiGiaoHangRepository.delete(diaChiGiaoHang);
        return 1;
    }
}
