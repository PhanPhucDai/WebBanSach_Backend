package PPD.vn.WebBanhSach_backend.Rest;


import PPD.vn.WebBanhSach_backend.Entity.ChiTietGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "chi-tiet-gio-hang")
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Integer> {
}
