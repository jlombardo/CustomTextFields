package edu.wctc.validator.decorator;

import javax.swing.JTextField;

/**
 * This class serves as a general contract for any JTextField 
 * components that are decorated for validation.
 * 
 * @author jlombardo
 */
public abstract class JTextFieldValidatorDecorator extends JTextField {
    
    public abstract boolean isValidInput();

    public abstract String getErrorMsg();
}
