package com.redmart.ticket.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.redmart.ticket.dao.TicketDao;
import com.redmart.ticket.dao.UserDao;
import com.redmart.ticket.domain.Ticket;
import com.redmart.ticket.util.exception.ApplicationException;

/**
 * The Class TicketService.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TicketService {

	/** The ticket dao. */
	@Autowired
	private TicketDao ticketDao;

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/**
	 * Gets the all tickets.
	 * 
	 * @param limitStart
	 *            the limit start
	 * @param sortColumn
	 *            the sort column
	 * @param order
	 *            the order
	 * @param searchText
	 *            the search text
	 * @return the all tickets
	 * @throws ApplicationException
	 *             the application exception
	 */
	public List<Ticket> getAllTickets(Integer limitStart, String sortColumn, String order, String searchText) throws ApplicationException {
		return ticketDao.getAllTickets(limitStart, sortColumn, order, searchText);
	}

	/**
	 * Gets the all tickets count.
	 * 
	 * @param searchText
	 *            the search text
	 * @return the all tickets count
	 * @throws ApplicationException
	 *             the application exception
	 */
	public Long getAllTicketsCount(String searchText) throws ApplicationException {
		return ticketDao.getAllTicketsCount(searchText);
	}

	/**
	 * Save Ticket.
	 * 
	 * @param ticket
	 *            the ticket
	 * @return true, if successful
	 * @throws ApplicationException
	 *             the application exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean saveTicket(Ticket ticket) throws ApplicationException {

		ticket.setCreatedDate(new Date());

		// Saving Customer details
		if (ticket.getReportedBy().getId() == null) {
			ticket.getReportedBy().setCreatedBy(ticket.getCreatedBy());
			ticket.getReportedBy().setCreatedDate(new Date());
			userDao.save(ticket.getReportedBy());
		}

		// If ticket is not assigned, then set status to NEW
		if (ticket.getAssignedTo().getId() == null) {
			ticket.setAssignedTo(ticket.getCreatedBy());
			ticket.setStatus("NEW");
		} else {
			ticket.setStatus("OPEN");
		}
		return ticketDao.save(ticket);

	}

	/**
	 * Update Ticket.
	 * 
	 * @param ticket
	 *            the ticket
	 * @return true, if successful
	 * @throws ApplicationException
	 *             the application exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateTicket(Ticket ticket) throws ApplicationException {
		ticket.getReportedBy().setUpdatedBy(ticket.getUpdatedBy());
		ticket.getReportedBy().setUpdatedDate(new Date());
		userDao.update(ticket.getReportedBy());
		ticket.setUpdatedDate(new Date());
		return ticketDao.update(ticket);

	}

	/**
	 * Gets the ticket.
	 * 
	 * @param id
	 *            the id
	 * @return the ticket
	 * @throws ApplicationException
	 *             the application exception
	 */
	public Ticket getTicket(Long id) throws ApplicationException {
		return (Ticket) ticketDao.find(id);
	}

}
