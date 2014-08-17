package org.seventyeight.automation;

public class AutomationException extends Exception {
	public AutomationException(String msg, Exception e) {
		super(msg, e);
	}
	
	public AutomationException(Exception e) {
		super(e);
	}
}
