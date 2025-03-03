package PPD.vn.WebBanhSach_backend.Controller;

import PPD.vn.WebBanhSach_backend.Model.CreateMoMoRequest;
import PPD.vn.WebBanhSach_backend.Model.CreateModelRepone;
import PPD.vn.WebBanhSach_backend.Service.MoMoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MomoControlle {
    @Autowired
    private MoMoService moMoService;
}
