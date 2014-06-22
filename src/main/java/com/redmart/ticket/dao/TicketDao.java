package com.redmart.ticket.dao;

import java.util.List;

import com.redmart.ticket.domain.Ticket;
import com.redmart.ticket.util.exception.ApplicationException;

/**
 * The Interface TicketDao.
 */
public interface TicketDao extends CoreDao {

	/**
	 * Gets the all tickets.
	 *
	 * @param limitStart the limit start
	 * @param sortColumn the sort column
	 * @param order the order
	 * @param searchText the search text
	 * @return the all tickets
	 * @throws ApplicationException the application exception
	 */
	public List<Ticket> getAllTickets(Integer limitStart, String sortColumn, String order, String searchText) throws ApplicationException;

	/**
	 * Gets the all tickets count.
	 *
	 * @param searchText the search text
	 * @return the all tickets count
	 * @throws ApplicationException the application exception
	 */
	public Long getAllTicketsCount(String searchText) throws ApplicationException;
	
	/**
	 * Find.
	 *
	 * @param id the id
	 * @return the ticket
	 * @throws ApplicationException the application exception
	 */
	public Ticket find(Long id) throws ApplicationException;

}
