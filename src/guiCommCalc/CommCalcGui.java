package guiCommCalc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CommCalcGui extends JFrame implements ActionListener {

	public static JFrame myFrame;
	public static String name, product, comments, credit;
	public static int book, soldFor, commission, totalCommissions, totalSales, notSold, closeRate;
	public static JButton submitButton, noSaleButton;
	public static JTextField bookField, soldForField, nameField, commentsField, productField, creditField;
	public static JLabel commissionLabel, monthlyCommissionLabel, monthlySalesLabel, nameLabel, productLabel, commentsLabel, bookLabel, soldForLabel, commissionDescriptionLabel, monthlyCommissionDescriptionLabel, monthlySalesDescriptionLabel, creditLabel, closeRateLabel, closeRateDescriptionLabel;
	public static JPanel panel, panel2, panel3, panel4;
	public static JOptionPane closeRatePane;

	CommCalcGui() {

		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		myFrame.setTitle("CommCalc");
		myFrame.setPreferredSize(new Dimension(535, 650));
		myFrame.getContentPane().setBackground(Color.black);
		myFrame.setResizable(false);
		
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

		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(250, 40));
		submitButton.setBackground(Color.yellow);
		submitButton.setFocusable(false);
		submitButton.addActionListener(this);
		noSaleButton = new JButton("No Sale");
		noSaleButton.setPreferredSize(new Dimension(250, 40));
		noSaleButton.setBackground(Color.yellow);
		noSaleButton.setFocusable(false);
		noSaleButton.addActionListener(this);
		
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
		panel4.add(nameLabel);
		panel4.add(productLabel);
		panel4.add(commentsLabel);
		panel4.add(creditLabel);
		panel4.add(bookLabel);
		panel4.add(soldForLabel);
		
		myFrame.add(panel4);
		myFrame.add(panel);
		myFrame.add(panel3);
		myFrame.add(panel2);
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
			comments = commentsField.getText();

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

	}

}
