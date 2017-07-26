package com.api.toggle.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Basic class to databse configuration.
 * 
 * @author emersonmuraro
 *
 */
public abstract class AbstractRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Get the correct session to database access.
	 * @return
	 */
	protected Session getSession() {
		try 
		{
		    //Step-2: Implementation
			return sessionFactory.getCurrentSession();
		} 
		catch (HibernateException e) 
		{
		    //Step-3: Implementation
			return sessionFactory.openSession();
		}
	}

	/**
	 * Method for database persistence.
	 * @param entity
	 */
	public void persist(Object entity) {
		getSession().persist(entity);
	}

	/**
	 * Method for database delete.
	 * @param entity
	 */
	public void delete(Object entity) {
		getSession().delete(entity);
	}
	
	/**
	 * Method for database merge.
	 * @param entity
	 */
	public void merge(Object entity) {
		getSession().merge(entity);
	}
}