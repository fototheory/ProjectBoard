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
import com.beans.Discipline;

/**
 * <code>DisciplineJdbcDaoImpl</code> implements <code>SpringJdbcDao</code>
 * All the database transactions associated with <code>Discipline</code> are implemented
 * Any service with <code>Disciplines</code> related activities will instantiate this class
 * @author Yumiko Iwai
 * @version 1.0
 */
public class DisciplineJdbcDaoImpl implements SpringJdbcDao<Discipline> {
	private JdbcDataSource ds = new SpringJdbcDataSource();
	private JdbcTemplate template;
	public DisciplineJdbcDaoImpl() {
		super();
		ds.createDataSource();
		template = ds.getJdbcTemplate();
	}
	
	/**
	 * Select a discipline given its id
	 * @param id discipline_id of Discipline table
	 * @return returns empty Discipline object if a discipline_id doesn't have match in database, 
	 * otherwise, returns a Discipline record
	 */
	public Discipline selectById(int id) {
		String query = "SELECT * FROM discipline WHERE discipline_id=?";
		List<Discipline> disciplineInfo = this.template.query(query,
				 new Object[]{id},
			        new RowMapper<Discipline>() {
			            public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
			            	Discipline temp = new Discipline();
			            	temp.setId(rs.getInt("discipline_id"));
			            	temp.setName(rs.getString("discipline_name"));
			                return temp;
			            }
		        });
		return fetchOneDiscipline(disciplineInfo);
	}
	
	/**
	 * Fetch the first record from the Discipline list
	 * @param disciplineInfo a list of records retrieved from database
	 * @return returns empty user object if the list is empty, otherwise, returns a user in the list
	 */
	protected Discipline fetchOneDiscipline(List<Discipline> disciplineInfo) {
		if(disciplineInfo.size()>0) {
			//only one user should be returned
			return disciplineInfo.get(0);			
		}
		//return empty user if not user found in database
		return new Discipline();
	}
	
	/**
	 * Select a list of all disciplines
	 * @return returns a list of all disciplines in the database
	 */
	public List<Discipline> selectAllDisciplines() {
		String query = "select * from discipline order by discipline_id;";
		List<Discipline> disciplines = this.template.query(query, new RowMapper<Discipline>(){
			public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
				Discipline temp = new Discipline();
				temp.setId(rs.getInt("discipline_id"));
				temp.setName(rs.getString("discipline_name"));
				return temp;
			}
		});
		return disciplines;
	}
	
	/**
	 * Get the discipline name given the discipline's id
	 * @param disciplineId the discipline_id for the discipline
	 * @return returns the name of the discipline
	 */
	public String getDisciplineNameById(int disciplineId) {
		String query = "select discipline_name from discipline where discipline_id = ?";

		List<Discipline> disciplineName = this.template.query(query,
				 new Object[]{disciplineId},
			        new RowMapper<Discipline>() {
			            public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
			            	Discipline temp = new Discipline();
			            	temp.setName(rs.getString("discipline_name"));
			                return temp;
			            }
		        });
		return fetchOneDiscipline(disciplineName).getName();
	}

	//
	/**
	 * Insert a discipline
	 * @param discipline a Discipline object
	 * @return int
	 */
	public int insert(final Discipline discipline) {
		final String query = "insert into discipline (discipline_name) values (?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
			
		this.template.update(new PreparedStatementCreator() {           
			@Override
	        public PreparedStatement createPreparedStatement(Connection connection) 
	        		throws SQLException, DataIntegrityViolationException {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, discipline.getName());

	                return ps;
	            }
	        }, keyHolder);
			
			return keyHolder.getKey().intValue();
	}	
	
	/**
	 * Update a discipline with a discipline object
	 * @param discipline a Discipline object
	 * @return returns the number of records affected by the update
	 */
	public int update(Discipline discipline) {
		String query = "UPDATE discipline SET discipline_name=? WHERE discipline_id=?";
		int count = 0;
		try {
			count = this.template.update(query, new Object[]{discipline.getName(), discipline.getId()});
		}
		catch(Exception e) { 
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Delete a discipline by the discipline id
	 * @param disciplineID the discipline's id number
	 * @return returns the number of records deleted
	 */
	public int deleteById(int disciplineID) {
		String query = "DELETE FROM discipline WHERE discipline_id=? ";
		int count = 0;
		try {
			count = this.template.update(query,new Object[]{disciplineID});
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}
	
	/**
	 * Get the last discipline's id number
	 * @return returns the discipline_id of the last discipline in the table
	 */
	public int getLastDisciplineId() {
		String query = "select max(discipline_id) from discipline";
		int count = 0;
		try {
			count = this.template.queryForInt(query);
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		return count;
	}	
	
}

