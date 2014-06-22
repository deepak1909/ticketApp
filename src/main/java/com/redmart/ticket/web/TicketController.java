package com.redmart.ticket.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.redmart.ticket.domain.Ticket;
import com.redmart.ticket.service.TicketService;
import com.redmart.ticket.service.UserService;
import com.redmart.ticket.util.exception.ApplicationException;
import com.redmart.ticket.vo.DataTableResponse;

/**
 * The Class TicketController.
 */
@Controller
public class TicketController {

	/** The ticket service. */
	@Autowired
	private TicketService ticketService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);


	/**
	 * Dashboard.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "ticket/dashboard", method = RequestMethod.GET)
	public String dashboard(ModelMap model) {

		List<Ticket> tickets = new ArrayList<Ticket>();
		LOGGER.info("LISTING ALL TICKETS");
		model.addAttribute("tickets", tickets);
		return "ticket/list";

	}

	/**
	 * Gets the all tickets.
	 *
	 * @param pageNo the page no
	 * @param limitStart the limit start
	 * @param displayLength the display length
	 * @param sortColumn the sort column
	 * @param order the order
	 * @param searchText the search text
	 * @param request the request
	 * @param response the response
	 * @return the all tickets
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "ticket/dashboard/load", method = RequestMethod.POST)
	public @ResponseBody
	DataTableResponse<Object> getAllTickets(@RequestParam("sEcho") int pageNo, @RequestParam("iDisplayStart") int limitStart,
			@RequestParam("iDisplayLength") int displayLength, @RequestParam("iSortCol_0") String sortColumn,
			@RequestParam("sSortDir_0") String order, @RequestParam("sSearch") String searchText, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String applicationContext = request.getSession().getServletContext().getContextPath();

		// Define the Database Column Names needed for Sorting
		List<String> sortColumnsList = new ArrayList<String>();
		sortColumnsList.add("id");
		sortColumnsList.add("reportedBy");
		sortColumnsList.add("createdBy");
		sortColumnsList.add("assignedTo");
		sortColumnsList.add("status");

		sortColumn = sortColumnsList.get(Integer.parseInt(sortColumn));
		Long ticketsCount = ticketService.getAllTicketsCount(searchText);
		int totalPages = (ticketsCount.intValue() + displayLength - 1) / displayLength;

		if ((displayLength + limitStart) > ticketsCount) {
			displayLength = ticketsCount.intValue() - limitStart;
		}
		List<Ticket> tickets = ticketService.getAllTickets(limitStart, sortColumn, order, searchText);

		List<Object> resultDataList = new ArrayList<Object>();
		for (Ticket ticket : tickets) {
			List<Object> rowObject = new ArrayList<Object>();
			rowObject.add("TA-" + ticket.getId());
			rowObject.add(ticket.getReportedBy().getFirstName());
			rowObject.add(ticket.getCreatedBy().getFirstName());
			rowObject.add(ticket.getAssignedTo().getFirstName());
			rowObject.add(ticket.getStatus());
			if (ticket.getStatus().equals("CLOSED")) {
				rowObject.add("<a href='" + applicationContext + "/ticket/edit/" + ticket.getId() + "'>View Ticket</a>");
			} else {
				rowObject.add("<a href='" + applicationContext + "/ticket/edit/" + ticket.getId() + "'>Edit Ticket</a>");
			}
			resultDataList.add(rowObject);
		}

		// Construct the DataTableResponse Object
		DataTableResponse<Object> DataTableResponseObject = new DataTableResponse<Object>();
		DataTableResponseObject.setTotalRecords(ticketsCount);
		DataTableResponseObject.setTotalDisplayRecords(ticketsCount);
		DataTableResponseObject.setCurrentPageNumber(pageNo);
		DataTableResponseObject.setResultData(resultDataList);

		/**
		 * Send the JSON response Due to the presence of '@ResponseBody' in the function definition, Jackson libray will
		 * automatically convert this object to a json string
		 */
		return DataTableResponseObject;
	}

	/**
	 * Adds the ticket.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "ticket/add", method = RequestMethod.GET)
	public String addTicketPage(ModelMap model) {

		model.addAttribute("ticket", new Ticket());
		return "ticket/add";

	}

	/**
	 * Adds the ticket.
	 *
	 * @param ticket the ticket
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "ticket/save", method = RequestMethod.POST)
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult result, ModelMap model) {

		try {
			ticketService.saveTicket(ticket);
			return "redirect:/ticket/dashboard";
		} catch (ApplicationException e) {
			model.addAttribute("error", e.getMsg());
			return "error/error";
		}

	}
	
	/**
	 * Adds the ticket.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "ticket/edit/{id}", method = RequestMethod.GET)
	public String editTicketPage(@PathVariable("id")Long id, ModelMap model) {

		try {
			Ticket ticket = ticketService.getTicket(id);
			model.addAttribute("ticket", ticket);
			return "ticket/edit";
		} catch (ApplicationException e) {
			model.addAttribute("error", e.getMsg());
			return "error/error";
		}

	}
	
	/**
	 * Update ticket.
	 *
	 * @param ticket the ticket
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "ticket/update", method = RequestMethod.POST)
	public String updateTicket(@ModelAttribute("ticket") Ticket ticket, BindingResult result, ModelMap model) {

		try {
			ticketService.updateTicket(ticket);
			return "redirect:/ticket/dashboard";
		} catch (ApplicationException e) {
			model.addAttribute("error", e.getMsg());
			return "error/error";
		}

	}

	/**
	 * Gets the autocomplete list.
	 *
	 * @param searchText the search text
	 * @param lookupType the lookup type
	 * @param request the request
	 * @return the autocomplete
	 */
	@RequestMapping(value = "/autoComplete", method = RequestMethod.GET)
	@ResponseBody
	public List getAutocomplete(@RequestParam(value = "searchText") String searchText,
			@RequestParam(value = "lookupType") String lookupType, final HttpServletRequest request) {
		List list = null;
		if (lookupType.equals("CUSTOMER")) {
			list = userService.getCustomers(searchText);
		} else if (lookupType.equals("USER")) {
			list = userService.getUsers(searchText);
		}
		return list;
	}

}
