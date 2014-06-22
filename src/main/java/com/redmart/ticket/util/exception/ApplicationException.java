package com.redmart.ticket.util.exception;

/**
 * The Class ApplicationException.
 */
public class ApplicationException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The exception. */
    private Exception exception;
    
    /** The msg. */
    private String msg;

    /**
     * Instantiates a new application exception.
     *
     * @param msg the msg
     * @param exception the exception
     */
    public ApplicationException(String msg, Exception exception) {
        this.exception = exception;
        this.msg = msg;
    }

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * Sets the exception.
	 *
	 * @param exception the new exception
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the msg.
	 *
	 * @param msg the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
