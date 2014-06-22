package com.redmart.ticket.rest;


/**
 * This method will be used to construct the API Response body 
 * 
 */
public class InvalidApiResponse implements ApiResponseInterface {
    // initialize the default for the class variables
    boolean status = false;
    String message;
    Object result;

    // initialize the error message for the API response
    public static final String ERR_INVALID_APIKEY = "API key param is missing/invalid.";
    

    /**
     * This method returns the value of status property.
     * 
     * @return status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * This method returns the value of message property.
     * 
     * @return status
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method sets the given value to message property
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * This method returns the value of result property.
     * 
     * @return result
     */
    public Object getResult() {
        return result;
    }

    /**
     * This method sets the given value to result property
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * Convert object to string
     */
    public String toString() {
        return this.message;
    }

}
