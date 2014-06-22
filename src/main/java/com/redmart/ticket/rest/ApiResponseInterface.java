package com.redmart.ticket.rest;

/**
 * The Interface ApiResponseInterface.
 */
public interface ApiResponseInterface {
    /**
     * This method returns the value of status property.
     * 
     * @return status
     */
    public boolean isStatus();

    /**
     * This method returns the value of message property.
     * 
     * @return status
     */
    public String getMessage();

    /**
     * This method sets the given value to message property.
     *
     * @param message the new message
     */
    public void setMessage(String message);

    /**
     * This method returns the value of result property.
     * 
     * @return result
     */
    public Object getResult();

    /**
     * This method sets the given value to result property.
     *
     * @param result the new result
     */
    public void setResult(Object result);
}
