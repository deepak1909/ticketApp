package com.redmart.ticket.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redmart.ticket.domain.Ticket;
import com.redmart.ticket.service.TicketService;

/**
 * The Class RESTApiController.
 */
@Controller
public class RESTApiController {
	
	/** The ticket service. */
	@Autowired
	private TicketService ticketService;
	
	 /** The Constant APIKEY. */
 	private static final String APIKEY = "M4loiu83umgf9zGZ7yoD67t3A9vzH2Qi";
	
	/**
	 * Adds the ticket.
	 *
	 * @param ticket the ticket
	 * @param result the result
	 * @param apiKey the api key
	 * @param request the request
	 * @return the api response interface
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/api/addTicket", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ApiResponseInterface addTicket(@Valid Ticket ticket, BindingResult result, @RequestHeader(value = "api_key") String apiKey,
            HttpServletRequest request) throws Exception {

        InvalidApiResponse invalidResponse = new InvalidApiResponse();
        SuccessApiResponse successApiResponse = new SuccessApiResponse();

        if (!isValidApiRequest(apiKey)) {
       		invalidResponse.setMessage(InvalidApiResponse.ERR_INVALID_APIKEY);
            return invalidResponse;
        }

        boolean success = ticketService.saveTicket(ticket);
        if(success){
        	return successApiResponse;
        }
		return invalidResponse;
        
    }
	
	/**
	 * Update ticket.
	 *
	 * @param ticket the ticket
	 * @param result the result
	 * @param apiKey the api key
	 * @param request the request
	 * @return the api response interface
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/api/updateTicket", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ApiResponseInterface updateTicket(@Valid Ticket ticket, BindingResult result, @RequestHeader(value = "api_key") String apiKey,
            HttpServletRequest request) throws Exception {

        InvalidApiResponse invalidResponse = new InvalidApiResponse();
        SuccessApiResponse successApiResponse = new SuccessApiResponse();

        if (!isValidApiRequest(apiKey)) {
       		invalidResponse.setMessage(InvalidApiResponse.ERR_INVALID_APIKEY);
            return invalidResponse;
        }

        boolean success = ticketService.updateTicket(ticket);
        if(success){
        	return successApiResponse;
        }
		return invalidResponse;
        
    }

	 /**
 	 * Checks if is valid api request.
 	 *
 	 * @param apiKey the api key
 	 * @return true, if is valid api request
 	 */
 	private boolean isValidApiRequest(String apiKey) {
	        if (StringUtils.isNotBlank(apiKey) && apiKey.equalsIgnoreCase(APIKEY)) {
	            return true;
	        }

	        return false;
	    }
}
