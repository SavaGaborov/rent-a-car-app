package rentacar.mvp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by savagaborov on 20.1.2020
 */
public class EmailUtil {

    @Autowired
    private static Environment env;

    @Autowired
    private static JavaMailSender javaMailSender;

    public static void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject(subject);
        mail.setText(body);
        javaMailSender.send(mail);
    }
}
