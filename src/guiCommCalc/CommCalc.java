package guiCommCalc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CommCalc {

	static int zeros;
	static int ones;
	static String date;

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

	static void sendEmail(String messageVariable, String soldStatus) {

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
			message.setSubject(soldStatus);
			message.setText(String.valueOf(messageVariable));
			System.out.println("sending...");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	static String getDate() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date(System.currentTimeMillis());
		date = (formatter.format(date1));
		return date;

	}

	static void putDBData() throws ClassNotFoundException, SQLException {

		getDate();
		Connection conn = null;
		Statement stmt = null;
		int sold = 1;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/commcalc", "root", "root");
		stmt = conn.createStatement();
		String sql = "insert into commcalctable (Name, Product, Comments, Date, Book_Price,"
				+ " Sold_For, Commission, Sold) VALUES " + "('" + CommCalcGui.name + "', '" + CommCalcGui.product
				+ "', '" + CommCalcGui.comments + "', '" + date + "', " + CommCalcGui.book + ", " + CommCalcGui.soldFor
				+ ", '" + CommCalcGui.commission + "', " + sold + ")";
		stmt.executeUpdate(sql);

	}

	public static int addCurrentMonthCommissionsFromDB() throws ClassNotFoundException, SQLException {

		Connection conn = null;
		Statement stmt = null;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/commcalc", "root", "root");
		stmt = conn.createStatement();
		ResultSet rs;
		String sql = "SELECT SUM(Commission) as total FROM commcalctable WHERE MONTH(Date) = "
				+ "MONTH(CURRENT_DATE());";
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			CommCalcGui.totalCommissions = rs.getInt("total");
		}
		return CommCalcGui.totalCommissions;

	}

	public static int addCurrentMonthSalesFromDB() throws ClassNotFoundException, SQLException {

		Connection conn = null;
		Statement stmt = null;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/commcalc", "root", "root");
		stmt = conn.createStatement();
		ResultSet rs;
		String sql = "SELECT SUM(Sold_For) as total FROM commcalctable WHERE MONTH(Date) = " + "MONTH(CURRENT_DATE());";
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			CommCalcGui.totalSales = rs.getInt("total");
		}
		return CommCalcGui.totalSales;

	}
	
	static int noSale() throws ClassNotFoundException, SQLException {

		getDate();
		Connection conn = null;
		Statement stmt = null;
		CommCalcGui.notSold = 0;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/commcalc", "root", "root");
		stmt = conn.createStatement();
		String sql = "insert into commcalctable (Name, Product, Comments, Date, Book_Price,"
				+ " Sold_For, Commission, Sold) VALUES " + "('"+ "No Sale" + "', '" + "No Sale" + "', '" + "No Sale" + "', '" + date + "', " + "0" + ", " + "0"
				+ ", '" + "0" + "', " + CommCalcGui.notSold + ")";
		stmt.executeUpdate(sql);
		
		return CommCalcGui.notSold;

	}
	
	static int getCloseRateFromDB() throws ClassNotFoundException, SQLException {
		
		double totalNumberOfSales;
		double rawCloseRate;
		Connection conn = null;
		Statement stmt = null;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/commcalc", "root", "root");
		stmt = conn.createStatement();
		ResultSet rs;
		String sql = "SELECT COUNT(sold) AS count FROM commcalctable WHERE sold = 0 AND MONTH(Date) = "
				+ "MONTH(CURRENT_DATE());";
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			zeros = rs.getInt("count");
		}
		String sql2 = "SELECT COUNT(sold) AS count FROM commcalctable WHERE sold = 1 AND MONTH(Date) = "
				+ "MONTH(CURRENT_DATE());";
		rs = stmt.executeQuery(sql2);
		if (rs.next()) {
			ones = rs.getInt("count");
		}
		totalNumberOfSales = zeros + ones;
		rawCloseRate = ones / totalNumberOfSales * 100;
		CommCalcGui.closeRate = (int) rawCloseRate;
		System.out.println(CommCalcGui.closeRate);
		return CommCalcGui.closeRate;
		
	}
	
}
