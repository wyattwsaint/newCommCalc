package guiCommCalc;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommCalcGui extends JFrame implements ActionListener {

	public static String name;
	public static int book;
	public static int soldFor;
	public static int commission;
	public static JButton submit;
	public static JTextField bookField, soldForField, nameField;
	public static JLabel commissionLabel;

	CommCalcGui() {

		JFrame myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
		myFrame.setTitle("CommCalc");
		myFrame.setPreferredSize(new Dimension(650, 250));

		bookField = new JTextField();
		bookField.setPreferredSize(new Dimension(250, 40));

		soldForField = new JTextField();
		soldForField.setPreferredSize(new Dimension(250, 40));
		
		nameField = new JTextField("Name");
		nameField.setPreferredSize(new Dimension(250, 40));

		commissionLabel = new JLabel();
		commissionLabel.setPreferredSize(new Dimension(250, 40));

		submit = new JButton("Submit");
		submit.setPreferredSize(new Dimension(250, 40));
		submit.setFocusable(false);
		submit.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(600, 200));
		panel.setLayout(new FlowLayout());

		panel.add(nameField);
		panel.add(bookField);
		panel.add(soldForField);
		panel.add(commissionLabel);
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

			CommCalc.commissionCalculation(book, soldFor);
			
			String commissionString = String.valueOf(commission);
			commissionLabel.setText(commissionString);
			
		}

	}
}
