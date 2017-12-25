/*
 * Created on 25 thg 8 2016 ( Time 14:19:50 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service;

import java.util.List;

import org.trams.bean.jpa.ContentEntity;
import org.trams.bean.jpa.ContentsRecomEntity;
import org.trams.bean.ContentsRecom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Business Service Interface for entity ContentsRecom.
 */
public interface ContentsRecomService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	ContentsRecom findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<ContentsRecom> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<ContentsRecomEntity> findAll(Integer page);

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
	ContentsRecom save(ContentsRecom entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	ContentsRecom update(ContentsRecom entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	ContentsRecom create(ContentsRecom entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<ContentsRecomEntity> listPaging(Integer page,Integer size);
	
	List<ContentEntity> listPagingAllContent();

	void deleteAll();
}