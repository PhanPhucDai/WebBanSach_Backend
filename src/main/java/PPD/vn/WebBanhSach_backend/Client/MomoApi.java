package PPD.vn.WebBanhSach_backend.Client;

import PPD.vn.WebBanhSach_backend.Model.CreateMoMoRequest;
import PPD.vn.WebBanhSach_backend.Model.CreateModelRepone;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "MoMo", url = "${momo.end-point}")
public interface MomoApi {
    @PostMapping("/create")
    CreateModelRepone createMomoQR(@RequestBody CreateMoMoRequest createMoMoRequest);
}
