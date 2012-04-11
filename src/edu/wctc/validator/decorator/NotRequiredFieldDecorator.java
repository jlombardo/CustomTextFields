package edu.wctc.validator.decorator;

import javax.swing.JTextField;

/**
 * This class represents a decorated JTextField that provides validation for 
 * inputs that are required.
 * 
 * @author jlombardo
 */
public class NotRequiredFieldDecorator extends JTextFieldValidatorDecorator {
    private JTextField textComponent;
    private String errorMsg;
    
    /**
     * Custom constructor to decorate a component.
     * 
     * @param textComponent - the object to be decorated. Note that the
     * object's setName method must be called first if the name of the
     * object should be included in any error message.
     */
    public NotRequiredFieldDecorator(JTextField textComponent) {
        this.textComponent = textComponent;
        errorMsg = "The field " + textComponent.getName() + " is required.";
    }
 
    @Override
    public boolean isValidInput() {
        return true;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
