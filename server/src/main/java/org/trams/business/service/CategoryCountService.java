/*
 * Created on 5 12�� 2016 ( Time 15:38:23 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service;

import java.util.List;
import org.trams.bean.jpa.CategoryCountEntity;
import org.trams.bean.CategoryCount;
import org.springframework.data.domain.Page;

/**
 * Business Service Interface for entity CategoryCount.
 */
public interface CategoryCountService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	CategoryCount findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<CategoryCount> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<CategoryCountEntity> findAll(Integer page);

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
	CategoryCount save(CategoryCount entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	CategoryCount update(CategoryCount entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	CategoryCount create(CategoryCount entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<CategoryCountEntity> listPaging(Integer page,Integer size);
	
	CategoryCount findByUserIdAndCategoryId(Integer userId, Integer categoryId) ;

	Integer getFirstCategoryIdByUserId(Integer userId);
	
}
