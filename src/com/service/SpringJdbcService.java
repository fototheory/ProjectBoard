package com.service;

/**
 * <code>SpringJdbcService</code> implements generic interface
 * @author Yumiko Iwai
 * @version 1.0
 */
public interface SpringJdbcService<T> {
	T selectById(int id);
}