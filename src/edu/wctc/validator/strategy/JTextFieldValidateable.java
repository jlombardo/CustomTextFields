package edu.wctc.validator.strategy;

import javax.swing.JTextField;

/**
 * This class represents a custom JTextField and Validateable implementation 
 * providing a Strategy component (Validator) to perform various 
 * validation functions. Although this high-level class is based on a 
 * JTextField, any high-level class that implements Validateable and 
 * uses a Validator strategy component will work.
 * 
 * @author jlombardo
 */
public class JTextFieldValidateable extends JTextField implements Validateable {
	private Validator validator;
	private String errorMsg;
	
	public JTextFieldValidateable(int cols) {
		super(cols);
	}
	
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	public Validator getValidator() {
		return validator;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
