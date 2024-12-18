package PPD.vn.WebBanhSach_backend.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp  implements EmailService{
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailServiceImp(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMessage(String from, String to, String subject, String text) {
        //MineMailMessage => có đính kèm media
        //SimpleMailMessage => nội dung thông thường
        MimeMessage message=javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true, "UTF-8");
            helper.setFrom(from);
            helper.setTo((to));
            helper.setSubject(subject);
            helper.setText(text, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(message);
    }
}
