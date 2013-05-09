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
	 * Select a role by its id
	 * @param id a role id
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
	 * Fetch the first record from a list of Roles
	 * @param roleInfo a Role list
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
	
	/**
	 * Select all roles
	 * @return returns a list of roles 
	 */
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

	/**
	 * Select all roles except the admin role
	 * @return a list of all roles except the admin role
	 */
	public List<Role> selectAllRolesExceptAdmin() {
		String query = "select * from role where role_name <> 'Admin' order by role_id;";
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
	
	/**
	 * Insert a role
	 * @param role a Role object
	 * @return int
	 */
	public int insert(final Role role) {
		final String query = "insert into role (role_name) values (?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
			
		this.template.update(new PreparedStatementCreator() {           
			@Override
	        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException, DataIntegrityViolationException {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, role.getName());

	                return ps;
	            }
	        }, keyHolder);
			
			return keyHolder.getKey().intValue();
	}	
	
	/**
	 * Update a role with a role object
	 * @param role a Role object
	 * @return returns the number of records affected by the update
	 */
	public int update(Role role) {
		String query = "UPDATE role SET role_name=? WHERE role_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{role.getName(), role.getId()});
		}
		catch(Exception e) { 
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Delete a role by the role id
	 * @param roleID the role's id number
	 * @return returns the number of records deleted
	 */
	public int deleteById(int roleID) {
		String query = "DELETE FROM role WHERE role_id=? ";
		int count = 0;
		try {
			count = this.template.update(query,new Object[]{roleID});
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Get the last role's id number
	 * @return returns the role_id of the last role in the table
	 */
	public int getLastRoleId() {
		String query = "select max(role_id) from role";
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
	 * Get the role's name given the role's id
	 * @return returns the name of the role
	 */
	public String getRoleNameById(int roleId) {
		String query = "select role_name from role where role_id = ?";

		List<Role> roleName = this.template.query(query,
				 new Object[]{roleId},
			        new RowMapper<Role>() {
			            public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			            	Role temp = new Role();
			            	temp.setName(rs.getString("role_name"));
			                return temp;
			            }
		        });
		return fetchOneRole(roleName).getName();
	}
}

