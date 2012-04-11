package edu.wctc.validator.decorator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class is provided for demonstration/lab purposes. It demonstrates how 
 * to use the Decorator Pattern to provide validation capabilities to 
 * JTextField components.
 * 
 * Compared to the Strategy version, this is slightly less flexible because
 * the decorators are all based exclusively on JTextField. For other
 * components you would need to construct new decorator classes.
 * 
 * @author Jim Lombardo
 * @version 1.01
 */
public class MainGUI extends JFrame implements ActionListener {
	private JTextField txtField1;
        private JTextFieldValidatorDecorator field1;
	private JTextField txtField2;
        private JTextFieldValidatorDecorator field2;
	private JTextField txtField3;
        private JTextFieldValidatorDecorator field3;
	private JTextFieldValidatorDecorator[] textFields = new JTextFieldValidatorDecorator[3];
	private JPanel fieldPanel;
	private JPanel btnPanel;
	private JButton submitBtn;
	private Container c;
	
	public MainGUI() {
            txtField1 = new JTextField(10);
            txtField1.setName("Field1");
            field1 = new RequiredFieldDecorator(txtField1);
            
            txtField2 = new JTextField(10);
            txtField2.setName("Field2");
            // Here we wrap a pain JTextField
//            field2 = new NumericRangeDecorator(txtField2,20,50);
            
            // Here we wrap a RequiredFieldDecorator
            JTextFieldValidatorDecorator dField = new NotRequiredFieldDecorator(txtField2);
            dField.setName("Field2");
            field2 = new NumericRangeDecorator(dField,20,50);
            
            txtField3 = new JTextField(10);
            txtField3.setName("Field3");
            field3 = new DateRangeDecorator(txtField3,"8/1/2007","12/1/2007");
            
            textFields[0] = field1;
            textFields[1] = field2;
            textFields[2] = field3;
    
            c = this.getContentPane();


            // ignore this, we're just building the gui...
            fieldPanel = new JPanel();
            // rows,columns
            fieldPanel.setLayout(new GridLayout(3,2));
            fieldPanel.add(new JLabel("Required entry:"));
            fieldPanel.add(field1);
            fieldPanel.add(new JLabel("Number range (20-50):"));
            fieldPanel.add(field2);
            fieldPanel.add(new JLabel("Date range (8/1/2007 - 12/1/2007:"));
            fieldPanel.add(field3);
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
                if( !textFields[i].isValidInput() ) {
                    JOptionPane.showMessageDialog(
                            null, textFields[i].getErrorMsg(), 
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    allValid = false;
                }
            }

            if (allValid) {
                JOptionPane.showMessageDialog(
                        null, "Validation Succeeded", 
                        "Validation Success", JOptionPane.INFORMATION_MESSAGE);
            }
	}

}
