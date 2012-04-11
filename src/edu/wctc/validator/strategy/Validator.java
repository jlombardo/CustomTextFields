package edu.wctc.validator.strategy;

/**
 * The general contract for all Validators.
 * 
 * @author jlombardo
 */
public interface Validator {

	/** Test a form field for validity based on the <code>Validator</code> subtype. */
	public abstract boolean isValid();
	
	/** The value to test. */
	public void setTestValue(String value);
}
