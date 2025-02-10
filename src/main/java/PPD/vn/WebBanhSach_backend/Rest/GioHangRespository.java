package PPD.vn.WebBanhSach_backend.Rest;

import PPD.vn.WebBanhSach_backend.Entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "gio-hang")
public interface GioHangRespository extends JpaRepository<GioHang, Integer> {
 }

