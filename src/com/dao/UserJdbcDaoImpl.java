package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
	//Initialize the logger
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
	 * Select a user by id
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
	            		temp.setEmail(rs.getString("user_email"));
	            		temp.setPassword(rs.getString("user_password"));
		            	temp.setFname(rs.getString("user_fname"));
		            	temp.setLname(rs.getString("user_lname"));
		            	temp.setRoleId(rs.getInt("role_id"));
		            	temp.setVerified(rs.getInt("user_isverified"));
		            	temp.setHasProfile(rs.getInt("user_hasprofile"));
		            	temp.setProfileId(rs.getInt("profile_id"));
		            	temp.setDisciplineId(rs.getInt("discipline_id"));
		            	temp.setGroupId(rs.getInt("group_id"));
		                return temp;
		            }
		        });
		return this.fetchOneUser(userInfo);	
	}
	
	/**
	 * Select a user by email and password
	 * @param email is passed from <code>UserJdbcServiceImpl</code>
	 * @param password is passed from <code>UserJdbcServiceImpl</code>
	 * @return returns empty user object if a user_id doesn't have match in database, 
	 * otherwise, returns a user record
	 */
	public User getUserByEmailAndPassword(String email, String password) {

		String query = "SELECT * FROM user WHERE user_email=? and user_password=?";

		List<User> userInfo = this.template.query(query,
		        new Object[]{email, password},
		        new RowMapper<User>() {
		            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	   	User temp = new User();
	            	   	temp.setId(rs.getInt("user_id"));
	            		temp.setEmail(rs.getString("user_email"));
	            		temp.setPassword(rs.getString("user_password"));
		            	temp.setFname(rs.getString("user_fname"));
		            	temp.setLname(rs.getString("user_lname"));
		            	temp.setEmail(rs.getString("user_email"));
		            	temp.setDisciplineId(rs.getInt("discipline_id"));
		            	temp.setRoleId(rs.getInt("role_id"));
		            	temp.setVerified(rs.getInt("user_isverified"));
		            	temp.setHasProfile(rs.getInt("user_hasprofile"));
		            	temp.setProfileId(rs.getInt("profile_id"));
		            	temp.setDisciplineId(rs.getInt("discipline_id"));
		            	temp.setGroupId(rs.getInt("group_id"));
		            	return temp;
		            }
		        });
		return this.fetchOneUser(userInfo);		
	}
	
	/**
	 * Return the record count of the user found in database (select by id)
	 * @param id user_id of user table
	 * @return record count
	 */
	public int countById(int id) {
		String query = "SELECT COUNT(user_id) FROM user WHERE user_id=?";
		
		int count = 0;
		try {
			count = this.template.queryForInt(query, new Object[]{id});
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
		return count;
	}
	
	/**
	 * Fetch the first record from a user list
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

	/**
	 * Get the user's id by email
	 * @param email the user's email
	 * @return returns the user_id for the user with the given email
	 */
	public int getIdByEmail(String email) {
		String query = "select user_id from user where user_email = ?";
		
		int UserId=0;
		try {
			UserId = this.template.queryForInt(query,email);
		}
		catch(Exception e) {
			logger.info(e.toString());
		}		
		return UserId;
	}
	
	/**
	 * Insert a user into the database
	 * @param user a user object to be inserted into the database
	 * @return returns the number of records inserted into the database
	 */
	public int insert(User user) {
		String query = "insert into user (user_email,user_password,user_fname,user_lname,role_id,discipline_id) " +
				"values (?, ?, ?, ?, ?, ?)";

		int count = 0;
		try {
			count = this.template.update(query,user.getEmail(),user.getPassword(),
					user.getFname(),user.getLname(),
					user.getRoleId(),user.getDisciplineId());
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
		return count;
	}

	/**
	 * Get the user's lead faculty's id given their discipline id
	 * @param dispId the discipline id for the lead faculty of that discipline
	 * @return returns the lead faculty's id for the given discipline id
	 */
	public int getLeadId(int dispId) {
		String query = "SELECT u.user_id FROM user u, role r " +
				"WHERE u.role_id=r.role_id AND u.discipline_id=? AND r.role_name=?";
		
		int LeadId = 0;
		try {
			LeadId = this.template.queryForInt(query, new Object[]{dispId,"Lead faculty"});
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
		return LeadId;
	}
	
	/**
	 * Get all leads for the given discipline id
	 * @param dispId the discipline id for the lead faculty of that discipline
	 * @return returns a list of lead faculty members for the given discipline id
	 */
	public List<User> getAllLeadsInDisp(int dispId) {
		String query = "SELECT * FROM user u, role r " +
				"WHERE u.role_id=r.role_id AND u.discipline_id=? AND r.role_name=?";
		
		List<User> userInfo = new LinkedList<User>();
		try {
			userInfo = this.template.query(query, new Object[]{dispId,"Lead faculty"}, 
					new RowMapper<User>() {
	            		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            			User temp = new User(rs.getInt("user_id"),rs.getString("user_fname"),rs.getString("user_lname"),
            	   			rs.getString("user_email"),rs.getString("user_password"),rs.getInt("user_isverified"),
            	   			rs.getInt("user_hasProfile"),rs.getInt("role_Id"),rs.getInt("profile_Id"),
            	   			rs.getInt("discipline_Id"),rs.getInt("group_id"));
            	   	
	                return temp;
	            }
	        });
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
		return userInfo;
	}
	
	/**
	 * Get a user by their email address
	 * @param email the email of the user
	 * @return returns a user given their email address
	 */
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
	
	/**
	 * Update the user's profile id (used as a pointer to the user's profile in the profile table)
	 * @param user the user to be updated
	 * @param profileId the user's profile id
	 * @return returns the number of records affected by the update
	 */
	public int updateProfileId(User user,int profileId) {
		String query = "update user set user_hasprofile=?,profile_id=? where user_id=?";
		
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{true,profileId,user.getId()});
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
		return count;
	}
	
	/**
	 * Update a user's attributes
	 * @param user the user to be updated
	 * @return returns the number of records affected by the update
	 */
	public int update(User user) {
		String query = "UPDATE user SET user_email=?,user_password,user_fname=?,user_lname=?," +
				"user_isverified=?,user_hasprofile=?,role_id=?,profile_id=?,discipline_id=?," +
				"group_id=? WHERE user_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{user.getEmail(), user.getPassword(),
					user.getFname(), user.getLname(), user.getIsVerified(), user.getHasProfile(),
					user.getRoleId(), user.getProfileId(), user.getDisciplineId(), user.getGroupId(),
					user.getId()});
		}
		catch(Exception e) { 
			logger.info(e.toString());
		}
		return count;
	}
	
	/**
	 * Delete a user record by their user_id
	 * @param userId the user id of the user to be deleted
	 * @return returns the number of records affected by the deletion
	 */
	public int deleteById(int userId) {
		String query = "DELETE FROM user WHERE user_id=? ";
		
		int count = 0;
		try {
			count = this.template.update(query,new Object[]{userId});
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
		return count;
	}

	/**
	 * Gets a list of all users
	 * @return returns a list of all users in the database
	 */
	public List<User> selectAllUsers() {
		String query = "select * from user order by user_id;";
		List<User> users = this.template.query(query, new RowMapper<User>(){
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User temp = new User();
        	   	temp.setId(rs.getInt("user_id"));
        		temp.setEmail(rs.getString("user_email"));
        		temp.setPassword(rs.getString("user_password"));
            	temp.setFname(rs.getString("user_fname"));
            	temp.setLname(rs.getString("user_lname"));
            	temp.setEmail(rs.getString("user_email"));
            	temp.setDisciplineId(rs.getInt("discipline_id"));
            	temp.setRoleId(rs.getInt("role_id"));
            	temp.setVerified(rs.getInt("user_isverified"));
            	temp.setHasProfile(rs.getInt("user_hasprofile"));
            	temp.setProfileId(rs.getInt("profile_id"));
            	temp.setDisciplineId(rs.getInt("discipline_id"));
            	temp.setGroupId(rs.getInt("group_id"));
				return temp;
			}
		});
		return users;
	}

	/**
	 * Get a a list of all unverified users
	 * @return returns a list of all unverified users in the database
	 */
	public List<User> selectUnVerifiedUsers() {
		String query = "select * from user where user_isverified=false order by user_id;";
		List<User> users = this.template.query(query, new RowMapper<User>(){
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User temp = new User();
        	   	temp.setId(rs.getInt("user_id"));
        		temp.setEmail(rs.getString("user_email"));
        		temp.setPassword(rs.getString("user_password"));
            	temp.setFname(rs.getString("user_fname"));
            	temp.setLname(rs.getString("user_lname"));
            	temp.setEmail(rs.getString("user_email"));
            	temp.setDisciplineId(rs.getInt("discipline_id"));
            	temp.setRoleId(rs.getInt("role_id"));
            	temp.setVerified(rs.getInt("user_isverified"));
            	temp.setHasProfile(rs.getInt("user_hasprofile"));
            	temp.setProfileId(rs.getInt("profile_id"));
            	temp.setDisciplineId(rs.getInt("discipline_id"));
            	temp.setGroupId(rs.getInt("group_id"));
				return temp;
			}
		});
		return users;
	}

	/**
	 * Verify the user so that they can access the system
	 * @param user the user to be verified
	 * @return the number of records affected by the verification
	 */
	public int verifyUser(User user) {
		String query = "update user set user_isverified=true where user_id=?";

		int count = 0;
		try {
			count = this.template.update(query,new Object[]{user.getId()});
		}
		catch(Exception e) {
			logger.info(e.toString());
		}
		return count;
	}
	
	public List<User> getUsersByRole(String roleName) {
		String query = "select * from user u, role r WHERE u.role_id=r.role_id AND r.role_name=?;";
		List<User> users = this.template.query(query, new Object[]{roleName}, new RowMapper<User>(){
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User temp = new User();
        	   	temp.setId(rs.getInt("user_id"));
        		temp.setEmail(rs.getString("user_email"));
        		temp.setPassword(rs.getString("user_password"));
            	temp.setFname(rs.getString("user_fname"));
            	temp.setLname(rs.getString("user_lname"));
            	temp.setEmail(rs.getString("user_email"));
            	temp.setDisciplineId(rs.getInt("discipline_id"));
            	temp.setRoleId(rs.getInt("role_id"));
            	temp.setVerified(rs.getInt("user_isverified"));
            	temp.setHasProfile(rs.getInt("user_hasprofile"));
            	temp.setProfileId(rs.getInt("profile_id"));
            	temp.setDisciplineId(rs.getInt("discipline_id"));
            	temp.setGroupId(rs.getInt("group_id"));
				return temp;
			}
		});
		return users;
	}
}
