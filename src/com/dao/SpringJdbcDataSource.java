package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * <code>SpringJdbcDataSource</code> implements <code>JdbcDataSource</code>
 * This class creates mysql database connection.
 * mysql-connector-java-1.1.24-bin.jar is required.
 * @author Yumiko Iwai
 * @version 1.0
 */
public class SpringJdbcDataSource implements JdbcDataSource {
	//attribute to store database connection
	protected JdbcTemplate jdbcTemplate;
	public void createDataSource() {
		//creates data source object
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//set driver through mysql-connector-java-1.1.24-bin.jar
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//jdbc:mysql://[host]:[port]/[database name]
		dataSource.setUrl("jdbc:mysql://localhost:3306/projectboard");
		//database user
		dataSource.setUsername("root");
		//database user password
		dataSource.setPassword("password");
		//set dtabase source to the attribute
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	//gettter
	public JdbcTemplate getJdbcTemplate() { 
		return this.jdbcTemplate; 
	}
}
