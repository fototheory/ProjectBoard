package com.service;

import com.beans.Status;
import com.dao.StatusJdbcDaoImpl;

/**
 * <code>StatusJdbcServiceImpl</code> implements <code>SpringJdbcService</code>
 * All the services associated with <code>Status</code> are implemented
 * Any controller with <code>Status</code> related activities will instantiate this class
 * This service instantiates <code>StatusJdbcDaoImpl</code> to perform database transactions
 * @author Yumiko Iwai
 * @version 1.0
 */
public class StatusJdbcServiceImpl implements SpringJdbcService<Status> {
	//instantiates StatusJdbcDaoImpl user related database transaction
	StatusJdbcDaoImpl statusJdbcDao = new StatusJdbcDaoImpl();
	//getter/setter for the attribute
	public StatusJdbcDaoImpl getSpringJdbcDao() {
		return statusJdbcDao;
	}

	public void setSpringJdbcDao(StatusJdbcDaoImpl springJdbcDao) {
		this.statusJdbcDao = springJdbcDao;
	}
	/**
	 * generic select by id
	 * @param id status_id of status table
	 * @return returns empty status object if a status_id doesn't have match in database, 
	 * otherwise, returns a status in the list
	 */
	@Override
	public Status selectById(int id) {
		return statusJdbcDao.selectById(id);

	}	
	
	public int selectIdByName(String stat) {
		return statusJdbcDao.selectIdByName(stat);

	}	
}

