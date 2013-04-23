package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.beans.User;

/**
 * <code>UserJdbcDaoImpl</code> implements <code>SpringJdbcDao</code>
 * All the database transactions associated with <code>User</code> are implemented
 * Any service with <code>User</code> related activities will instantiate this class
 * @author Yumiko Iwai
 * @version 1.0
 */
public class UserJdbcDaoImpl implements SpringJdbcDao<User> {
	protected final Log logger = LogFactory.getLog(getClass());

	//attributes
	//data source
	private JdbcDataSource ds = new SpringJdbcDataSource();
	//database connection
	private JdbcTemplate template;
    /**
	 * default constructor
	 * will create data source and <code>JdbcTemplate</code> for database connection
	 */
	public UserJdbcDaoImpl() {
		super();
		ds.createDataSource();
		template = ds.getJdbcTemplate();
	}
	/**
	 * generic select by id
	 * @param id user_id of user table
	 * @return returns empty user object if a user_id doesn't have match in database, 
	 * otherwise, returns a user record
	 */
	@Override
	public User selectById(int id) {
		String query = "SELECT * FROM user WHERE user_id=?";
		List<User> userInfo = this.template.query(query,
		        new Object[]{id},
		        new RowMapper<User>() {
		            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	   	User temp = new User();
	            		temp.setId(rs.getInt("user_id"));
		            	temp.setFname(rs.getString("user_fname"));
		            	temp.setLname(rs.getString("user_lname"));
		            	temp.setRoleId(rs.getInt("role_id"));
		            	temp.setVerified(rs.getInt("user_isverified"));
		                return temp;
		            }
		        });
		return this.fetchOneUser(userInfo);	
	}
	/**
	 * select by email and password
	 * @param email is passed from <code>UserJdbcServiceImpl</code>
	 * @param password is passed from <code>UserJdbcServiceImpl</code>
	 * @return returns empty user object if a user_id doesn't have match in database, 
	 * otherwise, returns a user record
	 */
	public User loginCheck(String email, String password) {

		String query = "SELECT * FROM user WHERE user_email=? and user_password=?";

		List<User> userInfo = this.template.query(query,
		        new Object[]{email, password},
		        new RowMapper<User>() {
		            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	   	User temp = new User();
	            	   	temp.setId(rs.getInt("user_id"));
		            	temp.setFname(rs.getString("user_fname"));
		            	temp.setLname(rs.getString("user_lname"));
		            	temp.setRoleId(rs.getInt("role_id"));
		            	temp.setVerified(rs.getInt("user_isverified"));
		                return temp;
		            }
		        });
		return this.fetchOneUser(userInfo);		
	}
	
	/**
	 * return the record count of the user found in database (select by id)
	 * @param id user_id of user table
	 * @return record count
	 */
	public int countById(int id) {
		String query = "SELECT COUNT(user_id) FROM user WHERE user_id=?";
		return this.template.queryForInt(query, new Object[]{id});
	}
	
	/**
	 * from the list, fetch the first record
	 * @param userInfo a list of records retrieved from database
	 * @return returns empty user object if the list is empty, otherwise, returns a user in the list
	 */
	protected User fetchOneUser(List<User> userInfo) {
		if(userInfo.size()>0) {
			//only one user should be returned
			return userInfo.get(0);			
		}
		//return empty user if not user found in database
		return new User();
	}

	public int getIdByEmail(String email) {
		String query = "select user_id from user where user_email = ?";
		
		int id = this.template.queryForInt(query,email);
		
		return id;
	}
	
	public int addNewUser(User user) {
		logger.info("adding user "+user.getEmail()+","+user.getPassword()+","+user.getFname()+
				","+user.getLname()+","+user.getRoleId()+","+user.getDisciplineId());

		
		String query = "insert into user (user_email,user_password,user_fname,user_lname,role_id,discipline_id) " +
				"values (?, ?, ?, ?, ?, ?)";
		
		this.template.update(query,user.getEmail(),user.getPassword(),
				user.getFname(),user.getLname(),
				user.getRoleId(),user.getDisciplineId());

		return getIdByEmail(user.getEmail());
	}

	public User getUserByEmail(String email) {
		String query = "select * from user where user_email = ?";
		
		List<User> userInfo = this.template.query(query,
		        new Object[]{email},
		        new RowMapper<User>() {
		            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	   	User temp = new User(rs.getInt("user_id"),rs.getString("user_fname"),rs.getString("user_lname"),
	            	   			rs.getString("user_email"),rs.getString("user_password"),rs.getInt("user_isverified"),
	            	   			rs.getInt("user_hasProfile"),rs.getInt("role_Id"),rs.getInt("profile_Id"),
	            	   			rs.getInt("discipline_Id"),rs.getInt("group_id"));
	            	   	
		                return temp;
		            }
		        });
		return this.fetchOneUser(userInfo);	
	}
}
