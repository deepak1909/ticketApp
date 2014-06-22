package com.redmart.ticket.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.redmart.ticket.dao.TicketDao;
import com.redmart.ticket.domain.CoreObject;
import com.redmart.ticket.domain.Ticket;
import com.redmart.ticket.util.exception.ApplicationException;
import com.redmart.ticket.util.exception.DataException;

/**
 * The Class TicketDaoImpl.
 */
@Repository
public class TicketDaoImpl extends CoreDaoImpl implements TicketDao {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.TicketDao#getAllTicketsCount(java.lang.String)
	 */
	public Long getAllTicketsCount(String searchText) throws ApplicationException{
		try {
			Query query = session.createQuery("SELECT COUNT(t.id) FROM Ticket t");
			Long ticketsCount = (Long)query.uniqueResult();
			return ticketsCount;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE GETTING TICKETS COUNT ",e);
			throw new DataException("ERROR WHILE GETTING TICKETS COUNT ", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.TicketDao#getAllTickets(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Ticket> getAllTickets(Integer limitStart, String sortColumn, String order, String searchText) throws ApplicationException{
		try {
			searchText = "%"+searchText+"%";
			Query query = session.createQuery("SELECT t FROM Ticket t WHERE t.reportedBy.firstName LIKE :reportedBy OR " +
					"t.createdBy.firstName LIKE :createdBy OR t.assignedTo.firstName LIKE :assignedTo ORDER BY "+sortColumn+ " "+order)
					.setParameter("reportedBy", searchText).setParameter("createdBy", searchText).setParameter("assignedTo", searchText).setFirstResult(limitStart).setMaxResults(10);
			List<Ticket> tickets = (List<Ticket>)query.list();
			return tickets;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE LISTING TICKETS ",e);
			throw new DataException("ERROR WHILE LISTING TICKETS ", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.TicketDao#find(java.lang.Long)
	 */
	public Ticket find(Long id) throws ApplicationException{
		try {
			Ticket ticket = (Ticket) session.get(Ticket.class, id);
			return ticket;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE FINDING TICKET ",e);
			throw new DataException("ERROR WHILE FINDING TICKET ", e);
		}
	}

}
