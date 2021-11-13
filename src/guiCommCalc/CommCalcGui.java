package guiCommCalc;

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
	
	public static String name, product, comments;
	public static int book, soldFor, commission, totalCommissions;
	public static JButton submit;
	public static JTextField bookField, soldForField, nameField, commentsField, productField;
	public static JLabel commissionLabel, monthlyCommissionLabel;
	public static JPanel panel;
	

	CommCalcGui() {

		JFrame myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		myFrame.setTitle("CommCalc");
		myFrame.setPreferredSize(new Dimension(650, 250));
		
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
		
		submit = new JButton("Submit");
		submit.setPreferredSize(new Dimension(250, 40));
		submit.setFocusable(false);
		submit.addActionListener(this);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(600, 200));
		panel.setLayout(new FlowLayout());

		panel.add(nameField);
		panel.add(productField);
		panel.add(commentsField);
		panel.add(bookField);
		panel.add(soldForField);
		panel.add(commissionLabel);
		panel.add(monthlyCommissionLabel);
		panel.add(submit);
		myFrame.add(panel);
		myFrame.pack();
		myFrame.setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			
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
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			String monthlyCommissions = String.valueOf(totalCommissions);
			monthlyCommissionLabel.setText(monthlyCommissions);
			
			try {
				CommCalc.putDBData();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}

	}
}
