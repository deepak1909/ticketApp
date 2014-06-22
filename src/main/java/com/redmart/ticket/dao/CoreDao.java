package com.redmart.ticket.dao;

import com.redmart.ticket.domain.CoreObject;
import com.redmart.ticket.util.exception.ApplicationException;

/**
 * The Interface CoreDao.
 */
public interface CoreDao {
	
	/**
	 * Save.
	 *
	 * @param coreObject the core object
	 * @return true, if successful
	 * @throws ApplicationException the application exception
	 */
	public boolean save(CoreObject coreObject) throws ApplicationException;
	
	/**
	 * Update.
	 *
	 * @param coreObject the core object
	 * @return true, if successful
	 * @throws ApplicationException the application exception
	 */
	public boolean update(CoreObject coreObject) throws ApplicationException;
	
}
