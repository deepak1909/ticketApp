package com.redmart.ticket.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.redmart.ticket.dao.CoreDao;
import com.redmart.ticket.domain.CoreObject;
import com.redmart.ticket.util.exception.ApplicationException;
import com.redmart.ticket.util.exception.DataException;
import com.redmart.ticket.web.TicketController;

/**
 * The Class CoreDaoImpl.
 */
public abstract class CoreDaoImpl implements CoreDao {
	
	/** The session. */
	protected Session session;
	
	/** The session factory. */
	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * Inits the.
	 *
	 * @param factory the factory
	 */
	@Autowired
	public void init(SessionFactory factory) {
		this.session = sessionFactory.openSession();
	}
	
	/** The jdbc template. */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CoreDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.CoreDao#save(com.redmart.ticket.domain.CoreObject)
	 */
	public boolean save(CoreObject coreObject) throws ApplicationException{
		try {
			session.save(coreObject);
			session.flush();
			session.clear();
			return true;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE SAVING ",e);
			throw new DataException("ERROR WHILE SAVING", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.CoreDao#update(com.redmart.ticket.domain.CoreObject)
	 */
	public boolean update(CoreObject coreObject) throws ApplicationException{
		try {
			session.merge(coreObject);
			session.flush();
			session.clear();
			return true;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE UPDATING ",e);
			throw new DataException("ERROR WHILE UPDATING", e);
		}
		
	}
	
}
