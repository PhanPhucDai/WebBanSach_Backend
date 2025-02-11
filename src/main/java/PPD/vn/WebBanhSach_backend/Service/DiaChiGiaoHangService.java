package PPD.vn.WebBanhSach_backend.Service;

import PPD.vn.WebBanhSach_backend.Entity.DiaChiGiaoHang;
import PPD.vn.WebBanhSach_backend.Rest.DiaChiGiaoHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaChiGiaoHangService {
    @Autowired
    private DiaChiGiaoHangRepository diaChiGiaoHangRepository;
    public int luuDiaChi(DiaChiGiaoHang diaChiGiaoHang){
        diaChiGiaoHangRepository.save(diaChiGiaoHang);
        return 1;
    }
}
