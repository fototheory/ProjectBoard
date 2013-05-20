package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.beans.Profile;

/**
 * <code>ProfileJdbcDaoImpl</code> implements <code>SpringJdbcDao</code>
 * All the database transactions associated with <code>Profile</code> are implemented
 * Any service with <code>Profile</code> related activities will instantiate this class
 * @author Yumiko Iwai
 * @version 1.0
 */
public class ProfileJdbcDaoImpl implements SpringJdbcDao<Profile> {
	//attributes
	//data source
	private JdbcDataSource ds = new SpringJdbcDataSource();
	//database connection
	private JdbcTemplate template;
	
    /**
	 * default constructor
	 * will create data source and <code>JdbcTemplate</code> for database connection
	 */
	public ProfileJdbcDaoImpl() {
		super();
		ds.createDataSource();
		template = ds.getJdbcTemplate();
	}
	
	/**
	 * Select a profile by its id
	 * @param id a profile id
	 * @return returns empty profile object if a profile_id doesn't have match in database, 
	 * otherwise, returns a profile record
	 */
	@Override
	public Profile selectById(int id) {
		String query = "SELECT * FROM profile WHERE profile_id=?";
		List<Profile> profileInfo = this.template.query(query,
		        new Object[]{id},
		        new RowMapper<Profile>() {
		            public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	   	Profile temp = new Profile();
	            	   	temp.setId(rs.getInt("profile_id"));
	            	   	temp.setCompany(rs.getString("profile_company_name"));
	            	   	temp.setPhone(rs.getString("profile_phone"));
	            	   	temp.setSkills(rs.getString("profile_skills"));
		                return temp;
		            }
		        });
		return this.fetchOneProfile(profileInfo);	
	}

	/**
	 * Insert a profile into the database
	 * @param profile a user profile
	 * @return the profile's id number
	 */
	public int insert(final Profile profile) {
		final String query = "insert into profile (profile_company_name,profile_phone,profile_skills) "+
				"values (?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
			
		this.template.update(new PreparedStatementCreator() {           
			@Override
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException, DataIntegrityViolationException {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, profile.getCompany());
				ps.setString(2, profile.getPhone());
				ps.setString(3, profile.getSkills());
	                return ps;
	            }
	        }, keyHolder);
			
			return keyHolder.getKey().intValue();
	}	
	
	/**
	 * Fetch the first record from a profile list
	 * @param profileInfo a list of records retrieved from database
	 * @return returns empty profile object if the list is empty, otherwise, returns a profile in the list
	 */
	protected Profile fetchOneProfile(List<Profile> profileInfo) {
		if(profileInfo.size()>0) {
			//only one profile should be returned
			return profileInfo.get(0);			
		}
		//return empty profile if not profile found in database
		return new Profile();
	}

	/**
	 * Delete a profile by its id
	 * @param profileID the profile's id to be deleted
	 * @return returns the number of records deleted
	 */
	public int deleteById(int profileID) {
		String query = "DELETE FROM profile WHERE profile_id=? ";
		int count = 0;
		try {
			count = this.template.update(query,new Object[]{profileID});
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Get the last profile's id from the database
	 * @return returns the last profile's id
	 */
	public int getLastProfileId() {
		String query = "select max(profile_id) from profile";
		int count = 0;
		try {
			count = this.template.queryForInt(query);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Update a profile
	 * @param profile the profile to be updated
	 * @return returns the number of records affected by the update
	 */
	public int update(Profile profile) {
		String query = "UPDATE profile SET profile_company_name=?, profile_phone=?, " +
				"profile_skills=? WHERE profile_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{profile.getCompany(), 
						profile.getPhone(), profile.getSkills(), profile.getId()});
		}
		catch(Exception e) { 
			System.out.println(e.toString());
		}
		return count;
	}

}
