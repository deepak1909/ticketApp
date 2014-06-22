package com.redmart.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redmart.ticket.dao.UserDao;
import com.redmart.ticket.domain.User;
import com.redmart.ticket.util.exception.ApplicationException;

/**
 * The Class UserService.
 */
@Service
public class UserService {

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/**
	 * Gets the all users.
	 * 
	 * @return the all users
	 * @throws ApplicationException
	 *             the application exception
	 */
	public List<User> getAllUsers() throws ApplicationException {
		return userDao.getAllUsers();
	}

	/**
	 * Gets the all users count.
	 * 
	 * @return the all users count
	 * @throws ApplicationException
	 *             the application exception
	 */
	public Long getAllUsersCount() throws ApplicationException {
		return userDao.getAllUsersCount();

	}

	/**
	 * Gets the customers.
	 * 
	 * @param searchText
	 *            the search text
	 * @return the customers
	 */
	public List getCustomers(String searchText) {
		return userDao.getCustomers(searchText);
	}

	/**
	 * Gets the users.
	 * 
	 * @param searchText
	 *            the search text
	 * @return the users
	 */
	public List getUsers(String searchText) {
		return userDao.getUsers(searchText);
	}

}
