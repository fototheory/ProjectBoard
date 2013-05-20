package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.beans.Project;

/**
 * <code>ProjectJdbcDaoImpl</code> implements <code>SpringJdbcDao</code>
 * All the database transactions associated with <code>Project</code> are implemented
 * Any service with <code>Project</code> related activities will instantiate this class
 * @author Yumiko Iwai
 * @version 1.0
 */
public class ProjectJdbcDaoImpl implements SpringJdbcDao<Project> {
	//attributes
	//data source
	private JdbcDataSource ds = new SpringJdbcDataSource();
	//database connection
	private JdbcTemplate template;
	
    /**
	 * default constructor
	 * will create data source and <code>JdbcTemplate</code> for database connection
	 */
	public ProjectJdbcDaoImpl() {
		super();
		ds.createDataSource();
		template = ds.getJdbcTemplate();
	}
	
	/**
	 * Select a project by its id
	 * @param id a project id
	 * @return returns empty project object if a project_id doesn't have match in database, 
	 * otherwise, returns a project record
	 */
	@Override
	public Project selectById(int id) {
		String query = "SELECT * FROM project WHERE project_id=?";
		List<Project> projInfo = this.template.query(query,
		        new Object[]{id},
		        new RowMapper<Project>() {
		            public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	   	Project temp = new Project();
	            		temp.setId(rs.getInt("project_id"));
	            	   	temp.setTitle(rs.getString("project_title"));
	                	temp.setDesc(rs.getString("project_description"));
	                	temp.setArchived(rs.getInt("project_isarchived"));
	                	temp.setManHours(rs.getInt("project_manhours"));
	                	temp.setDue(rs.getDate("project_date_due"));
	                	temp.setDateStarted(rs.getDate("project_date_started"));
	                	temp.setDateCompleted(rs.getDate("project_date_completed"));
	                	temp.setDateCreated(rs.getDate("project_date_created"));
	                	temp.setStatusId(rs.getInt("status_id"));
	                	temp.setDispId(rs.getInt("discipline_id"));
	                	temp.setGroupId(rs.getInt("group_id"));
	                	temp.setSponsorId(rs.getInt("project_sponsor"));
	                	temp.setLeadId(rs.getInt("project_lead_faculty"));
	                	temp.setNegId(rs.getInt("project_negotiating_faculty"));
	                	temp.setCapId(rs.getInt("project_capstone_faculty"));
		                return temp;
		            }
		        });
		return this.fetchOneProject(projInfo);	
	}
	
	/**
	 * Insert a project into the database
	 * @param proj a project object
	 * @return the project's id number
	 */
	public int insert(final Project proj) {
		final String query = "insert into project (project_title,project_description,project_date_due," +
				"project_date_created,status_id,discipline_id,project_sponsor) " +
				"values (?, ?, ?,now(),?, ?, ?)";
		if(proj.getDue()!=null) {
			final java.sql.Date sqlDate = changetoSqlDate(proj.getDue());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			this.template.update(new PreparedStatementCreator() {           
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection)
	                    throws SQLException {
	                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	                ps.setString(1, proj.getTitle());
	                ps.setString(2, proj.getDesc());
	                ps.setDate(3, sqlDate);
	                ps.setInt(4, proj.getStatusId());
	                ps.setInt(5, proj.getDispId());
	                ps.setInt(6, proj.getSponsorId());
	                return ps;
	            }
	        }, keyHolder);
			
			return keyHolder.getKey().intValue();
		}
		return 0;
	}
	
	/**
	 * Delete a project by its id
	 * @param projId the project's id to be deleted
	 * @return returns the number of records deleted
	 */
	public int delete(int projId) {
		String query = "DELETE FROM project WHERE project_id=? ";
		int count = 0;
		try {
			count = this.template.update(query,new Object[]{projId});
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Select by email and password
	 * @param email is passed from <code>ProjectJdbcServiceImpl</code>
	 * @param password is passed from <code>ProjectJdbcServiceImpl</code>
	 * @return returns empty user object if a user_id doesn't have match in database, 
	 * otherwise, returns a user record
	 */
	public Project loginCheck(String email, String password) {

		String query = "SELECT * FROM user WHERE user_email=? and user_password=?";

		List<Project> userInfo = this.template.query(query,
		        new Object[]{email, password},
		        new RowMapper<Project>() {
		            public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	   	Project temp = new Project();
	            	   	temp.setId(rs.getInt("user_id"));
	            	   	/*temp.setFname(rs.getString("user_fname"));
		            	temp.setLname(rs.getString("user_lname"));
		            	temp.setRoleId(rs.getInt("role_id"));
		            	temp.setVerified(rs.getInt("user_isverified"));*/
		                return temp;
		            }
		        });
		return this.fetchOneProject(userInfo);		
	}
	
	/**
	 * Return the record project count base on the user's role
	 * @param id the project's id
	 * @param roleFld the role's database field name
	 * @return record count
	 */
	public int countByRole(int id, String roleFld) {
		String query = "SELECT COUNT(project_id) FROM project WHERE "+roleFld+"=?";
		int count = 0;
		try {
			count = this.template.queryForInt(query, new Object[]{id}); 
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Get a list of unarchived projects by the user's role
	 * @param id user_id of user table
	 * @param roleFld the role's database field name 
	 * @return a list of unarchived projects
	 */
	public List<Project> selectByRole(int id, String roleFld) {
		String query = "SELECT * FROM project WHERE "+roleFld+"=? AND project_isarchived=0";
		return this.template.query(query, new Object[]{id},
				new RowMapper<Project>() {
            public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        	   	Project temp = new Project();
        	   	temp.setId(rs.getInt("project_id"));
        	   	temp.setTitle(rs.getString("project_title"));
            	temp.setDesc(rs.getString("project_description"));
            	temp.setArchived(rs.getInt("project_isarchived"));
            	temp.setManHours(rs.getInt("project_manhours"));
            	temp.setDue(rs.getDate("project_date_due"));
            	temp.setDateStarted(rs.getDate("project_date_started"));
            	temp.setDateCompleted(rs.getDate("project_date_completed"));
            	temp.setDateCreated(rs.getDate("project_date_created"));
            	temp.setStatusId(rs.getInt("status_id"));
            	temp.setDispId(rs.getInt("discipline_id"));
            	temp.setGroupId(rs.getInt("group_id"));
            	temp.setSponsorId(rs.getInt("project_sponsor"));
            	temp.setLeadId(rs.getInt("project_lead_faculty"));
            	temp.setNegId(rs.getInt("project_negotiating_faculty"));
            	temp.setCapId(rs.getInt("project_capstone_faculty"));
                return temp;
            }
        });
	}
	
	/**
	 * Select an archived project by the user's role
	 * @param id user_id of user table
	 * @param roleFld the role's database field name 
	 * @return a list of archived projects
	 */
	public List<Project> selectArchivedByRole(int id, String roleFld) {
		String query = "SELECT * FROM project WHERE "+roleFld+"=? AND project_isarchived=1";
		return this.template.query(query, new Object[]{id},
				new RowMapper<Project>() {
            public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        	   	Project temp = new Project();
        	   	temp.setId(rs.getInt("project_id"));
        	   	temp.setTitle(rs.getString("project_title"));
            	temp.setDesc(rs.getString("project_description"));
            	temp.setArchived(rs.getInt("project_isarchived"));
            	temp.setManHours(rs.getInt("project_manhours"));
            	temp.setDue(rs.getDate("project_date_due"));
            	temp.setDateStarted(rs.getDate("project_date_started"));
            	temp.setDateCompleted(rs.getDate("project_date_completed"));
            	temp.setDateCreated(rs.getDate("project_date_created"));
            	temp.setStatusId(rs.getInt("status_id"));
            	temp.setDispId(rs.getInt("discipline_id"));
            	temp.setGroupId(rs.getInt("group_id"));
            	temp.setSponsorId(rs.getInt("project_sponsor"));
            	temp.setLeadId(rs.getInt("project_lead_faculty"));
            	temp.setNegId(rs.getInt("project_negotiating_faculty"));
            	temp.setCapId(rs.getInt("project_capstone_faculty"));
                return temp;
            }
        });
	}
	
	/**
	 * Get all unarchived projects in the database
	 * @return a list of all unarchived projects
	 */
	public List<Project> selectAll() {
		String query = "SELECT * FROM project WHERE project_isarchived=0";
		return this.template.query(query, new Object[]{},
				new RowMapper<Project>() {
            public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        	   	Project temp = new Project();
        	   	temp.setId(rs.getInt("project_id"));
        	   	temp.setTitle(rs.getString("project_title"));
            	temp.setDesc(rs.getString("project_description"));
            	temp.setArchived(rs.getInt("project_isarchived"));
            	temp.setManHours(rs.getInt("project_manhours"));
            	temp.setDue(rs.getDate("project_date_due"));
            	temp.setDateStarted(rs.getDate("project_date_started"));
            	temp.setDateCompleted(rs.getDate("project_date_completed"));
            	temp.setDateCreated(rs.getDate("project_date_created"));
            	temp.setStatusId(rs.getInt("status_id"));
            	temp.setDispId(rs.getInt("discipline_id"));
            	temp.setGroupId(rs.getInt("group_id"));
            	temp.setSponsorId(rs.getInt("project_sponsor"));
            	temp.setLeadId(rs.getInt("project_lead_faculty"));
            	temp.setNegId(rs.getInt("project_negotiating_faculty"));
            	temp.setCapId(rs.getInt("project_capstone_faculty"));
                return temp;
            }
        });
	}
	
	/**
	 * Get all archived projects in the database
	 * @return a list of all archived projects
	 */
	public List<Project> selectAllARchived() {
		String query = "SELECT * FROM project WHERE project_isarchived=1";
		return this.template.query(query, new Object[]{},
				new RowMapper<Project>() {
            public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        	   	Project temp = new Project();
        	   	temp.setId(rs.getInt("project_id"));
        	   	temp.setTitle(rs.getString("project_title"));
            	temp.setDesc(rs.getString("project_description"));
            	temp.setArchived(rs.getInt("project_isarchived"));
            	temp.setManHours(rs.getInt("project_manhours"));
            	temp.setDue(rs.getDate("project_date_due"));
            	temp.setDateStarted(rs.getDate("project_date_started"));
            	temp.setDateCompleted(rs.getDate("project_date_completed"));
            	temp.setDateCreated(rs.getDate("project_date_created"));
            	temp.setStatusId(rs.getInt("status_id"));
            	temp.setDispId(rs.getInt("discipline_id"));
            	temp.setGroupId(rs.getInt("group_id"));
            	temp.setSponsorId(rs.getInt("project_sponsor"));
            	temp.setLeadId(rs.getInt("project_lead_faculty"));
            	temp.setNegId(rs.getInt("project_negotiating_faculty"));
            	temp.setCapId(rs.getInt("project_capstone_faculty"));
                return temp;
            }
        });
	}
	
	/**
	 * Update a project
	 * @param proj the project object to be updated
	 * @return returns the number of records affected by the update
	 */
	public int update(Project proj) {
		java.sql.Date sqlDate = null;
		if(proj.getDue()!=null) {
			sqlDate = changetoSqlDate(proj.getDue());
		}
		String query = "UPDATE project SET project_title=?, project_description=?, " +
				"project_date_due=? WHERE project_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{proj.getTitle(), proj.getDesc(), sqlDate, proj.getId()}); 
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Update the project's status
	 * @param projId the project's id
	 * @param statId the new status' id
	 * @return returns the number of records affected by the update
	 */
	public int updateStatus(int projId, int statId) {
		String query = "UPDATE project SET status_id=? WHERE project_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{statId, projId});
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Archive a project
	 * @param projId the project's id
	 * @return returns the number of records affected by the update
	 */
	public int archiveProject(int projId) {
		String query = "UPDATE project SET project_isarchived=1 WHERE project_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{projId}); 
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Update the project's status and faculty id
	 * @param projId the project's id
	 * @param statjId the status' id
	 * @param facFld the faculty's database field name
	 * @param facId the faculty user's id
	 * @return returns the number of records affected by the update
	 */
	public int updateStatusWithFac(int projId, int statId, String facFld, int facId) {
		String query = "UPDATE project SET status_id=?, "+ facFld+"=? WHERE project_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{statId, facId, projId}); 
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Check to see if project was accepted by the lead faculty user
	 * @param projId the project's id
	 * @param leadId the lead faculty user's id
	 * @return returns 0 for no and 1 yes that the project was accepted
	 */
	public int checkAcceptedbyLead(int projId, int leadId) {
		String query = "SELECT COUNT(project_id) FROM project WHERE project_id=? AND project_lead_faculty=?";
		int count = 0;
		try {
			count = this.template.queryForInt(query, new Object[]{projId, leadId}); 
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Change the Date object to sql formatted date
	 * @param date the date to be formatted
	 * @return returns sql formatted date
	 */
	protected java.sql.Date changetoSqlDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		String formattedDate = sdf.format(date);
		java.sql.Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(sdf.parse(formattedDate).getTime());
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return sqlDate;
	}
	
	/**
	 * Fetch the first record from a project list
	 * @param userInfo a list of records retrieved from database
	 * @return returns empty user object if the list is empty, otherwise, returns a user in the list
	 */
	protected Project fetchOneProject(List<Project> projInfo) {
		if(projInfo.size()>0) {
			//only one user should be returned
			return projInfo.get(0);			
		}
		//return empty user if not user found in database
		return new Project();
	}
}