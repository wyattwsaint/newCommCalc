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
import java.sql.SQLException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class CommCalcGui extends JFrame implements ActionListener {

	public static JFrame myFrame, recordsFrame, addRecordFrame, deleteRecordFrame;
	public static String name, product, comments, credit, emailMessage, emailSubject;
	public static int book, soldFor, commission, totalCommissions, totalSales, notSold, closeRate, idNumber;
	public static JButton submitButton, noSaleButton, viewRecordsButton, addRecordButton, deleteRecordButton,
			recordSubmitButton, deleteButton;
	public static JTextField bookField, soldForField, nameField, commentsField, productField, creditField,
			idNumberField;
	public static JLabel commissionLabel, monthlyCommissionLabel, monthlySalesLabel, nameLabel, productLabel,
			commentsLabel, bookLabel, soldForLabel, commissionDescriptionLabel, monthlyCommissionDescriptionLabel,
			monthlySalesDescriptionLabel, creditLabel, closeRateLabel, closeRateDescriptionLabel, handshakeLabel,
			deleteRecordLabel, emailSentLabel, financingLabel, monthlyBonusLabel, monthlyBonusDescriptionLabel,
			logoLabel1, logoLabel2, logoLabel3, twoPercentLabel, selfGenLabel;
	public static JPanel panel, panel2, panel3, panel4, panel5, panel6, recordsPanel1, recordsPanel2, addRecordPanel1,
			deleteRecordPanel1, financingPanel, logoPanel, logoPanel1, logoPanel2, logoPanel3, checkBoxPanel1,
			checkBoxPanel2, checkBoxPanel3;
	public static JOptionPane closeRatePane, recordAddedPane;
	public static ImageIcon handshakeImage, mainPanePic;
	public static BufferedImage myPicture;
	public static JScrollPane pane;
	public static JTable table;
	public static JCheckBox financingCheckBox, twoPercentCheckBox, selfGenCheckBox;

	CommCalcGui() throws IOException {

		mainPanePic = new ImageIcon("C:\\Users\\wmsai\\Desktop\\Java Projects\\CommCalc2\\CommCalc.png");

		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		myFrame.setTitle("CommCalc");
		myFrame.setPreferredSize(new Dimension(1100, 690));
		myFrame.getContentPane().setBackground(Color.black);
		myFrame.setResizable(false);
		myFrame.setIconImage(mainPanePic.getImage());
		recordsFrame = new JFrame();
		recordsFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		recordsFrame.setTitle("CommCalc Records");
		recordsFrame.setPreferredSize(new Dimension(900, 600));
		recordsFrame.getContentPane().setBackground(Color.black);
		recordsFrame.setResizable(true);
		recordsFrame.setIconImage(mainPanePic.getImage());

		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(250, 40));
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		creditField = new JTextField();
		creditField.setPreferredSize(new Dimension(250, 40));
		creditField.setHorizontalAlignment(SwingConstants.CENTER);
		productField = new JTextField();
		productField.setPreferredSize(new Dimension(250, 40));
		productField.setHorizontalAlignment(SwingConstants.CENTER);
		commentsField = new JTextField();
		commentsField.setPreferredSize(new Dimension(250, 40));
		commentsField.setHorizontalAlignment(SwingConstants.CENTER);
		bookField = new JTextField();
		bookField.setPreferredSize(new Dimension(250, 40));
		bookField.setHorizontalAlignment(SwingConstants.CENTER);
		soldForField = new JTextField();
		soldForField.setPreferredSize(new Dimension(250, 40));
		soldForField.setHorizontalAlignment(SwingConstants.CENTER);
		idNumberField = new JTextField();
		idNumberField.setPreferredSize(new Dimension(250, 40));
		idNumberField.setHorizontalAlignment(SwingConstants.CENTER);

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
		commissionLabel.setOpaque(true);
		commissionLabel.setBackground(Color.darkGray);
		commissionLabel.setPreferredSize(new Dimension(250, 40));
		commissionLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		commissionLabel.setForeground(Color.yellow);
		commissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthlyCommissionLabel = new JLabel();
		monthlyCommissionLabel.setOpaque(true);
		monthlyCommissionLabel.setBackground(Color.darkGray);
		monthlyCommissionLabel.setPreferredSize(new Dimension(250, 40));
		monthlyCommissionLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		monthlyCommissionLabel.setForeground(Color.yellow);
		monthlyCommissionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthlySalesLabel = new JLabel();
		monthlySalesLabel.setOpaque(true);
		monthlySalesLabel.setBackground(Color.darkGray);
		monthlySalesLabel.setPreferredSize(new Dimension(250, 40));
		monthlySalesLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		monthlySalesLabel.setForeground(Color.yellow);
		monthlySalesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		closeRateLabel = new JLabel();
		closeRateLabel.setOpaque(true);		
		closeRateLabel.setBackground(Color.darkGray);
		closeRateLabel.setPreferredSize(new Dimension(250, 40));
		closeRateLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		closeRateLabel.setForeground(Color.yellow);
		closeRateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myPicture = ImageIO.read(new File("C:\\Users\\wmsai\\Desktop\\Java Projects\\CommCalc2\\CommCalc.png"));
		handshakeLabel = new JLabel(new ImageIcon(myPicture));
		handshakeLabel.setVerticalAlignment(SwingConstants.CENTER);
		deleteRecordLabel = new JLabel("Record \"id\" number:");
		deleteRecordLabel.setPreferredSize(new Dimension(150, 40));
		deleteRecordLabel.setForeground(Color.yellow);
		deleteRecordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailSentLabel = new JLabel("Email sent");
		emailSentLabel.setPreferredSize(new Dimension(150, 40));
		emailSentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		emailSentLabel.setForeground(Color.green);
		emailSentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		financingLabel = new JLabel("Check if financing:");
		financingLabel.setPreferredSize(new Dimension(170, 30));
		financingLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		financingLabel.setForeground(Color.yellow);
		twoPercentLabel = new JLabel("2% Override:");
		twoPercentLabel.setPreferredSize(new Dimension(170, 30));
		twoPercentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		twoPercentLabel.setForeground(Color.yellow);
		selfGenLabel = new JLabel("Self-gen':");
		selfGenLabel.setPreferredSize(new Dimension(170, 30));
		selfGenLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		selfGenLabel.setForeground(Color.yellow);
		monthlyBonusLabel = new JLabel();
		monthlyBonusLabel.setOpaque(true);
		monthlyBonusLabel.setBackground(Color.darkGray);
		monthlyBonusLabel.setPreferredSize(new Dimension(250, 40));
		monthlyBonusLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		monthlyBonusLabel.setForeground(Color.yellow);
		monthlyBonusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthlyBonusDescriptionLabel = new JLabel("Monthly bonus:");
		monthlyBonusDescriptionLabel.setPreferredSize(new Dimension(150, 40));
		monthlyBonusDescriptionLabel.setForeground(Color.yellow);
		monthlyBonusDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		logoLabel1 = new JLabel("$");
		logoLabel1.setPreferredSize(new Dimension(500, 170));
		logoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel1.setVerticalAlignment(SwingConstants.BOTTOM);
		logoLabel1.setVerticalTextPosition(SwingConstants.BOTTOM);
		logoLabel1.setFont(new Font("Stencil", Font.PLAIN, 170));
		logoLabel1.setForeground(Color.green);
		logoLabel3 = new JLabel("Comm Calc!");
		logoLabel3.setPreferredSize(new Dimension(500, 95));
		logoLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel3.setFont(new Font("Showcard Gothic", Font.PLAIN, 50));
		logoLabel3.setForeground(Color.green);
		

		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(250, 40));
		submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
		submitButton.setBackground(Color.yellow);
		submitButton.setFocusable(false);
		submitButton.addActionListener(this);
		noSaleButton = new JButton("No Sale");
		noSaleButton.setFont(new Font("Arial", Font.PLAIN, 20));
		noSaleButton.setPreferredSize(new Dimension(250, 40));
		noSaleButton.setBackground(Color.red);
		noSaleButton.setFocusable(false);
		noSaleButton.addActionListener(this);
		viewRecordsButton = new JButton("View/Add/Delete Records");
		viewRecordsButton.setPreferredSize(new Dimension(250, 40));
		viewRecordsButton.setBackground(Color.yellow);
		viewRecordsButton.setFocusable(false);
		viewRecordsButton.addActionListener(this);
		addRecordButton = new JButton("Add record");
		addRecordButton.setPreferredSize(new Dimension(250, 40));
		addRecordButton.setBackground(Color.yellow);
		addRecordButton.setFocusable(false);
		addRecordButton.addActionListener(this);
		deleteRecordButton = new JButton("Delete record");
		deleteRecordButton.setPreferredSize(new Dimension(250, 40));
		deleteRecordButton.setBackground(Color.yellow);
		deleteRecordButton.setFocusable(false);
		deleteRecordButton.addActionListener(this);
		recordSubmitButton = new JButton("Submit");
		recordSubmitButton.setPreferredSize(new Dimension(250, 40));
		recordSubmitButton.setBackground(Color.yellow);
		recordSubmitButton.setFocusable(false);
		recordSubmitButton.addActionListener(this);
		deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(new Dimension(250, 40));
		deleteButton.setBackground(Color.yellow);
		deleteButton.setFocusable(false);
		deleteButton.addActionListener(this);

		financingCheckBox = new JCheckBox();
		financingCheckBox.setPreferredSize(new Dimension(30, 30));
		financingCheckBox.setBackground(Color.black);
		financingCheckBox.addActionListener(this);
		twoPercentCheckBox = new JCheckBox();
		twoPercentCheckBox.setPreferredSize(new Dimension(30, 30));
		twoPercentCheckBox.setBackground(Color.black);
		twoPercentCheckBox.addActionListener(this);
		selfGenCheckBox = new JCheckBox();
		selfGenCheckBox.setPreferredSize(new Dimension(30, 30));
		selfGenCheckBox.setBackground(Color.black);
		selfGenCheckBox.addActionListener(this);

		closeRatePane = new JOptionPane();
		closeRatePane.setPreferredSize(new Dimension(50, 20));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 300));
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.black);
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(300, 340));
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.black);
		panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(200, 340));
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
		recordsPanel1.setPreferredSize(new Dimension(800, 535));
		recordsPanel1.setLayout(new FlowLayout());
		recordsPanel1.setBackground(Color.black);
		recordsPanel2 = new JPanel();
		recordsPanel2.setPreferredSize(new Dimension(800, 55));
		recordsPanel2.setLayout(new FlowLayout());
		recordsPanel2.setBackground(Color.black);
		addRecordPanel1 = new JPanel();
		addRecordPanel1.setPreferredSize(new Dimension(500, 600));
		addRecordPanel1.setLayout(new FlowLayout());
		addRecordPanel1.setBackground(Color.black);
		deleteRecordPanel1 = new JPanel();
		deleteRecordPanel1.setPreferredSize(new Dimension(500, 600));
		deleteRecordPanel1.setLayout(new FlowLayout());
		deleteRecordPanel1.setBackground(Color.black);
		financingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		financingPanel.setPreferredSize(new Dimension(535, 500));
		financingPanel.setBackground(Color.black);
		logoPanel = new JPanel();
		logoPanel.setPreferredSize(new Dimension(535, 500));
		logoPanel.setLayout(new FlowLayout());
		logoPanel.setBackground(Color.black);
		logoPanel1 = new JPanel();
		logoPanel1.setPreferredSize(new Dimension(535, 145));
		logoPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		logoPanel1.setBackground(Color.black);
		logoPanel2 = new JPanel();
		logoPanel2.setPreferredSize(new Dimension(535, 245));
		logoPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		logoPanel2.setBackground(Color.black);
		logoPanel3 = new JPanel();
		logoPanel3.setPreferredSize(new Dimension(535, 95));
		logoPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		logoPanel3.setBackground(Color.black);
		checkBoxPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkBoxPanel1.setPreferredSize(new Dimension(300, 40));
		checkBoxPanel1.setBackground(Color.black);
		checkBoxPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkBoxPanel2.setPreferredSize(new Dimension(300, 40));
		checkBoxPanel2.setBackground(Color.black);
		checkBoxPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkBoxPanel3.setPreferredSize(new Dimension(300, 40));
		checkBoxPanel3.setBackground(Color.black);
		
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
		panel2.add(monthlyBonusLabel);
		panel2.add(submitButton);
		panel2.add(noSaleButton);
		panel3.add(commissionDescriptionLabel);
		panel3.add(monthlyCommissionDescriptionLabel);
		panel3.add(monthlySalesDescriptionLabel);
		panel3.add(closeRateDescriptionLabel);
		panel3.add(monthlyBonusDescriptionLabel);
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

		checkBoxPanel1.add(financingLabel);
		checkBoxPanel1.add(financingCheckBox);
		checkBoxPanel2.add(twoPercentLabel);
		checkBoxPanel2.add(twoPercentCheckBox);
		checkBoxPanel3.add(selfGenLabel);
		checkBoxPanel3.add(selfGenCheckBox);
		financingPanel.add(checkBoxPanel1);
		financingPanel.add(checkBoxPanel2);
		financingPanel.add(checkBoxPanel3);
		
		logoPanel1.add(logoLabel1);
		logoPanel2.add(handshakeLabel);
		logoPanel3.add(logoLabel3);
		logoPanel.add(logoPanel1);
		logoPanel.add(logoPanel2);
		logoPanel.add(logoPanel3);
		
		panel6.add(logoPanel);
		panel6.add(financingPanel);

		myFrame.add(panel5);
		myFrame.add(panel6);
		myFrame.pack();
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {

			boolean fnnceCheckBox = financingCheckBox.isSelected();
			boolean twoPrcntCheckBox = twoPercentCheckBox.isSelected();
			boolean slfGnCheckBox = selfGenCheckBox.isSelected();
			 
			
			String book0 = bookField.getText();
			book = Integer.valueOf(book0);

			String sold0 = soldForField.getText();
			soldFor = Integer.valueOf(sold0);

			name = nameField.getText();
			product = productField.getText();
			String commentsStripApostrophes = commentsField.getText().replace("'", "");
			comments = commentsStripApostrophes;

			CommCalc.commissionCalculation(book, soldFor, fnnceCheckBox, twoPrcntCheckBox, slfGnCheckBox);

			String commissionString = String.valueOf(commission);
			commissionLabel.setText(commissionString);
			
			try {
				CommCalc.putDBData();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			try {
				CommCalc.addCurrentMonthCommissionsFromDB();
			} catch (ClassNotFoundException | SQLException e3) {
				e3.printStackTrace();
			}

			String monthlyCommissions = String.valueOf(totalCommissions);
			monthlyCommissionLabel.setText(monthlyCommissions);

			try {
				CommCalc.addCurrentMonthSalesFromDB();
			} catch (ClassNotFoundException | SQLException e2) {
				e2.printStackTrace();
			}

			String monthlySales = String.valueOf(totalSales);
			monthlySalesLabel.setText(monthlySales);

			try {
				CommCalc.getCloseRateFromDB();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			String closeRateString = String.valueOf(closeRate);
			closeRateLabel.setText(closeRateString + "%");
			
			CommCalc.monthlyBonus();

			String preMessage = "Commission is " + commission + "! I sold it for " + soldFor + "! Our total monthly "
					+ "commission is " + totalCommissions + ". Our total monthly sales are " + totalSales + ". " + 
					"Current monthly bonus is: " + monthlyBonusLabel.getText();
			String preSubject = "SOOOOOOLD!!";

			emailMessage = preMessage;
			emailSubject = preSubject;

			CommCalc.sendEmail(emailMessage, emailSubject);

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

			String preMessage = "We'll get the next one.";
			String preSubject = "No sale my heart :(";

			emailMessage = preMessage;
			emailSubject = preSubject;

			CommCalc.sendEmail(emailMessage, emailSubject);

			myFrame.dispose();

		}

		else if (e.getSource() == viewRecordsButton) {

			CommCalc.createRecordsTable();

			recordsPanel1.add(pane);
			recordsPanel2.add(addRecordButton);
			recordsPanel2.add(deleteRecordButton);
			recordsFrame.add(recordsPanel1);
			recordsFrame.add(recordsPanel2);
			recordsFrame.setPreferredSize(new Dimension(830, 640));
			recordsFrame.pack();
			recordsFrame.setLocationRelativeTo(null);
			recordsFrame.setVisible(true);

		}

		else if (e.getSource() == addRecordButton) {

			addRecordPanel1.add(nameLabel);
			addRecordPanel1.add(nameField);
			addRecordPanel1.add(productLabel);
			addRecordPanel1.add(productField);
			addRecordPanel1.add(commentsLabel);
			addRecordPanel1.add(commentsField);
			addRecordPanel1.add(bookLabel);
			addRecordPanel1.add(bookField);
			addRecordPanel1.add(soldForLabel);
			addRecordPanel1.add(soldForField);
			addRecordPanel1.add(recordSubmitButton);

			addRecordFrame = new JFrame();
			addRecordFrame.add(addRecordPanel1);
			addRecordFrame.setPreferredSize(new Dimension(430, 320));
			addRecordFrame.pack();
			addRecordFrame.setLocationRelativeTo(null);
			addRecordFrame.setVisible(true);

		}

		else if (e.getSource() == recordSubmitButton) {

			String book0 = bookField.getText();
			book = Integer.valueOf(book0);

			String sold0 = soldForField.getText();
			soldFor = Integer.valueOf(sold0);

			name = nameField.getText();
			product = productField.getText();
			String commentsStripApostrophes = commentsField.getText().replace("'", "");
			comments = commentsStripApostrophes;

			try {
				CommCalc.putDBData();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			recordAddedPane = new JOptionPane();
			recordAddedPane.setPreferredSize(new Dimension(50, 20));
			recordAddedPane.showMessageDialog(addRecordFrame, "Record added!");
			recordAddedPane.setVisible(true);

			addRecordFrame.dispose();

		}

		else if (e.getSource() == deleteRecordButton) {

			deleteRecordPanel1.add(deleteRecordLabel);
			deleteRecordPanel1.add(idNumberField);
			deleteRecordPanel1.add(deleteButton);

			deleteRecordFrame = new JFrame();
			deleteRecordFrame.add(deleteRecordPanel1);
			deleteRecordFrame.setPreferredSize(new Dimension(430, 140));
			deleteRecordFrame.pack();
			deleteRecordFrame.setLocationRelativeTo(null);
			deleteRecordFrame.setVisible(true);

		}

		else if (e.getSource() == deleteButton) {

			String idNumber1 = idNumberField.getText();
			idNumber = Integer.valueOf(idNumber1);
			System.out.println(idNumber);

			try {
				CommCalc.deleteRecord();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

			recordAddedPane = new JOptionPane();
			recordAddedPane.setPreferredSize(new Dimension(50, 20));
			recordAddedPane.showMessageDialog(deleteRecordFrame, "Record deleted!");
			recordAddedPane.setVisible(true);

			deleteRecordFrame.dispose();

		}

	}

}
