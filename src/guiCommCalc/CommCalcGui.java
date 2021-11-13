package guiCommCalc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommCalcGui extends JFrame implements ActionListener {

	public static JFrame myFrame;
	public static String name, product, comments;
	public static int book, soldFor, commission, totalCommissions, totalSales, notSold;
	public static JButton submitButton, noSaleButton;
	public static JTextField bookField, soldForField, nameField, commentsField, productField;
	public static JLabel commissionLabel, monthlyCommissionLabel, monthlySalesLabel;
	public static JPanel panel, panel2, panel3, panel4;

	CommCalcGui() {

		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		myFrame.setTitle("CommCalc");
		myFrame.setPreferredSize(new Dimension(650, 600));

		nameField = new JTextField("Name");
		nameField.setPreferredSize(new Dimension(250, 40));
		productField = new JTextField("Product");
		productField.setPreferredSize(new Dimension(250, 40));
		commentsField = new JTextField("Comments");
		commentsField.setPreferredSize(new Dimension(250, 40));
		bookField = new JTextField("500");
		bookField.setPreferredSize(new Dimension(250, 40));
		soldForField = new JTextField("450");
		soldForField.setPreferredSize(new Dimension(250, 40));

		commissionLabel = new JLabel();
		commissionLabel.setPreferredSize(new Dimension(250, 40));
		monthlyCommissionLabel = new JLabel();
		monthlyCommissionLabel.setPreferredSize(new Dimension(250, 40));
		monthlySalesLabel = new JLabel();
		monthlySalesLabel.setPreferredSize(new Dimension(250, 40));

		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(250, 40));
		submitButton.setFocusable(false);
		submitButton.addActionListener(this);
		noSaleButton = new JButton("No Sale");
		noSaleButton.setPreferredSize(new Dimension(250, 40));
		noSaleButton.setFocusable(false);
		noSaleButton.addActionListener(this);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 250));
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.DARK_GRAY);
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(300, 250));
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.DARK_GRAY);
		panel3 = new JPanel();
		panel3.setPreferredSize(new Dimension(300, 250));
		panel3.setLayout(new FlowLayout());
		panel3.setBackground(Color.DARK_GRAY);
		panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(300, 250));
		panel4.setLayout(new FlowLayout());
		panel4.setBackground(Color.DARK_GRAY);


		panel.add(nameField);
		panel.add(productField);
		panel.add(commentsField);
		panel.add(bookField);
		panel.add(soldForField);
		panel2.add(commissionLabel);
		panel2.add(monthlyCommissionLabel);
		panel2.add(monthlySalesLabel);
		panel2.add(noSaleButton);
		panel2.add(submitButton);
		
		myFrame.add(panel4);
		myFrame.add(panel);
		myFrame.add(panel3);
		myFrame.add(panel2);
		myFrame.pack();
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

		}

		else if (e.getSource() == noSaleButton) {

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
