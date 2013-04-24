package com.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beans.User;
import com.dao.StatusJdbcDaoImpl;
import com.dao.UserJdbcDaoImpl;

/**
 * <code>UserJdbcServiceImpl</code> implements <code>SpringJdbcService</code>
 * All the services associated with <code>User</code> are implemented
 * Any controller with <code>User</code> related activities will instantiate this class
 * This service instantiates <code>UserJdbcDaoImpl</code> to perform database transactions
 * @author Yumiko Iwai
 * @version 1.0
 */
public class UserJdbcServiceImpl implements SpringJdbcService<User> {
	protected final Log logger = LogFactory.getLog(getClass());

	//instantiates UserJdbcDaoImpl user related database transaction
	UserJdbcDaoImpl userJdbcDao = new UserJdbcDaoImpl();
	//getter/setter for the attribute
	public UserJdbcDaoImpl getSpringJdbcDao() {
		return userJdbcDao;
	}

	public void setSpringJdbcDao(UserJdbcDaoImpl springJdbcDao) {
		this.userJdbcDao = springJdbcDao;
	}
    /**
	 * generic select by id
	 * @param id user_id of user table
	 * @return returns empty user object if a user hasn't logged in, 
	 * otherwise, returns a user in the list
	 */
	@Override
	public User selectById(int id) {
		//select user by user_id
		return userJdbcDao.selectById(id);
	}
    /**
	 * check user's authenticity
	 * @param email is passed from <code>LoginController</code> through login.jsp
	 * @param password is passed from <code>LoginController</code> through login.jsp
	 * @return returns empty user object if a user hasn't logged in, 
	 * otherwise, returns a user in the list
	 */
	public User loginCheck(String email, String password) {
		//get user with matching email and password
		return userJdbcDao.loginCheck(email, password);
	}
	/**
	 * check session user's authenticity 
	 * @param id user_id of session variable
	 * @return returns count of user found
	 */
	public int sessoionCheck(int id) {
		//get user with matching email and password
		return userJdbcDao.countById(id);
	}
	
	public int getIdByEmail(String email){
		return userJdbcDao.getIdByEmail(email);
	}
	
	public void addNewUser(User user) {
		userJdbcDao.addNewUser(user);
	}
	
	public User getUserByEmail(String email) {
		return userJdbcDao.getUserByEmail(email);
	}
	
	public int getLeadId(int dispId) {
		return userJdbcDao.getLeadId(dispId);
	}
}
