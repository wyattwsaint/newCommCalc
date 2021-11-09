package guiCommCalc;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CommCalc {

	public static void main(String[] args) {

		CommCalcGui run = new CommCalcGui();
	}

	public static int commissionCalculation(int book, int soldFor) {

		int commCalc = book - soldFor;
		double intConvert = Double.valueOf(commCalc);
		double commCalc0 = intConvert / book;
		double commCalc1 = .48 - commCalc0;
		double commCalc2 = commCalc1 / 2;
		double commCalc3 = commCalc2 * soldFor * .7;
		CommCalcGui.commission = (int) commCalc3;
		return CommCalcGui.commission;

	}

	static void sendEmail() {
		
		String to = "wmsaint17@gmail.com";
		String from = "wyatt.saint@aspenwindows.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("wyatt.saint@aspenwindows.com", "Swatts0429*");
			}
		});
		session.setDebug(true);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("SOLD!!");
			message.setText(String.valueOf("message"));
			System.out.println("sending...");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
