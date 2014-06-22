package com.redmart.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redmart.ticket.dao.LoginDao;
import com.redmart.ticket.domain.User;
import com.redmart.ticket.util.exception.ApplicationException;

/**
 * The Class LoginService.
 */
@Service
public class LoginService {

	
	/** The login dao. */
	@Autowired
	private LoginDao loginDao;
	
	/**
	 * Authenticate.
	 *
	 * @param userId the user id
	 * @param password the password
	 * @return the user
	 * @throws ApplicationException the application exception
	 */
	public User authenticate(String userId, String password) throws ApplicationException {
		
		User user = loginDao.authenticate(userId, password);
		return user;
	}
}
