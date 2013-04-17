package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
/**
 * <code>JdbcDataSource</code> implements generic interface
 * @author Yumiko Iwai
 * @version 1.0
 */
public interface JdbcDataSource {
	public void createDataSource();
	JdbcTemplate getJdbcTemplate();
}
