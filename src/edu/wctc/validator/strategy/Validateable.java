package edu.wctc.validator.strategy;

/**
 * A general contract for making objects validateable.
 * @author jlombardo
 *
 */
public interface Validateable {
	public abstract void setValidator(Validator component);
	
	public abstract Validator getValidator();
	
	public abstract void setErrorMsg(String msg);
	
	public abstract String getErrorMsg();
}
