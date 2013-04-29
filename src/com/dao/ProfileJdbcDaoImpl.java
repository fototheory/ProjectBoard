package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	 * generic select by id
	 * @param id profile_id of profile table
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
	 * return the record count of the profile found in database (select by id)
	 * @param id profile_id of profile table
	 * @return record count
	 */
	public int countById(int id) {
		String query = "SELECT COUNT(profile_id) FROM profile WHERE profile_id=?";
		return this.template.queryForInt(query, new Object[]{id});
	}
	
	/**
	 * from the list, fetch the first record
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

	
	public int addNewProfile(Profile profile) {
		String query = "insert into profile (profile_company_name,profile_skills,profile_phone) values (?, ?, ?)";

		this.template.update(query,profile.getCompany(),profile.getSkills(),profile.getPhone());
		
		//Return id of the last record inserted
		return this.template.queryForInt("select max(profile_id) from profile;");
	}

}
