package com.redmart.ticket.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.redmart.ticket.dao.LoginDao;
import com.redmart.ticket.domain.User;
import com.redmart.ticket.util.exception.ApplicationException;
import com.redmart.ticket.util.exception.DataException;

/**
 * The Class LoginDaoImpl.
 */
@Repository
public class LoginDaoImpl extends CoreDaoImpl implements LoginDao {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.redmart.ticket.dao.LoginDao#authenticate(java.lang.String, java.lang.String)
	 */
	public User authenticate(String userId, String password) throws ApplicationException {
		
		try {
			Query query = session
					.createQuery("SELECT u FROM User u WHERE u.deleteFlag = 0 AND u.email = :email and u.password = :password")
					.setParameter("email", userId).setParameter("password", password);
			User user = (User) query.uniqueResult();
			return user;
		} catch (HibernateException e) {
			LOGGER.error("ERROR WHILE AUTHENTICATING ",e);
			throw new DataException("ERROR WHILE AUTHENTICATING", e);
		}
	}

}
