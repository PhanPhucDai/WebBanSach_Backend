package PPD.vn.WebBanhSach_backend.Security;

public class Endpoints {
    public static final String frontEnd_host = "http://localhost:3000";

    public  static  final String[] PUBLIC_GET_ENPOINT= {
            "/sach"
            ,"/sach/**"
            ,"/hinh-anh"
            ,"/hinh-anh/**"
            ,"/nguoi-dung"
            ,"/nguoi-dung/search/existsByTenDangNhap"
            ,"/nguoi-dung/search/existsByEmail"
            ,"/tai-khoan/kich-hoat"

    };

    public  static  final String[] PUBLIC_POST_ENPOINT_DangKi= {
            "/tai-khoan/dang-ki",
            "/tai-khoan/dang-nhap"
    };

    public  static  final String[] ADMIN_POST_ENPOINT_DangKi= {
            "/sach"
    };
}
