package PPD.vn.WebBanhSach_backend.Rest;

import PPD.vn.WebBanhSach_backend.Entity.DiaChiGiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="dia-chi-giao-hang")
public interface DiaChiGiaoHangRepository extends JpaRepository<DiaChiGiaoHang, Integer> {
}
