package edu.wctc.validator.strategy;

import edu.wctc.util.datetime.DateUtilities;
import java.util.Date;

/**
 * This class is a <code>Validator</code> component used to test the value of 
 * a field against a specified range of values (inclusive).
 * 
 * @author jlombardo
 */
public class RangeValidator implements Validator {
    /** Type safe options */
    public enum ValidatorType {
        NUMBER, DATE, STRING
    }

    private static final String NULL_EMPTY_STR_MSG =
            "String parameters cannot be null or zero length";
    private String testValue;
    private ValidatorType type;
    private String minimumValue;
    private String maxValue;

    /** Default constructor */
    public RangeValidator() {}

    /**
     * Convenience overload allows initializing all properties.
     * @param type - a type safe enumerated constant indicating the type of
     * values to test.
     * @param minimumValue - the current field value to test. If fieldValue
     * does not match the data type being tested, isValid() will be false.
     * @param maxValue - the current field value to test. If fieldValue
     * does not match the data type being tested, isValid() will be false.
     */
    public RangeValidator(ValidatorType type, String minimumValue, String maxValue) {
        this.type = type;
        this.minimumValue = minimumValue;
        this.maxValue = maxValue;
    }

    /**
     * Sets the type of values that will be tested.
     * @param type - a type safe enumerated constant indicating the type of
     * values to test.
     */
    public void setType(ValidatorType type) {
            this.type = type;
    }

    /**
     * Use to store the current value of the field for testing.
     * @param value - the field value to be tested
     */
    public void setTestValue(String value) {
        testValue = value;
    }

    /**
     * Tests whether the current field value is within a specified range (inclusive).
     * All comparisons are based on Java natural order specifications.
     * @return result of test
     */
    public boolean isValid() {
        boolean testResult = false;

        if(testValue == null || testValue.length() == 0) return testResult;

        switch(type) {
                // Leave this alone
                case NUMBER:
                        try {
                                double fv = Double.parseDouble(testValue);
                                double min = Double.parseDouble(minimumValue);
                                double max = Double.parseDouble(maxValue);
                                if(fv >= min && fv <= max) {
                                        testResult = true;
                                }
                        } catch(Exception e) {
                                testResult = false;
                        }
                        break;

                ////////////////////////////////////////////////////////////////
                // MODIFY THIS CASE STATEMENT to implement your own test logic
                ////////////////////////////////////////////////////////////////
                case DATE:
                        try {
                                Date fv = DateUtilities.toDate(testValue);
                                Date min = DateUtilities.toDate(minimumValue);
                                Date max = DateUtilities.toDate(maxValue);
                                if(fv.compareTo(min) >= 0 && fv.compareTo(max) <= 0) {
                                        testResult = true;
                                }
                        } catch(Exception e) {
                                testResult = false;
                        }
                        break;

                // Leave this alone
                case STRING:
                        if(testValue.compareTo(minimumValue) >= 0
                                        && testValue.compareTo(maxValue) <= 0) {
                                testResult = true;
                        }
                        break;
                default:
                        testResult = false;
        } // end switch

        return testResult;
    }
}
