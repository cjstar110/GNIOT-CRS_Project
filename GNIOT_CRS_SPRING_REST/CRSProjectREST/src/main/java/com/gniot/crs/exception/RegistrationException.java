package com.gniot.crs.exception;

public class RegistrationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
