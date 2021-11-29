package guiCommCalc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CommCalc {

	static int zeros;
	static int ones;
	static String date;
	static double financing = 0.0;

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		
		//CommCalcGui run = new CommCalcGui();
		
		//populateData();
		
		scrapeData();
		
		
		
	}

	public static int commissionCalculation(int book, int soldFor, boolean checkedOrNot) {
		
		if (checkedOrNot == true) {
			financing = 0.07;
		}
		else if (checkedOrNot == false) {
			financing = 0.0;
		}
		
		double commCalc3 = 0.0;
		int commCalc = book - soldFor;
		double intConvert = Double.valueOf(commCalc);
		double commCalc0 = intConvert / book;
		double commCalc1 = .48 - commCalc0 - financing;
		if (commCalc1 > 0) {
			double commCalc2 = commCalc1 / 2;
			commCalc3 = commCalc2 * soldFor * .7;
		}
		else if (commCalc1 < 0) {
			commCalc3 = 0.0;
		}
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
		
		CommCalcGui.panel3.add(CommCalcGui.emailSentLabel);

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
				+ " Sold_For, Commission, Sold) VALUES " + "('" + "No Sale" + "', '" + "No Sale" + "', '" + "No Sale"
				+ "', '" + date + "', " + "0" + ", " + "0" + ", '" + "0" + "', " + CommCalcGui.notSold + ")";
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
		return CommCalcGui.closeRate;

	}

	static JTable createRecordsTable() {

		try {

			Connection conn = null;
			Statement stmt = null;
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/commcalc", "root", "root");
			stmt = conn.createStatement();
			ResultSet rs;
			String sql = "SELECT * FROM commcalctable";
			rs = stmt.executeQuery(sql);

			String columns[] = { "id", "Name", "Product", "Comments", "Date", "Book", "Sold_For", "Commission" };
			String data[][] = new String[70][8];

			int i = 0;
			while (rs.next()) {

				String id = rs.getString("id");
				String name = rs.getString("Name");
				String product = rs.getString("Product");
				String comments = rs.getString("Comments");
				String date = rs.getString("Date");
				String book = rs.getString("Book_Price");
				String soldfor = rs.getString("Sold_For");
				String commission = rs.getString("Commission");
				data[i][0] = id;
				data[i][1] = name;
				data[i][2] = product;
				data[i][3] = comments;
				data[i][4] = date;
				data[i][5] = book;
				data[i][6] = soldfor;
				data[i][7] = commission;

				i++;

			}

			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			DefaultTableModel model = new DefaultTableModel(data, columns);
			CommCalcGui.table = new JTable(model);
			CommCalcGui.table.setShowGrid(true);
			CommCalcGui.table.setShowVerticalLines(true);
			CommCalcGui.table.setGridColor(Color.yellow);
			CommCalcGui.table.setBackground(Color.black);
			CommCalcGui.table.setForeground(Color.yellow);
			CommCalcGui.table.setDefaultRenderer(String.class, centerRenderer);
			CommCalcGui.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			TableColumnModel columnModel = CommCalcGui.table.getColumnModel();
			for (int column = 0; column < 8; column++) {
				int width = 15; // Min width
				for (int row = 0; row < CommCalcGui.table.getRowCount(); row++) {
					TableCellRenderer renderer = CommCalcGui.table.getCellRenderer(row, column);
					Component comp = CommCalcGui.table.prepareRenderer(renderer, row, column);
					width = Math.max(comp.getPreferredSize().width + 1, width);
					columnModel.getColumn(column).setPreferredWidth(width);
				}
			}
			for (int x = 0; x < 8; x++) {
				CommCalcGui.table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
			}

			CommCalcGui.pane = new JScrollPane(CommCalcGui.table);
			CommCalcGui.pane.setPreferredSize(new Dimension(775, 525));

		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return CommCalcGui.table;
	}
	
	static void deleteRecord() throws ClassNotFoundException, SQLException {
		
		int idNumber = CommCalcGui.idNumber;
		Connection conn = null;
		Statement stmt = null;
		int sold = 1;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1/commcalc", "root", "root");
		stmt = conn.createStatement();
		String sql = "DELETE FROM commcalctable where id = " + idNumber;
		stmt.executeUpdate(sql);
		
	}
	
	static void monthlyBonus() {
		
		if (CommCalcGui.totalSales > 0 && CommCalcGui.totalSales < 60000) {
			int bonus = 0;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 60000 && CommCalcGui.totalSales < 70000) {
			int bonus = 800;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 70000 && CommCalcGui.totalSales < 80000) {
			int bonus = 1200;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 80000 && CommCalcGui.totalSales < 90000) {
			int bonus = 1500;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 90000 && CommCalcGui.totalSales < 100000) {
			int bonus = 1700;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 100000 && CommCalcGui.totalSales < 115000) {
			int bonus = 2000;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 115000 && CommCalcGui.totalSales < 130000) {
			int bonus = 2200;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 130000 && CommCalcGui.totalSales < 145000) {
			int bonus = 2400;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 145000 && CommCalcGui.totalSales < 160000) {
			int bonus = 2600;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 160000 && CommCalcGui.totalSales < 170000) {
			int bonus = 2800;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 170000 && CommCalcGui.totalSales < 180000) {
			int bonus = 3200;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 180000 && CommCalcGui.totalSales < 190000) {
			int bonus = 3500;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 190000 && CommCalcGui.totalSales < 200000) {
			int bonus = 3700;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 200000 && CommCalcGui.totalSales < 215000) {
			int bonus = 4000;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 215000 && CommCalcGui.totalSales < 230000) {
			int bonus = 4200;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 230000 && CommCalcGui.totalSales < 245000) {
			int bonus = 4400;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 245000 && CommCalcGui.totalSales < 260000) {
			int bonus = 4600;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 260000 && CommCalcGui.totalSales < 270000) {
			int bonus = 4800;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 270000 && CommCalcGui.totalSales < 280000) {
			int bonus = 5200;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 280000 && CommCalcGui.totalSales < 290000) {
			int bonus = 5500;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else if (CommCalcGui.totalSales >= 290000 && CommCalcGui.totalSales < 300000) {
			int bonus = 5700;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		} else {
			int bonus = 6000;
			CommCalcGui.monthlyBonusLabel.setText(String.valueOf(bonus));
		}
		double taxAdjust = (Integer.valueOf(CommCalcGui.monthlyBonusLabel.getText())) * .7;
		int taxAdjust1 = (int) taxAdjust;
		CommCalcGui.monthlyBonusLabel.setText(String.valueOf(taxAdjust1));
		
		
	}
	
	static void populateData() throws ClassNotFoundException, SQLException {
		
		addCurrentMonthCommissionsFromDB();
		String monthlyCommissions = String.valueOf(CommCalcGui.totalCommissions);
		CommCalcGui.monthlyCommissionLabel.setText(monthlyCommissions);
		
		getCloseRateFromDB();
		String closeRateString = String.valueOf(CommCalcGui.closeRate);
		CommCalcGui.closeRateLabel.setText(closeRateString + "%");
		
		addCurrentMonthSalesFromDB();
		String monthlySales = String.valueOf(CommCalcGui.totalSales);
		CommCalcGui.monthlySalesLabel.setText(monthlySales);
		
		monthlyBonus();
		
	}
	
	static void scrapeData() {
		
		try {
			String url = "https://app.remotesf.com/login";
			Document doc = Jsoup.connect(url).maxBodySize(0).get();
			Elements info = doc.getElementsByTag("div");
			
			System.out.println(doc);
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
