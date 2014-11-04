package fr.ocr.sql;

import java.sql.Connection;

public abstract class DAO<T> {
	protected Connection connect = null;
	
	public DAO(Connection conn) {
		this.connect = conn;
	}
	
	/** 
	 * Create method
	 * @param obj
	 * @return boolean
	 */
	public abstract T create(T obj);
	
	/**
	 * Delete method
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Update method
	 * @param obj
	 * @return boolean
	 */
	public abstract T update(T obj);
	
	public abstract T find(int id);
}
