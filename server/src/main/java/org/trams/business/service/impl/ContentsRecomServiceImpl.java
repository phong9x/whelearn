/*
 * Created on 25 thg 8 2016 ( Time 14:19:50 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.trams.bean.ContentsRecom;
import org.trams.bean.jpa.ContentEntity;
import org.trams.bean.jpa.ContentsRecomEntity;
import java.util.Date;
import org.trams.business.service.ContentsRecomService;
import org.trams.business.service.mapping.ContentsRecomServiceMapper;
import org.trams.data.repository.jpa.ContentsRecomJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of ContentsRecomService
 */
@Component
@Transactional
public class ContentsRecomServiceImpl implements ContentsRecomService {

	@Resource
	private ContentsRecomJpaRepository contentsRecomJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private ContentsRecomServiceMapper contentsRecomServiceMapper;
	
	@Override
	public ContentsRecom findById(Integer id) {
		ContentsRecomEntity contentsRecomEntity = contentsRecomJpaRepository.findOne(id);
		return contentsRecomServiceMapper.mapContentsRecomEntityToContentsRecom(contentsRecomEntity);
	}

	@Override
	public Page<ContentsRecomEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return contentsRecomJpaRepository.findAll(request);
	}

	@Override
	public List<ContentsRecom> findAll() {
		Iterable<ContentsRecomEntity> entities = contentsRecomJpaRepository.findAll();
		List<ContentsRecom> beans = new ArrayList<ContentsRecom>();
		for(ContentsRecomEntity contentsRecomEntity : entities) {
			beans.add(contentsRecomServiceMapper.mapContentsRecomEntityToContentsRecom(contentsRecomEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = contentsRecomJpaRepository.count();
		return count;
	}

	@Override
	public ContentsRecom save(ContentsRecom contentsRecom) {
		return update(contentsRecom) ;
	}

	@Override
	public ContentsRecom create(ContentsRecom contentsRecom) {
/*
		ContentsRecomEntity contentsRecomEntity = contentsRecomJpaRepository.findOne(contentsRecom.getId());
		if( contentsRecomEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		contentsRecomEntity = new ContentsRecomEntity();
		contentsRecomServiceMapper.mapContentsRecomToContentsRecomEntity(contentsRecom, contentsRecomEntity);
		ContentsRecomEntity contentsRecomEntitySaved = contentsRecomJpaRepository.save(contentsRecomEntity);
		return contentsRecomServiceMapper.mapContentsRecomEntityToContentsRecom(contentsRecomEntitySaved);
*/
		try {
			ContentsRecomEntity contentsRecomEntity = new ContentsRecomEntity();
			contentsRecomServiceMapper.mapContentsRecomToContentsRecomEntity(contentsRecom, contentsRecomEntity);
			System.out.println(contentsRecom);;
			System.out.println(contentsRecomEntity);;
			ContentsRecomEntity contentsRecomEntitySaved = contentsRecomJpaRepository.save(contentsRecomEntity);
			return contentsRecomServiceMapper.mapContentsRecomEntityToContentsRecom(contentsRecomEntitySaved);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public ContentsRecom update(ContentsRecom contentsRecom) {
		ContentsRecomEntity contentsRecomEntity = contentsRecomJpaRepository.findOne(contentsRecom.getId());
		contentsRecomServiceMapper.mapContentsRecomToContentsRecomEntity(contentsRecom, contentsRecomEntity);
		ContentsRecomEntity contentsRecomEntitySaved = contentsRecomJpaRepository.save(contentsRecomEntity);
		return contentsRecomServiceMapper.mapContentsRecomEntityToContentsRecom(contentsRecomEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		contentsRecomJpaRepository.delete(id);
	}

	public ContentsRecomJpaRepository getContentsRecomJpaRepository() {
		return contentsRecomJpaRepository;
	}

	public void setContentsRecomJpaRepository(ContentsRecomJpaRepository contentsRecomJpaRepository) {
		this.contentsRecomJpaRepository = contentsRecomJpaRepository;
	}

	public ContentsRecomServiceMapper getContentsRecomServiceMapper() {
		return contentsRecomServiceMapper;
	}

	public void setContentsRecomServiceMapper(ContentsRecomServiceMapper contentsRecomServiceMapper) {
		this.contentsRecomServiceMapper = contentsRecomServiceMapper;
	}

	@Override
	public Page<ContentsRecomEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.ASC, "id")));
			return contentsRecomJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<ContentEntity> listPagingAllContent() {
		try {
			PageRequest pageable = new PageRequest(0, 3, new Sort(new Order(Direction.ASC, "id")));
			return contentsRecomJpaRepository.listPagingAllContent(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public void deleteAll() {
		try {
			 contentsRecomJpaRepository.deleteAll();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	


}
