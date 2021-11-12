package guiCommCalc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CommCalc {
	
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://192.168.100.174/db";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

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

	static void putDBData() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
        Statement stmt = null;
            //STEP 2: Register JDBC driver
        	Class.forName("org.mariadb.jdbc.Driver");
            
            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/commcalc", "root", "root");
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting task in given table...");
            stmt = conn.createStatement();

            String sql = "insert into commcalctable (Name, Product, Comments, Date, Book_Price,"
            		+ " Sold_For, Commission) VALUES " + "('" + CommCalcGui.name + "', '" + "test"
            		+ "', '" + "test" + "', '" + "1998-03-26" + "', " + "0000" + ", " + "0000" + 
            		", '" + CommCalcGui.commission + "')";

            stmt.executeUpdate(sql);
            System.out.println("Inserted task in given database table...");

	}

}
