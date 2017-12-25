/*
 * Created on 23 thg 8 2016 ( Time 16:20:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service;

import java.util.List;
import org.trams.bean.jpa.ContentEntity;
import org.trams.bean.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Business Service Interface for entity Content.
 */
public interface ContentService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Content findById( Integer id  ) ;
	
	ContentEntity findOne( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Content> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<ContentEntity> findAll(Integer page);

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
	Content save(Content entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Content update(Content entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Content create(Content entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<ContentEntity> listPaging(Integer page,Integer size);
	
	Page<ContentEntity> findByTitle(String title, Integer page, Integer size);

	Page<ContentEntity> findByCategoryId(Integer categoryId, Integer page, Integer size, String order);

	void updateLike(Integer userId, Integer programId);
	
	List<Content> findContentLike(Integer userId, Integer page, Integer size);
	
	Integer totalCountContentLike(Integer userId);

	Page<ContentEntity> findByCategoryName(String categoryName, Integer page, Integer size);
	
	void deleteContent(Integer contentId);
}
