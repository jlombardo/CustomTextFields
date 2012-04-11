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
    
    /**
     * Custom constructor to decorate a component.
     * 
     * @param textComponent - the object to be decorated. Note that the
     * object's setName method must be called first if the name of the
     * object should be included in any error message.
     * @param startRange - the start of the numeric range, inclusive.
     * @param endRange - the end of the numeric range, inclusive.
     */
    public NumericRangeDecorator(JTextField textComponent, 
            int startRange, int endRange) {
        
        this.textComponent = textComponent;
        this.startRange = startRange;
        this.endRange = endRange;
        errorMsg = "The field " + textComponent.getName() + 
                " requires a whole number between 20 and 50 inclusive.";
    }

    @Override
    public boolean isValidInput() {
        int number = 0;

        /*
            * By doing this we make NumericRange validation optional -- it
            * will only kick in if there is input. However, if we want 
            * required field validation in addition to numeric range, we 
            * can decorate another decorator -- the RequiredField decorator.
            */
        if(((JTextFieldValidatorDecorator)textComponent).isValidInput()
                && getText().length() == 0) {
            return true;
        }
//        if(!(textComponent.getClass()
//                .getSimpleName().equals("RequiredFieldDecorator"))
//                && getText().length() == 0) {
//
//            return true; // exit method immediately
//        }
        
        try {
            number = Integer.parseInt(getText());   
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
