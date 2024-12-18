package PPD.vn.WebBanhSach_backend.Service;

public interface EmailService {

    public void sendMessage(String from, String to, String subject, String text);
}
