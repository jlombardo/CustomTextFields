package edu.wctc.validator.decorator;

import edu.wctc.util.datetime.DateUtilities;
import java.util.Date;
import javax.swing.JTextField;

/**
 * This class represents a decorated JTextField that provides validation for 
 * inputs between a given date range.
 * 
 * @author jlombardo
 */
public class DateRangeDecorator extends JTextFieldValidatorDecorator {
    private JTextField textComponent;
    private String errorMsg;
    private String startRange;
    private String endRange;
    
    /**
     * Custom constructor to decorate a component.
     * 
     * @param textComponent - the object to be decorated. Note that the
     * object's setName method must be called first if the name of the
     * object should be included in any error message.
     * @param startRange - the start of the date range, inclusive. Must be
     * formatted in the short style of java.text.DateFormat.
     * @param endRange - the end of the date range, inclusive. Must be
     * formatted in the short style of java.text.DateFormat.
     */
    public DateRangeDecorator(JTextField textComponent, 
            String startRange, String endRange) {
        
        this.textComponent = textComponent;
        this.startRange = startRange;
        this.endRange = endRange;
        errorMsg = "The field " + textComponent.getName() + 
                " requires a date between " + startRange + 
                " and " + endRange + " inclusive.";
    }
    
    @Override
    public void setText(String value) {
    }

    @Override
    public boolean isValidInput() {
        Date fv = null;
        Date min = null;
        Date max = null;
        
        try {
            fv = DateUtilities.toDate(getText());
            min = DateUtilities.toDate(startRange);
            max = DateUtilities.toDate(endRange);
              
        } catch(Exception e) {
            return false;
        }
        
        if(fv.compareTo(min) >= 0 && fv.compareTo(max) <= 0) {
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
