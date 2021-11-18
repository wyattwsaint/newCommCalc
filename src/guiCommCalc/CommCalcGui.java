package guiCommCalc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CommCalcGui extends JFrame implements ActionListener {

	public static JFrame myFrame, recordsFrame;
	public static String name, product, comments, credit;
	public static int book, soldFor, commission, totalCommissions, totalSales, notSold, closeRate;
	public static JButton submitButton, noSaleButton, viewRecordsButton;
	public static JTextField bookField, soldForField, nameField, commentsField, productField, creditField;
	public static JLabel commissionLabel, monthlyCommissionLabel, monthlySalesLabel, nameLabel, productLabel,
			commentsLabel, bookLabel, soldForLabel, commissionDescriptionLabel, monthlyCommissionDescriptionLabel,
			monthlySalesDescriptionLabel, creditLabel, closeRateLabel, closeRateDescriptionLabel, handshakeLabel;
	public static JPanel panel, panel2, panel3, panel4, panel5, panel6, recordsPanel1;
	public static JOptionPane closeRatePane;
	public static ImageIcon handshakeImage;
	public static ImageIcon mainPanePic;
	public static BufferedImage myPicture;
	public static JScrollPane pane;

	CommCalcGui() throws IOException {

		mainPanePic = new ImageIcon("C:\\Users\\wmsai\\Desktop\\CommCalc.png");

		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		myFrame.setTitle("CommCalc");
		myFrame.setPreferredSize(new Dimension(1100, 650));
		myFrame.getContentPane().setBackground(Color.black);
		myFrame.setResizable(false);
		myFrame.setIconImage(mainPanePic.getImage());
		recordsFrame = new JFrame();
		recordsFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		recordsFrame.setTitle("CommCalc Records");
		recordsFrame.setPreferredSize(new Dimension(900, 600));
		recordsFrame.getContentPane().setBackground(Color.black);
		recordsFrame.setResizable(false);
		recordsFrame.setIconImage(mainPanePic.getImage());

		nameField = new JTextField("Name");
		nameField.setPreferredSize(new Dimension(250, 40));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		creditField = new JTextField("Credit");
		creditField.setPreferredSize(new Dimension(250, 40));
		creditField.setHorizontalAlignment(SwingConstants.CENTER);
		productField = new JTextField("Product");
		productField.setPreferredSize(new Dimension(250, 40));
		productField.setHorizontalAlignment(SwingConstants.CENTER);
		commentsField = new JTextField("Comments");
		commentsField.setPreferredSize(new Dimension(250, 40));
		commentsField.setHorizontalAlignment(SwingConstants.CENTER);
		bookField = new JTextField("500");
		bookField.setPreferredSize(new Dimension(250, 40));
		bookField.setHorizontalAlignment(SwingConstants.CENTER);
		soldForField = new JTextField("450");
		soldForField.setPreferredSize(new Dimension(250, 40));
		soldForField.setHorizontalAlignment(SwingConstants.CENTER);

		nameLabel = new JLabel("Name:");
		nameLabel.setPreferredSize(new Dimension(150, 40));
		nameLabel.setForeground(Color.yellow);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		productLabel = new JLabel("Product:");
		productLabel.setPreferredSize(new Dimension(150, 40));
		productLabel.setForeground(Color.yellow);
		productLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		commentsLabel = new JLabel("Comments:");
		commentsLabel.setPreferredSize(new Dimension(150, 40));
		commentsLabel.setForeground(Color.yellow);
		commentsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		bookLabel = new JLabel("Book:");
		bookLabel.setPreferredSize(new Dimension(150, 40));
		bookLabel.setForeground(Color.yellow);
		bookLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		soldForLabel = new JLabel("Sold for:");
		soldForLabel.setPreferredSize(new Dimension(150, 40));
		soldForLabel.setForeground(Color.yellow);
		soldForLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		commissionDescriptionLabel = new JLabel("Commission:");
		commissionDescriptionLabel.setPreferredSize(new Dimension(150, 40));
		commissionDescriptionLabel.setForeground(Color.yellow);
		commissionDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		monthlyCommissionDescriptionLabel = new JLabel("Total monthly commissions:");
		monthlyCommissionDescriptionLabel.setPreferredSize(new Dimension(155, 40));
		monthlyCommissionDescriptionLabel.setForeground(Color.yellow);
		monthlyCommissionDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		monthlySalesDescriptionLabel = new JLabel("Total monthly sales:");
		monthlySalesDescriptionLabel.setPreferredSize(new Dimension(150, 40));
		monthlySalesDescriptionLabel.setForeground(Color.yellow);
		monthlySalesDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		closeRateDescriptionLabel = new JLabel("Close rate:");
		closeRateDescriptionLabel.setPreferredSize(new Dimension(150, 40));
		closeRateDescriptionLabel.setForeground(Color.yellow);
		closeRateDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		creditLabel = new JLabel("Credit:");
		creditLabel.setPreferredSize(new Dimension(150, 40));
		creditLabel.setForeground(Color.yellow);
		creditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		commissionLabel = new JLabel();
		commissionLabel.setPreferredSize(new Dimension(250, 40));
		commissionLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		commissionLabel.setForeground(Color.yellow);
		commissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthlyCommissionLabel = new JLabel();
		monthlyCommissionLabel.setPreferredSize(new Dimension(250, 40));
		monthlyCommissionLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		monthlyCommissionLabel.setForeground(Color.yellow);
		monthlyCommissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthlySalesLabel = new JLabel();
		monthlySalesLabel.setPreferredSize(new Dimension(250, 40));
		monthlySalesLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		monthlySalesLabel.setForeground(Color.yellow);
		monthlySalesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		closeRateLabel = new JLabel();
		closeRateLabel.setPreferredSize(new Dimension(250, 40));
		closeRateLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		closeRateLabel.setForeground(Color.yellow);
		closeRateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myPicture = ImageIO.read(new File("C:\\Users\\wmsai\\Desktop\\CommCalc.png"));
		handshakeLabel = new JLabel(new ImageIcon(myPicture));
		handshakeLabel.setVerticalAlignment(SwingConstants.CENTER);

		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(250, 40));
		submitButton.setBackground(Color.yellow);
		submitButton.setFocusable(false);
		submitButton.addActionListener(this);
		noSaleButton = new JButton("No Sale");
		noSaleButton.setPreferredSize(new Dimension(250, 40));
		noSaleButton.setBackground(Color.MAGENTA);
		noSaleButton.setFocusable(false);
		noSaleButton.addActionListener(this);
		viewRecordsButton = new JButton("View/Add/Delete Records");
		viewRecordsButton.setPreferredSize(new Dimension(250, 40));
		viewRecordsButton.setBackground(Color.yellow);
		viewRecordsButton.setFocusable(false);
		viewRecordsButton.addActionListener(this);

		closeRatePane = new JOptionPane();
		closeRatePane.setPreferredSize(new Dimension(50, 20));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 300));
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(300, 300));
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.black);
		panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(200, 300));
		panel3.setLayout(new FlowLayout());
		panel3.setBackground(Color.black);
		panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(200, 300));
		panel4.setLayout(new FlowLayout());
		panel4.setBackground(Color.black);
		panel5 = new JPanel();
		panel5.setPreferredSize(new Dimension(535, 650));
		panel5.setLayout(new FlowLayout());
		panel5.setBackground(Color.black);
		panel6 = new JPanel();
		panel6.setPreferredSize(new Dimension(535, 650));
		panel6.setLayout(new FlowLayout());
		panel6.setBackground(Color.black);
		recordsPanel1 = new JPanel();
		recordsPanel1.setPreferredSize(new Dimension(800, 575));
		recordsPanel1.setLayout(new FlowLayout());
		recordsPanel1.setBackground(Color.gray);

		panel.add(nameField);
		panel.add(productField);
		panel.add(commentsField);
		panel.add(creditField);
		panel.add(bookField);
		panel.add(soldForField);
		panel2.add(commissionLabel);
		panel2.add(monthlyCommissionLabel);
		panel2.add(monthlySalesLabel);
		panel2.add(closeRateLabel);
		panel2.add(noSaleButton);
		panel2.add(submitButton);
		panel3.add(commissionDescriptionLabel);
		panel3.add(monthlyCommissionDescriptionLabel);
		panel3.add(monthlySalesDescriptionLabel);
		panel3.add(closeRateDescriptionLabel);
		panel3.add(viewRecordsButton);
		panel4.add(nameLabel);
		panel4.add(productLabel);
		panel4.add(commentsLabel);
		panel4.add(creditLabel);
		panel4.add(bookLabel);
		panel4.add(soldForLabel);

		panel5.add(panel4);
		panel5.add(panel);
		panel5.add(panel3);
		panel5.add(panel2);

		panel6.add(handshakeLabel);

		myFrame.add(panel5);
		myFrame.add(panel6);
		myFrame.pack();
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {

			String book0 = bookField.getText();
			book = Integer.valueOf(book0);

			String sold0 = soldForField.getText();
			soldFor = Integer.valueOf(sold0);

			name = nameField.getText();
			product = productField.getText();
			String commentsStripApostrophes = commentsField.getText().replace("'", "");
			comments = commentsStripApostrophes;

			CommCalc.commissionCalculation(book, soldFor);

			String commissionString = String.valueOf(commission);
			commissionLabel.setText(commissionString);

			try {
				CommCalc.addCurrentMonthCommissionsFromDB();
			} catch (ClassNotFoundException | SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			String monthlyCommissions = String.valueOf(totalCommissions);
			monthlyCommissionLabel.setText(monthlyCommissions);

			try {
				CommCalc.addCurrentMonthSalesFromDB();
			} catch (ClassNotFoundException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			String monthlySales = String.valueOf(totalSales);
			monthlySalesLabel.setText(monthlySales);

			try {
				CommCalc.putDBData();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				CommCalc.getCloseRateFromDB();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String closeRateString = String.valueOf(closeRate);
			closeRateLabel.setText(closeRateString + "%");

		}

		else if (e.getSource() == noSaleButton) {

			try {
				CommCalc.getCloseRateFromDB();
			} catch (ClassNotFoundException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			closeRatePane = new JOptionPane();
			closeRatePane.setPreferredSize(new Dimension(50, 20));
			closeRatePane.showMessageDialog(myFrame, "Next!  Closing rate: " + closeRate + "%");
			closeRatePane.setVisible(true);

			try {
				CommCalc.noSale();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			myFrame.dispose();
		}

		else if (e.getSource() == viewRecordsButton) {

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
				String data[][] = new String[40][8];

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
				DefaultTableModel model = new DefaultTableModel(data, columns);
				JTable table = new JTable(model);
				table.setShowGrid(true);
				table.setShowVerticalLines(true);
				pane = new JScrollPane(table);
				
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
			
			recordsPanel1.add(pane);
			recordsFrame.add(recordsPanel1);
			recordsFrame.setPreferredSize(new Dimension(830, 630));
			recordsFrame.pack();
			recordsFrame.setLocationRelativeTo(null);
			recordsFrame.setVisible(true);

		}

	}

}
