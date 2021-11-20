package guiCommCalc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class addDeleteRecords extends JFrame implements ActionListener{

	addDeleteRecords() {
		
		this.setPreferredSize(new Dimension(830, 640));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
}
