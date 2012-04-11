package edu.wctc.validator.strategy;

/**
 * This class is a <code>Validator</code> component used to test a field 
 * for required input.
 * 
 * @author jlombardo
 */
public class RequiredFieldValidator implements Validator {
	private String fieldValue;
	
	/** Default constructor */
	public RequiredFieldValidator() {}
		
	/**
	 * Use to store the current value of the field for testing.
	 * @param value - the field value to be tested
	 */
	public void setTestValue(String value) {
		if(value != null && value.length() > 0) {
			fieldValue = value;
		}
	}
	
	/**
	 * Tests whether the current field has content.
	 * @return true if the current field has content; false otherwise
	 */
	public boolean isValid() {
		boolean testResult = false;
		
		if(fieldValue != null && fieldValue.length() > 0) {
			testResult = true;
		}
		return testResult;
	}
}
