package com.dao;

/**
 * <code>SpringJdbcDao</code> implements generic interface
 * @author Yumiko Iwai
 * @version 1.0
 */
public interface SpringJdbcDao<T> {
	T selectById(int id);
}
