package com.redmart.ticket.vo;
/**
 * File to handle the response sent to jquery DataTables.
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an interface to Jquery DataTables in our Java system.
 * 
 * This class returns the JSON response to Jquery DataTables.
 */
@SuppressWarnings("serial")
public class DataTableResponse<T> implements Serializable {

	 /**
     * @JsonProperty is an annotation type in jackson (java library for json parsing) This annotation maps a json
     *               request param to corresponding class param
     */

    public Long iTotalRecords;

    public Long iTotalDisplayRecords;

    public int pageNumber;

    public List<T> aaData = new ArrayList<T>();

    /**
     * Public constructor.
     */
    public DataTableResponse() {
    }

    /**
     * Function to set value of iTotalRecords property.
     * 
     * @param int totalRecords total records found in data table response
     * 
     * @return void
     */
    public void setTotalRecords(Long totalRecords) {
        this.iTotalRecords = totalRecords;
    }

    /**
     * Function to set value of iTotalDisplayRecords property.
     * 
     * @param int totalDisplayRecords total records to display in data table response
     * 
     * @return void
     */
    public void setTotalDisplayRecords(Long totalDisplayRecords) {
        this.iTotalDisplayRecords = totalDisplayRecords;
    }

    /**
     * Function to set value of iTotalRecords sEcho.
     * 
     * @param int pageNumber current page number
     * 
     * @return void
     */
    public void setCurrentPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Function to set value of aaData property.
     * 
     * @param List<Object> resultData set the result data in data table response
     * 
     * @return void
     */
    public void setResultData(List<T> resultDataList) {
        this.aaData = resultDataList;
    }
}

