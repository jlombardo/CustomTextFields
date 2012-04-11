package edu.wctc.validator.strategy;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class is provided for demonstration/lab purposes. It demonstrates how 
 * to use validation components polymorphically via the Strategy Pattern.
 * 
 * Compared to the Decorator Pattern version, this one is more flexible 
 * because the Strategy components may be added to any high-level class.
 * 
 * @author Jim Lombardo
 * @version 1.01
 */
public class MainGUI extends JFrame implements ActionListener {
	private JTextFieldValidateable txtField1;
	private JTextFieldValidateable txtField2;
	private JTextFieldValidateable txtField3;
	private JTextFieldValidateable[] textFields = new JTextFieldValidateable[3];
	private JPanel fieldPanel;
	private JPanel btnPanel;
	private JButton submitBtn;
	private Container c;
	
	public MainGUI() {
		// a required field -- leave this one along
		txtField1 = new JTextFieldValidateable(10);
		txtField1.setValidator(new RequiredFieldValidator());
		txtField1.setErrorMsg("First field is a required field");
		textFields[0] = txtField1;
		
		// a numeric field for range validation -- leave this one alone
		txtField2 = new JTextFieldValidateable(10);
		txtField2.setValidator(new RangeValidator(
                        RangeValidator.ValidatorType.NUMBER, "20", "50"));
		txtField2.setErrorMsg(
                        "Second field must be in the range 20 - 50");
		textFields[1] = txtField2;
		
		//////////////////////////////////////////////////////
		//  MAKE YOUR MODIFICATIONS HERE
		//
		//  Here's how: modify the RangeValidator class to handle the
		//  type of date test you want to use. Then, modify the new RangeValidator(...)
		//  reference below to pass the appropriate arguments you need. If you don't
		//  need the 2nd or 3rd arguments, just pass null. You will also
		//  want to modify setErrorMsg(...) to use your own message.
		////////////////////////////////////////////////////////
		txtField3 = new JTextFieldValidateable(10);
		txtField3.setValidator(new RangeValidator(
                        RangeValidator.ValidatorType.DATE, "8/1/2007", "12/1/2007"));
		txtField3.setErrorMsg(
                        "Third field must be a date in the range 8/1/2007 - 12/1/2007");
		textFields[2] = txtField3;
		c = this.getContentPane();
		
		
		// ignore this, we're just building the gui...
		fieldPanel = new JPanel();
                // rows,columns
                fieldPanel.setLayout(new GridLayout(3,2));
                fieldPanel.add(new JLabel("Required entry:"));
		fieldPanel.add(txtField1);
                fieldPanel.add(new JLabel("Number range (20-50):"));
                fieldPanel.add(txtField2);
                fieldPanel.add(new JLabel("Date range (8/1/2007 - 12/1/2007:"));
                fieldPanel.add(txtField3);
		c.add(fieldPanel, BorderLayout.CENTER);
		
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(this);
		btnPanel = new JPanel();
		btnPanel.add(submitBtn);
		c.add(btnPanel, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(50, 50, 425, 150);
		this.setVisible(true);
	}
	
	// Processing the button click event ...
	public void actionPerformed(ActionEvent e) {
		boolean allValid = true;  // sentinel value
		
		// Polymorphic validation ... easily reused.
		for(int i=0; i < textFields.length; i++) {
			Validator v = textFields[i].getValidator();
			v.setTestValue(textFields[i].getText());
			if( !v.isValid() ) {
				JOptionPane.showMessageDialog(null, textFields[i].getErrorMsg(), 
						"Validation Error", JOptionPane.ERROR_MESSAGE);
				allValid = false;
			}
		}

		if (allValid) {
			JOptionPane.showMessageDialog(null, "Validation Succeeded", 
					"Validation Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
