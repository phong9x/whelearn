/*
 * Created on 23 thg 8 2016 ( Time 16:20:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service;

import java.util.List;
import org.trams.bean.jpa.ProgramEntity;
import org.trams.bean.jpa.ProgramEntityCustom;
import org.trams.bean.Keyword;
import org.trams.bean.KeywordWheaLearn;
import org.trams.bean.Program;
import org.trams.bean.UserKeywordList;
import org.trams.bean.WheLearn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Business Service Interface for entity Program.
 */
public interface ProgramService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Program findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Program> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<ProgramEntity> findAll(Integer page);

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
	Program save(Program entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Program update(Program entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Program create(Program entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<ProgramEntity> listPaging(Integer page,Integer size);
	
	Page<ProgramEntity> listPagingByUserId( Integer page, Integer size, Integer userId);

	Page<ProgramEntity> findByTitle(String title, Integer page, Integer size);

	void update_isDelete(Integer id);

	Page<ProgramEntity> findByCategoryId(Integer catId, Integer page, Integer size);

	void updatePointProgram(Integer userId, Integer programId);
	
	void updateLike(Integer userId, Integer programId);
	
	List<ProgramEntity> findByMonth( Integer month, Integer year);
	
	ProgramEntity findByIdEntity(Integer id);
	
	List<Program> findByProgramRelative(Integer categoryId,Integer programId);
	
	List<Program> findProgramLike(Integer userId, Integer page, Integer size);
	
	Integer totalCountProgramLike(Integer userId);
	
	List<Program> listProgram(Integer page, Integer size);
	
	Page<ProgramEntity> findByCategoryName(String categoryName, Integer page, Integer size);
	
	ProgramEntity findOne(Integer id);
	
	Page<ProgramEntity> findByListCategoryId(Integer[] catId, Integer page, Integer size);
	
	Page<ProgramEntityCustom> listPagingByUserIdCustom( Integer userId, Integer page, Integer size);
	
	Page<ProgramEntity> listPagingCaculate(Integer page, Integer size);
	
	Page<ProgramEntity> listPagingCaculateByTitle(String title, Integer page, Integer size);
	
	Page<ProgramEntity> listPagingCaculateByStatus(Integer status, Integer page, Integer size);
	
	Page<ProgramEntity> listPagingCaculateByUserId(Integer userId, Integer page, Integer size);
	
	Page<ProgramEntity> listPagingCaculateByTitleAndUserId(Integer userId,String title, Integer page, Integer size);
	
	Page<ProgramEntity> listPagingCaculateByStatusAndUserId(Integer userId,Integer status, Integer page, Integer size);
	
	void updateTotalMoney( Integer programId);
	
	WheLearn whelearnB_NotLogin();
	
	WheLearn whelearnC_NotLogin();
	
	WheLearn whelearnD_NotLogin();
	
	WheLearn whelearnE_NotLogin();
	
	WheLearn whelearnF_NotLogin();
	
	WheLearn listWhelearnB_Login();
	
	List<KeywordWheaLearn> listWhelearn_getProgram_byCategoryId(Integer categoryId, UserKeywordList ukl);
	
	Integer getCategoryId_Top1();
	
	List<String> listKeywordWhelearnC();
	
	List<String> listKeywordWhelearnD();
	
	List<String> listKeywordWhelearnE();
	
	List<String> listKeywordWhelearnF();
	
}