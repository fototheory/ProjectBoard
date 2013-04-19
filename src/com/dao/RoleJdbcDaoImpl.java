package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.beans.Role;

/**
 * <code>RoleJdbcDaoImpl</code> implements <code>SpringJdbcDao</code>
 * All the database transactions associated with <code>Role</code> are implemented
 * Any service with <code>Role</code> related activities will instantiate this class
 * @author Yumiko Iwai
 * @version 1.0
 */
public class RoleJdbcDaoImpl implements SpringJdbcDao<Role> {
	private JdbcDataSource ds = new SpringJdbcDataSource();
	private JdbcTemplate template;
	public RoleJdbcDaoImpl() {
		super();
		ds.createDataSource();
		template = ds.getJdbcTemplate();
	}
	
	/**
	 * generic select by id
	 * @param id role_id of role table
	 * @return returns empty role object if a role_id doesn't have match in database, 
	 * otherwise, returns a role record
	 */
	public Role selectById(int id) {
		String query = "SELECT * FROM role WHERE role_id=?";
		List<Role> roleInfo = this.template.query(query,
				 new Object[]{id},
			        new RowMapper<Role>() {
			            public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	   	Role temp = new Role();
			            	temp.setId(rs.getInt("role_id"));
			            	temp.setName(rs.getString("role_name"));
			                return temp;
			            }
		        });
		return fetchOneRole(roleInfo);
	}
	
	/**
	 * from the list, fetch the first record
	 * @param roleInfo a list of records retrieved from database
	 * @return returns empty user object if the list is empty, otherwise, returns a user in the list
	 */
	protected Role fetchOneRole(List<Role> roleInfo) {
		if(roleInfo.size()>0) {
			//only one user should be returned
			return roleInfo.get(0);			
		}
		//return empty user if not user found in database
		return new Role();
	}
	
	public List<Role> selectAllRoles() {
		String query = "select * from role order by role_id;";
		List<Role> roles = this.template.query(query, new RowMapper<Role>(){
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role temp = new Role();
				temp.setId(rs.getInt("role_id"));
				temp.setName(rs.getString("role_name"));
				return temp;
			}
		});
		return roles;
	}
}

