package PPD.vn.WebBanhSach_backend.Security;

public class Endpoints {
    public static final String frontEnd_host = "http://localhost:3000";

    public  static  final String[] PUBLIC_GET_ENPOINT= {
            "/sach"
            ,"/gio-hang/them-so-luong"
            ,"/create"
            ,"/the-loai/**"
            ,"/"
            ,"/xoa-dia-chi"
            ,"/save-dia-chi",
            "/update-password",
            "/update-dia-chi",
            "/update-user"
            ,"/sach/**"
            ,"/chi-tiet-gio-hang"
            ,"/chi-tiet-gio-hang/**"
            ,"/hinh-anh/**"
            ,"/nguoi-dung/**"
            ,"/nguoi-dung/search/existsByTenDangNhap"
            ,"/nguoi-dung/search/existsByEmail"
            ,"/tai-khoan/kich-hoat"
            ,"/gio-hang/**"

    };

    public  static  final String[] PUBLIC_POST_ENPOINT_DangKi= {
            "/tai-khoan/dang-ki",
            "/tai-khoan/dang-nhap"
            ,"/sach/them-sach"
            ,"/gio-hang/**"
    };

    public  static  final String[] ADMIN_POST_ENPOINT_DangKi= {
            "/sach"
    };

    public  static  final String[] User_POST_ENPOINT_DangKi= {


    };
}
