/*
 * Created on 8 thg 9 2016 ( Time 13:14:26 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.trams.bean.KeywordProgram;
import org.trams.bean.jpa.KeywordProgramEntity;
import java.util.Date;
import org.trams.business.service.KeywordProgramService;
import org.trams.business.service.mapping.KeywordProgramServiceMapper;
import org.trams.data.repository.jpa.KeywordProgramJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of KeywordProgramService
 */
@Component
@Transactional
public class KeywordProgramServiceImpl implements KeywordProgramService {

	@Resource
	private KeywordProgramJpaRepository keywordProgramJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private KeywordProgramServiceMapper keywordProgramServiceMapper;
	
	@Override
	public KeywordProgram findById(Integer programId) {
		KeywordProgramEntity keywordProgramEntity = keywordProgramJpaRepository.findOne(programId);
		return keywordProgramServiceMapper.mapKeywordProgramEntityToKeywordProgram(keywordProgramEntity);
	}

	@Override
	public Page<KeywordProgramEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "programId")));
		return keywordProgramJpaRepository.findAll(request);
	}

	@Override
	public List<KeywordProgram> findAll() {
		Iterable<KeywordProgramEntity> entities = keywordProgramJpaRepository.findAll();
		List<KeywordProgram> beans = new ArrayList<KeywordProgram>();
		for(KeywordProgramEntity keywordProgramEntity : entities) {
			beans.add(keywordProgramServiceMapper.mapKeywordProgramEntityToKeywordProgram(keywordProgramEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = keywordProgramJpaRepository.count();
		return count;
	}

	@Override
	public KeywordProgram save(KeywordProgram keywordProgram) {
		return update(keywordProgram) ;
	}

	@Override
	public KeywordProgram create(KeywordProgram keywordProgram) {
/*
		KeywordProgramEntity keywordProgramEntity = keywordProgramJpaRepository.findOne(keywordProgram.getProgramId());
		if( keywordProgramEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		keywordProgramEntity = new KeywordProgramEntity();
		keywordProgramServiceMapper.mapKeywordProgramToKeywordProgramEntity(keywordProgram, keywordProgramEntity);
		KeywordProgramEntity keywordProgramEntitySaved = keywordProgramJpaRepository.save(keywordProgramEntity);
		return keywordProgramServiceMapper.mapKeywordProgramEntityToKeywordProgram(keywordProgramEntitySaved);
*/
		KeywordProgramEntity keywordProgramEntity = new KeywordProgramEntity();
		keywordProgramServiceMapper.mapKeywordProgramToKeywordProgramEntity(keywordProgram, keywordProgramEntity);
		KeywordProgramEntity keywordProgramEntitySaved = keywordProgramJpaRepository.save(keywordProgramEntity);
		return keywordProgramServiceMapper.mapKeywordProgramEntityToKeywordProgram(keywordProgramEntitySaved);
	}

	@Override
	public KeywordProgram update(KeywordProgram keywordProgram) {
		KeywordProgramEntity keywordProgramEntity = keywordProgramJpaRepository.findOne(keywordProgram.getProgramId());
		keywordProgramServiceMapper.mapKeywordProgramToKeywordProgramEntity(keywordProgram, keywordProgramEntity);
		KeywordProgramEntity keywordProgramEntitySaved = keywordProgramJpaRepository.save(keywordProgramEntity);
		return keywordProgramServiceMapper.mapKeywordProgramEntityToKeywordProgram(keywordProgramEntitySaved);
	}

	@Override
	public void delete(Integer programId) {
		keywordProgramJpaRepository.delete(programId);
	}

	public KeywordProgramJpaRepository getKeywordProgramJpaRepository() {
		return keywordProgramJpaRepository;
	}

	public void setKeywordProgramJpaRepository(KeywordProgramJpaRepository keywordProgramJpaRepository) {
		this.keywordProgramJpaRepository = keywordProgramJpaRepository;
	}

	public KeywordProgramServiceMapper getKeywordProgramServiceMapper() {
		return keywordProgramServiceMapper;
	}

	public void setKeywordProgramServiceMapper(KeywordProgramServiceMapper keywordProgramServiceMapper) {
		this.keywordProgramServiceMapper = keywordProgramServiceMapper;
	}

	@Override
	public Page<KeywordProgramEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return keywordProgramJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public KeywordProgram findByProgramId(Integer programId) {
		try {
			return keywordProgramServiceMapper.mapKeywordProgramEntityToKeywordProgram(keywordProgramJpaRepository.findByProgramId(programId)) ;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	


}
