//package nl.hu.bep.shopping.webservices;
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class SendEmail {
//
//    public static void sendMail(String sender, String password, String receiver, String subject, String body) {
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(sender, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(sender));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
//            message.setSubject(subject);
//            message.setText(body);
//
//            Transport.send(message);
//            System.out.println("Your email has successfully been sent!");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
