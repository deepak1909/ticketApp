package com.redmart.ticket.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.redmart.ticket.dao.UserDao;
import com.redmart.ticket.domain.User;
import com.redmart.ticket.util.exception.ApplicationException;
import com.redmart.ticket.util.exception.DataException;

/**
 * The Class UserDaoImpl.
 */
@Repository
public class UserDaoImpl extends CoreDaoImpl implements UserDao {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.UserDao#getAllUsers()
	 */
	public List<User> getAllUsers() throws ApplicationException{
		
		try {
			Query query = session.createQuery("SELECT u FROM User WHERE u.deleteFlag = 0");
			List<User> users = (List<User>) query.list();
			return users;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE LISTING USERS ",e);
			throw new DataException("ERROR WHILE LISTING USERS ", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.UserDao#getAllUsersCount()
	 */
	public Long getAllUsersCount() throws ApplicationException{
		
		try {
			Query query = session.createQuery("SELECT count(u.id) FROM User WHERE u.deleteFlag = 0");
			Long userCount = (Long)query.uniqueResult();
			return userCount;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE GETTING USERS COUNT ",e);
			throw new DataException("ERROR WHILE GETTING USERS COUNT ", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.UserDao#getCustomers(java.lang.String)
	 */
	public List getCustomers(String searchText) {
		
		String jdbcQuery = "SELECT TOP 10 id id, email value, concat(email, ' (', firstName,' ',lastName,')') label, firstName, lastName, phone, address1, address2, city, state, zip FROM customer WHERE email like '%"+searchText+"%'";
		List customers = jdbcTemplate.queryForList(jdbcQuery);
		return customers;
		
	}
	
	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.UserDao#getUsers(java.lang.String)
	 */
	public List getUsers(String searchText) {
		
		String jdbcQuery = "SELECT TOP 10 id id, email value, concat(email, ' (', firstName,' ',lastName,')') label FROM users WHERE email like '%"+searchText+"%'";
		List customers = jdbcTemplate.queryForList(jdbcQuery);
		return customers;
		
	}

}
