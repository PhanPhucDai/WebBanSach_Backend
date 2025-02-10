package PPD.vn.WebBanhSach_backend.Rest;


import PPD.vn.WebBanhSach_backend.Entity.ChiTietGioHang;
import PPD.vn.WebBanhSach_backend.Entity.GioHang;
import PPD.vn.WebBanhSach_backend.Entity.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "chi-tiet-gio-hang")
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer> {
    Optional<ChiTietGioHang> findByGioHangAndSach(GioHang gioHang, Sach sach);
    List<ChiTietGioHang> findByGioHang(GioHang gioHang);
    List<ChiTietGioHang> findByGioHangAndIsSelected(GioHang gioHang, int isSelected);


}
