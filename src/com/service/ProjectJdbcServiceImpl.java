package com.service;

import java.util.List;

import com.beans.Project;
import com.beans.Role;
import com.dao.ProjectJdbcDaoImpl;
import com.dao.RoleJdbcDaoImpl;

/**
 * <code>ProjectJdbcServiceImpl</code> implements <code>SpringJdbcService</code>
 * All the services associated with <code>Project</code> are implemented
 * Any controller with <code>Project</code> related activities will instantiate this class
 * This service instantiates <code>ProjectJdbcDaoImpl</code> to perform database transactions
 * @author Yumiko Iwai
 * @version 1.0
 */
public class ProjectJdbcServiceImpl implements SpringJdbcService<Project> {
	//instantiates ProjectJdbcDaoImpl project related database transaction
	ProjectJdbcDaoImpl projectJdbcDao = new ProjectJdbcDaoImpl();
	//instantiates ProjectJdbcDaoImpl user related database transaction
	RoleJdbcDaoImpl roleJdbcDao = new RoleJdbcDaoImpl();
	
	//getter/setter for the attribute
	public ProjectJdbcDaoImpl getSpringJdbcDao() {
		return projectJdbcDao;
	}

	public void setSpringJdbcDao(ProjectJdbcDaoImpl springJdbcDao) {
		this.projectJdbcDao = springJdbcDao;
	}
    /**
	 * generic select by id
	 * @param id user_id of user table
	 * @return returns empty user object if a user hasn't logged in, 
	 * otherwise, returns a user in the list
	 */
	@Override
	public Project selectById(int id) {
		//select user by user_id
		return projectJdbcDao.selectById(id);
	}
    /**
	 * check user's authenticity
	 * @param email is passed from <code>LoginController</code> through login.jsp
	 * @param password is passed from <code>LoginController</code> through login.jsp
	 * @return returns empty user object if a user hasn't logged in, 
	 * otherwise, returns a user in the list
	 */
	public Project loginCheck(String email, String password) {
		//get user with matching email and password
		return projectJdbcDao.loginCheck(email, password);
	}
	
	/**
	 * check session user's authenticity 
	 * @param id user_id of session variable
	 * @return returns count of user found
	 */
	public int countByRole(int id, int roleId) {
		//get role name
		Role role = roleJdbcDao.selectById(roleId);
		//get user with matching email and password
		return projectJdbcDao.countByRole(id, projectFldByRole(role.getName()));
	}
	
	/**
	 * check session user's authenticity 
	 * @param id user_id of session variable
	 * @return returns count of user found
	 */
	public List<Project> selectByRole(int id, int roleId) {
		//get role name
		Role role = roleJdbcDao.selectById(roleId);
		//get user with matching email and password
		return projectJdbcDao.selectByRole(id, projectFldByRole(role.getName()));
	}
	
	/**
	 * fetch project field name based on role
	 * @param roleName of the user
	 * @return returns count of user found
	 */
	protected String projectFldByRole(String roleName) {
		String fld = "";
		switch (roleName) {
        case "Sponsor": 
        	fld = "project_sponsor";
        	break;
        case "Leadfaculty": 
        	fld = "project_lead_faculty";
        	break;
        case "Negotiatingfaculty": 
        	fld = "project_negotiating_faculty";
        	break;
        case "Capstonefaculty": 
        	fld = "project_capstone_faculty";
        	break;
        default:
        	break;
		}
		return fld;
	}
	
}

