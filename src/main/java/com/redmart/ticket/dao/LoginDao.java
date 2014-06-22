package com.redmart.ticket.dao;

import com.redmart.ticket.domain.User;
import com.redmart.ticket.util.exception.ApplicationException;

/**
 * The Interface LoginDao.
 */
public interface LoginDao {

	/**
	 * Authenticate.
	 *
	 * @param userId the user id
	 * @param password the password
	 * @return the user
	 * @throws ApplicationException the application exception
	 */
	public User authenticate(String userId, String password) throws ApplicationException;

}
