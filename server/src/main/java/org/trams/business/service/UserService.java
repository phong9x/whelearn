/*
 * Created on 23 thg 8 2016 ( Time 14:46:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service;

import java.util.Date;
import java.util.List;
import org.trams.bean.jpa.UserEntity;
import org.trams.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Business Service Interface for entity User.
 */
public interface UserService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	User findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<User> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<UserEntity> findAll(Integer page);

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
	User save(User entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	User update(User entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	User create(User entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<UserEntity> listPaging(Integer page,Integer size);
	
	List<UserEntity> findByEmail(String email);

	Page<UserEntity> findByFullname(String fullname, Integer page, Integer size);

	void update_isDelete(Integer id);

	User login(String username, String password);

	List<String> findByDayOfBirthAndGender(Date dayOfBirth, Short gender);
	
	User findByDayOfBirthAndGenderAndEmail(Date dayOfBirth, Short gender, String email);

	Page<UserEntity> listPagingByRole(Integer type, Integer page, Integer size);
	
	Page<UserEntity> findByNicknameAndEmailAndRole(String nickname, String email,Integer type, Integer page, Integer size);
	
	Page<UserEntity> findByNicknameAndEmail(String nickname, String email, Integer page, Integer size);
	
	User loginAdminPage(String username, String password);
	
	void updatePushFcmToken(String fcm_token);
}
