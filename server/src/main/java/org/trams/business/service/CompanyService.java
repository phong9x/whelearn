/*
 * Created on 26 thg 9 2016 ( Time 11:55:00 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service;

import java.util.List;
import org.trams.bean.jpa.CompanyEntity;
import org.trams.bean.Company;
import org.springframework.data.domain.Page;

/**
 * Business Service Interface for entity Company.
 */
public interface CompanyService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Company findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Company> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<CompanyEntity> findAll(Integer page);

	/**
	 * Count all entities
	 * @return Long
	 */
	Long countTotal();
	

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Company save(Company entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Company update(Company entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Company create(Company entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<CompanyEntity> listPaging(Integer page,Integer size);
	


}
