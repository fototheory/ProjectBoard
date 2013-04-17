package com.service;

import com.beans.Role;
import com.dao.RoleJdbcDaoImpl;

/**
 * <code>RoleJdbcServiceImpl</code> implements <code>SpringJdbcService</code>
 * All the services associated with <code>Role</code> are implemented
 * Any controller with <code>Role</code> related activities will instantiate this class
 * This service instantiates <code>RoleJdbcDaoImpl</code> to perform database transactions
 * @author Yumiko Iwai
 * @version 1.0
 */
public class RoleJdbcServiceImpl implements SpringJdbcService<Role> {
	//instantiates RoleJdbcDaoImpl user related database transaction
	RoleJdbcDaoImpl roleJdbcDao = new RoleJdbcDaoImpl();
	//getter/setter for the attribute
	public RoleJdbcDaoImpl getSpringJdbcDao() {
		return roleJdbcDao;
	}

	public void setSpringJdbcDao(RoleJdbcDaoImpl springJdbcDao) {
		this.roleJdbcDao = springJdbcDao;
	}
	/**
	 * generic select by id
	 * @param id role_id of role table
	 * @return returns empty role object if a role_id doesn't have match in database, 
	 * otherwise, returns a role in the list
	 */
	@Override
	public Role selectById(int id) {
		return roleJdbcDao.selectById(id);

	}	
}
