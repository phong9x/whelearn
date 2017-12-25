/*
 * Created on 23 thg 8 2016 ( Time 16:20:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.trams.bean.ProgramView;
import org.trams.bean.jpa.ProgramViewEntity;
import java.util.Date;
import org.trams.business.service.ProgramViewService;
import org.trams.business.service.mapping.ProgramViewServiceMapper;
import org.trams.data.repository.jpa.ProgramViewJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of ProgramViewService
 */
@Component
@Transactional
public class ProgramViewServiceImpl implements ProgramViewService {

	@Resource
	private ProgramViewJpaRepository programViewJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private ProgramViewServiceMapper programViewServiceMapper;
	
	@Override
	public ProgramView findById(Integer id) {
		ProgramViewEntity programViewEntity = programViewJpaRepository.findOne(id);
		return programViewServiceMapper.mapProgramViewEntityToProgramView(programViewEntity);
	}

	@Override
	public Page<ProgramViewEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return programViewJpaRepository.findAll(request);
	}

	@Override
	public List<ProgramView> findAll() {
		Iterable<ProgramViewEntity> entities = programViewJpaRepository.findAll();
		List<ProgramView> beans = new ArrayList<ProgramView>();
		for(ProgramViewEntity programViewEntity : entities) {
			beans.add(programViewServiceMapper.mapProgramViewEntityToProgramView(programViewEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = programViewJpaRepository.count();
		return count;
	}

	@Override
	public ProgramView save(ProgramView programView) {
		return update(programView) ;
	}

	@Override
	public ProgramView create(ProgramView programView) {
/*
		ProgramViewEntity programViewEntity = programViewJpaRepository.findOne(programView.getId());
		if( programViewEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		programViewEntity = new ProgramViewEntity();
		programViewServiceMapper.mapProgramViewToProgramViewEntity(programView, programViewEntity);
		ProgramViewEntity programViewEntitySaved = programViewJpaRepository.save(programViewEntity);
		return programViewServiceMapper.mapProgramViewEntityToProgramView(programViewEntitySaved);
*/
		ProgramViewEntity programViewEntity = new ProgramViewEntity();
		programViewServiceMapper.mapProgramViewToProgramViewEntity(programView, programViewEntity);
		ProgramViewEntity programViewEntitySaved = programViewJpaRepository.save(programViewEntity);
		return programViewServiceMapper.mapProgramViewEntityToProgramView(programViewEntitySaved);
	}

	@Override
	public ProgramView update(ProgramView programView) {
		ProgramViewEntity programViewEntity = programViewJpaRepository.findOne(programView.getId());
		programViewServiceMapper.mapProgramViewToProgramViewEntity(programView, programViewEntity);
		ProgramViewEntity programViewEntitySaved = programViewJpaRepository.save(programViewEntity);
		return programViewServiceMapper.mapProgramViewEntityToProgramView(programViewEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		programViewJpaRepository.delete(id);
	}

	public ProgramViewJpaRepository getProgramViewJpaRepository() {
		return programViewJpaRepository;
	}

	public void setProgramViewJpaRepository(ProgramViewJpaRepository programViewJpaRepository) {
		this.programViewJpaRepository = programViewJpaRepository;
	}

	public ProgramViewServiceMapper getProgramViewServiceMapper() {
		return programViewServiceMapper;
	}

	public void setProgramViewServiceMapper(ProgramViewServiceMapper programViewServiceMapper) {
		this.programViewServiceMapper = programViewServiceMapper;
	}

	@Override
	public Page<ProgramViewEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "updateDate")));
			return programViewJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	

	@Override
	public Page<ProgramViewEntity> listPagingByUserId(Integer page, Integer size, Integer userId) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "updateDate")));
			return programViewJpaRepository.listPagingByUserId(userId, pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Page<ProgramViewEntity> listPagingByUsernameAndTitle(String nickname, String title, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "updateDate")));
			return programViewJpaRepository.listPagingByUsernameAndTitle("%"+nickname+"%", "%"+title+"%", pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<ProgramViewEntity> listPagingByUserIdAndProgramId(Integer userId, Integer programId
			) {
		try {
			return programViewJpaRepository.listPagingByUserIdAndProgramId(userId, programId);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}