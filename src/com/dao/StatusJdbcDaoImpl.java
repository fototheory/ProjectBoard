package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.beans.Status;

/**
 * <code>StatusJdbcDaoImpl</code> implements <code>SpringJdbcDao</code>
 * All the database transactions associated with <code>Status</code> are implemented
 * Any service with <code>Status</code> related activities will instantiate this class
 * @author Yumiko Iwai
 * @version 1.0
 */
public class StatusJdbcDaoImpl implements SpringJdbcDao<Status> {
	private JdbcDataSource ds = new SpringJdbcDataSource();
	private JdbcTemplate template;
	public StatusJdbcDaoImpl() {
		super();
		ds.createDataSource();
		template = ds.getJdbcTemplate();
	}
	
	/**
	 * generic select by id
	 * @param id status_id of status table
	 * @return returns empty status object if a status_id doesn't have match in database, 
	 * otherwise, returns a status record
	 */
	public Status selectById(int id) {
		String query = "SELECT * FROM status WHERE status_id=?";
		List<Status> statusInfo = this.template.query(query,
				 new Object[]{id},
			        new RowMapper<Status>() {
			            public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	   	Status temp = new Status();
			            	temp.setId(rs.getInt("status_id"));
			            	temp.setName(rs.getString("status_name"));
			                return temp;
			            }
		        });
		return fetchOneStatus(statusInfo);
	}
	
	/**
	 * generic select by name
	 * @param id status_id of status table
	 * @return returns empty status object if a status_id doesn't have match in database, 
	 * otherwise, returns a status record
	 */
	public int selectIdByName(String statName) {
		String query = "SELECT * FROM status WHERE status_name=?";
		List<Status> statusInfo = this.template.query(query,
				 new Object[]{statName},
			        new RowMapper<Status>() {
			            public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	   	Status temp = new Status();
			            	temp.setId(rs.getInt("status_id"));
			            	temp.setName(rs.getString("status_name"));
			                return temp;
			            }
		        });
		return fetchOneId(statusInfo);
	}
	
	/**
	 * from the list, fetch the id of first record
	 * @param statusInfo a list of records retrieved from database
	 * @return returns empty user object if the list is empty, otherwise, returns a user in the list
	 */
	protected int fetchOneId(List<Status> statusInfo) {
		if(statusInfo.size()>0) {
			//only one user should be returned
			return statusInfo.get(0).getId();			
		}
		//return empty user if not user found in database
		return 0;
	}
	
	/**
	 * from the list, fetch the first record
	 * @param statusInfo a list of records retrieved from database
	 * @return returns empty user object if the list is empty, otherwise, returns a user in the list
	 */
	protected Status fetchOneStatus(List<Status> statusInfo) {
		if(statusInfo.size()>0) {
			//only one user should be returned
			return statusInfo.get(0);			
		}
		//return empty user if not user found in database
		return new Status();
	}
}

