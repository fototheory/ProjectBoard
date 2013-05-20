package com.service;

import com.beans.Profile;
import com.dao.ProfileJdbcDaoImpl;

/**
 * <code>ProfileJdbcServiceImpl</code> implements <code>SpringJdbcService</code>
 * All the services associated with <code>Profile</code> are implemented
 * Any controller with <code>Profile</code> related activities will instantiate this class
 * This service instantiates <code>ProfileJdbcDaoImpl</code> to perform database transactions
 * @author Yumiko Iwai
 * @version 1.0
 */
public class ProfileJdbcServiceImpl implements SpringJdbcService<Profile> {
	//instantiates ProfileJdbcDaoImpl profile related database transaction
	ProfileJdbcDaoImpl profileJdbcDao = new ProfileJdbcDaoImpl();
	//getter/setter for the attribute
	
	public ProfileJdbcDaoImpl getSpringJdbcDao() {
		return profileJdbcDao;
	}

	public void setSpringJdbcDao(ProfileJdbcDaoImpl springJdbcDao) {
		this.profileJdbcDao = springJdbcDao;
	}
	
	/**
	 * Select a profile by its id
	 * @param id a profile id
	 * @return returns empty profile object if a profile_id doesn't have match in database, 
	 * otherwise, returns a profile record
	 */
	@Override
	public Profile selectById(int id) {
		//select profile by profile_id
		return profileJdbcDao.selectById(id);
	}
	
	/**
	 * Insert a profile into the database
	 * @param profile a user profile
	 * @return the profile's id number
	 */
	public int insert(Profile profile) {
		return profileJdbcDao.insert(profile);
	}
	
	/**
	 * Update a profile
	 * @param profile the profile to be updated
	 * @return returns the number of records affected by the update
	 */
	public int update(Profile profile) {
		return profileJdbcDao.update(profile);
	}
	
	/**
	 * Delete a profile by its id
	 * @param profileID the profile's id to be deleted
	 * @return returns the number of records deleted
	 */
	public int delete(int profileID) {
		return profileJdbcDao.deleteById(profileID);
	}
	
	/**
	 * Get the last profile's id from the database
	 * @return returns the last profile's id
	 */
	public int getLastId() {
		return profileJdbcDao.getLastProfileId();
	}

}
