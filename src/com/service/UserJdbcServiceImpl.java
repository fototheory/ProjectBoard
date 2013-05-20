package com.service;

import java.util.List;
import com.beans.User;
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
		return userJdbcDao.getUserByEmailAndPassword(email, password);
	}
	
	/**
	 * check session user's authenticity 
	 * @param id user_id of session variable
	 * @return returns count of user found
	 */
	public int sessionCheck(int id) {
		//get user with matching email and password
		return userJdbcDao.countById(id);
	}
	
	/**
	 * Get the user's id by email
	 * @param email the user's email
	 * @return returns the user_id for the user with the given email
	 */
	public int getIdByEmail(String email){
		return userJdbcDao.getIdByEmail(email);
	}
	
	/**
	 * Insert a user into the database
	 * @param user a user object to be inserted into the database
	 * @return returns the number of records inserted into the database
	 */
	public int addNewUser(User user) {
		return userJdbcDao.insert(user);
	}
	
	/**
	 * Get a user by their email address
	 * @param email the email of the user
	 * @return returns a user given their email address
	 */
	public User getUserByEmail(String email) {
		return userJdbcDao.getUserByEmail(email);
	}
	
	/**
	 * Get the user's lead faculty's id given their discipline id
	 * @param dispId the discipline id for the lead faculty of that discipline
	 * @return returns the lead faculty's id for the given discipline id
	 */
	public int getLeadId(int dispId) {
		return userJdbcDao.getLeadId(dispId);
	}
	
	/**
	 * Update the user's profile id (used as a pointer to the user's profile in the profile table)
	 * @param user the user to be updated
	 * @param profileId the user's profile id
	 * @return returns the number of records affected by the update
	 */
	public int updateProfileId(User user, int profileId) {
		return userJdbcDao.updateProfileId(user, profileId);
	}
	
	/**
	 * Update a user's attributes
	 * @param user the user to be updated
	 * @return returns the number of records affected by the update
	 */
	public int updateUser(User user) {
		return userJdbcDao.update(user);
	}
	
	public int updateUserWithCond(User user, String type) {
		return userJdbcDao.updateWithCond(user, type);
	}
	
	/**
	 * Delete a user record by their user_id
	 * @param userId the user id of the user to be deleted
	 * @return returns the number of records affected by the deletion
	 */
	public int deleteUser(int userId) {
		return userJdbcDao.deleteById(userId);
	}

	/**
	 * Gets a list of all users
	 * @return returns a list of all users in the database
	 */
	public List<User> getAllUsers() {
		return userJdbcDao.selectAllUsers();
	}

	/**
	 * Get a a list of all unverified users
	 * @return returns a list of all unverified users in the database
	 */
	public List<User> getUnVerifiedUsers() {
		return userJdbcDao.selectUnVerifiedUsers();
	}
	
	/**
	 * Verify the user so that they can access the system
	 * @param user the user to be verified
	 * @return the number of records affected by the verification
	 */
	public int verifyUser(User user) {
		return userJdbcDao.verifyUser(user);
	}
	
	/**
	 * Get a list of users by their role
	 * @param roleName the name of the role
	 * @return a list of users
	 */
	public List<User> getUsersByRole(String roleName) {
		return userJdbcDao.getUsersByRole(roleName);
	}
	
	/**
	 * Get the last user id number
	 * @return returns the user_id of the last user in the table
	 */
	public int getLastUserId() {
		return userJdbcDao.getLastUserId();
	}
}
