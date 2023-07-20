package app.planet.core.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class MailSender {
    private static final String USER = "barrk999@qq.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "gkpzflzkbfvvdiba"; // 授权码
    public static final String LOGIN_CODE_INFORMATION = "。该验证码用于Planet登录身份确认，五分钟内有效，请勿泄露和转发。如非本人操作，请忽略此邮件。";
    public static final String LOGIN_CODE_MESSAGE = "欢迎登录Planet，验证码是:";
    public static final String LOGIN_CODE_TITLE = "您正在登录Planet";
    public static final String UPDATE_PASSWORD_CODE_INFORMATION = "。该验证码用于Planet登录身份确认，五分钟内有效，请勿泄露和转发。如非本人操作，请忽略此邮件。";
    public static final String UPDATE_PASSWORD_CODE_MESSAGE = "您正在设置或修改Planet账户密码，验证码是:";
    public static final String UPDATE_PASSWORD_CODE_TITLE = "您正在设置或修改Planet账户密码";


    public static boolean sendMail(String to, String text, String title) {
        try {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.user", USER);
            props.put("mail.password", PASSWORD);
            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session mailSession = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(mailSession);
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);
            InternetAddress toAddress = new InternetAddress(to);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(title);
            message.setContent(text, "text/html;charset=UTF-8");
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
