/*
 * Created on 24 thg 8 2016 ( Time 17:05:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service;

import java.util.List;
import org.trams.bean.jpa.CouponEntity;
import org.trams.bean.Coupon;
import org.springframework.data.domain.Page;

/**
 * Business Service Interface for entity Coupon.
 */
public interface CouponService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Coupon findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Coupon> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<CouponEntity> findAll(Integer page);

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
	Coupon save(Coupon entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Coupon update(Coupon entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Coupon create(Coupon entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<CouponEntity> listPaging(Integer page,Integer size);
	
	Page<CouponEntity> findByName(String name, Integer page, Integer size);



}
