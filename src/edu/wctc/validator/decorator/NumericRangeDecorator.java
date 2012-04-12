package edu.wctc.validator.decorator;

import javax.swing.JTextField;

/**
 * This class represents a decorated JTextField that provides validation for 
 * inputs between a given numeric range.
 * 
 * @author jlombardo
 */
public class NumericRangeDecorator extends JTextFieldValidatorDecorator {
    private JTextField textComponent;
    private String errorMsg;
    private int startRange;
    private int endRange;
    private boolean optional;
    
    /**
     * Custom constructor to decorate a component.
     * 
     * @param textComponent - the object to be decorated. Note that the
     * object's setName method must be called first if the name of the
     * object should be included in any error message.
     * @param startRange - the start of the numeric range, inclusive.
     * @param endRange - the end of the numeric range, inclusive.
     * @param optional - if true, validation only occurs with input
     */
    public NumericRangeDecorator(JTextField textComponent, 
            int startRange, int endRange, boolean optional) {
        
        this.textComponent = textComponent;
        this.startRange = startRange;
        this.endRange = endRange;
        this.optional = optional;
        
        errorMsg = "The field " + textComponent.getName() + 
                " requires a whole number between 20 and 50 inclusive.";
    }

    /**
     * Gets the validation status based on the range constraints. If the optional
     * flag is true and there is no input the validation is skipped and 
     * true is returned; else, numeric constraints are verified. 
     * 
     * @return the validation status
     */
    @Override
    public boolean isValidInput() {
        int number = 0;

        try {
            if(optional && getText().length() == 0) {
                return true;
            } else {
                number = Integer.parseInt(getText());  
            }
        } catch(RuntimeException e) {
            return false;
        }
        
        if(number >= startRange && number <= endRange) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
    
    
}
