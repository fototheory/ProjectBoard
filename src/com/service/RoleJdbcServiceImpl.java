package com.service;

import com.beans.Role;
import com.dao.RoleJdbcDaoImpl;
import java.util.List;

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
	 * Select a role by its id
	 * @param id a role id
	 * @return returns empty role object if a role_id doesn't have match in database, 
	 * otherwise, returns a role record
	 */
	@Override
	public Role selectById(int id) {
		return roleJdbcDao.selectById(id);
	}
	
	/**
	 * Select all roles
	 * @return returns a list of roles 
	 */
	public List<Role> selectAllRoles() {
		return roleJdbcDao.selectAllRoles();
	}
	
	/**
	 * Select all roles except the admin role
	 * @return a list of all roles except the admin role
	 */
	public List<Role> selectAllRolesExceptAdmin() {
		return roleJdbcDao.selectAllRolesExceptAdmin();
	}

	/**
	 * Insert a role
	 * @param role a Role object
	 * @return int
	 */
	public int insert(Role role) {
		return roleJdbcDao.insert(role);
	}
	
	/**
	 * Update a role with a role object
	 * @param role a Role object
	 * @return returns the number of records affected by the update
	 */
	public int update(Role role) {
		return roleJdbcDao.update(role);
	}
	
	/**
	 * Delete a role by the role id
	 * @param roleID the role's id number
	 * @return returns the number of records deleted
	 */
	public int delete(int roleId) {
		return roleJdbcDao.deleteById(roleId);
	}
	
	/**
	 * Get the last role's id number
	 * @return returns the role_id of the last role in the table
	 */
	public int getLastId() {
		return roleJdbcDao.getLastRoleId();
	}
	
}
