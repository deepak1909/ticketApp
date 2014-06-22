package com.redmart.ticket.util.exception;

/**
 * The Class DataException.
 */
public class DataException extends ApplicationException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new data exception.
     *
     * @param msg the msg
     * @param ex the ex
     */
    public DataException(String msg, Exception exception) {
        super(msg, exception);
    }

}
