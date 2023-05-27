package utils;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public boolean send(String destinatario, String assunto, String texto) {
        try {
            String host = "sandbox.smtp.mailtrap.io";
            String porta = "587";
            String usuario = "73f59c2799e517";
            String senha = "5a0fe7c7a5ca7d";

						String remetente = "nome@dominio.com";
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", porta);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usuario, senha);
                }
            });

            MimeMessage mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensagem.setSubject(assunto);
            mensagem.setText(texto);

            Transport.send(mensagem);

            return true;

        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

}