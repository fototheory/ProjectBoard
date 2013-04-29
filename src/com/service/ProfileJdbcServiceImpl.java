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
	 * generic select by id
	 * @param id profile_id of profile table
	 * @return returns empty profile object if a profile hasn't logged in, 
	 * otherwise, returns a profile in the list
	 */
	@Override
	public Profile selectById(int id) {
		//select profile by profile_id
		return profileJdbcDao.selectById(id);
	}
	
	public int addNewProfile(Profile profile) {
		return profileJdbcDao.addNewProfile(profile);
	}

}
