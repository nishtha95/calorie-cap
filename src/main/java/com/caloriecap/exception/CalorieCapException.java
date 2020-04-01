package com.caloriecap.exception;

public class CalorieCapException  extends Exception
{

	private static final long serialVersionUID = -5465086918919378791L;

	public CalorieCapException() {
		super();
	}
	public CalorieCapException(String message, Throwable cause) {
        super(message, cause);
        
    }

    public CalorieCapException(String message) {
        super(message);
        
    }

    public CalorieCapException(Throwable cause) {
        super(cause);
        
    }

}
