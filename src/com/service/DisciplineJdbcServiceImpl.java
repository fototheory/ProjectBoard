package com.service;

import com.beans.Discipline;
import com.dao.DisciplineJdbcDaoImpl;
import java.util.List;

/**
 * <code>DisciplineJdbcServiceImpl</code> implements <code>SpringJdbcService</code>
 * All the services associated with <code>Discipline</code> are implemented
 * Any controller with <code>Disciplines</code> related activities will instantiate this class
 * This service instantiates <code>DisciplineJdbcDaoImpl</code> to perform database transactions
 * @author Yumiko Iwai
 * @version 1.0
 */
public class DisciplineJdbcServiceImpl implements SpringJdbcService<Discipline> {
	//instantiates DisciplineJdbcDaoImpl user related database transaction
	DisciplineJdbcDaoImpl disciplineJdbcDao = new DisciplineJdbcDaoImpl();
	//getter/setter for the attribute
	public DisciplineJdbcDaoImpl getSpringJdbcDao() {
		return disciplineJdbcDao;
	}

	public void setSpringJdbcDao(DisciplineJdbcDaoImpl springJdbcDao) {
		this.disciplineJdbcDao = springJdbcDao;
	}
	
	/**
	 * Select a discipline given its id
	 * @param id discipline_id of discipline table
	 * @return returns empty discipline object if a discipline_id doesn't have match in database, 
	 * otherwise, returns a discipline in the list
	 */
	@Override
	public Discipline selectById(int id) {
		return disciplineJdbcDao.selectById(id);
	}
	
	/**
	 * Select a list of all disciplines
	 * @return returns a list of all disciplines in the database
	 */
	public List<Discipline> selectAllDisciplines() {
		return disciplineJdbcDao.selectAllDisciplines();
	}
	
	/**
	 * Get the discipline name given the discipline's id
	 * @param disciplineId the discipline_id for the discipline
	 * @return returns the name of the discipline
	 */
	public String getDisciplineName(int disciplineId) {
		return disciplineJdbcDao.getDisciplineNameById(disciplineId);
	}
}
