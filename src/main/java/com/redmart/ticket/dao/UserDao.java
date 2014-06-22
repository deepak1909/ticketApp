package com.redmart.ticket.dao;

import java.util.List;

import com.redmart.ticket.domain.User;
import com.redmart.ticket.util.exception.ApplicationException;

/**
 * The Interface UserDao.
 */
public interface UserDao extends CoreDao {

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 * @throws ApplicationException the application exception
	 */
	public List<User> getAllUsers() throws ApplicationException;
	
	/**
	 * Gets the all users count.
	 *
	 * @return the all users count
	 * @throws ApplicationException the application exception
	 */
	public Long getAllUsersCount() throws ApplicationException;

	/**
	 * Gets the customers.
	 *
	 * @param searchText the search text
	 * @return the customers
	 */
	public List getCustomers(String searchText);
	
	/**
	 * Gets the users.
	 *
	 * @param searchText the search text
	 * @return the users
	 */
	public List getUsers(String searchText);

}
