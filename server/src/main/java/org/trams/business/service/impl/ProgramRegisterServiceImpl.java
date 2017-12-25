/*
 * Created on 30 thg 8 2016 ( Time 15:36:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.bean.ProgramRegister;
import org.trams.bean.UserFeed;
import org.trams.bean.jpa.ProgramRegisterEntity;
import org.trams.bean.jpa.ProgramRegisterEntityCustom;
import org.trams.business.service.ProgramRegisterService;
import org.trams.business.service.mapping.ProgramRegisterServiceMapper;
import org.trams.data.repository.jpa.ProgramRegisterJpaRepository;
/**
 * Implementation of ProgramRegisterService
 */
@Component
@Transactional
public class ProgramRegisterServiceImpl implements ProgramRegisterService {

	@Resource
	private ProgramRegisterJpaRepository programRegisterJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private ProgramRegisterServiceMapper programRegisterServiceMapper;
	
	@Override
	public ProgramRegister findById(Integer id) {
		ProgramRegisterEntity programRegisterEntity = programRegisterJpaRepository.findOne(id);
		return programRegisterServiceMapper.mapProgramRegisterEntityToProgramRegister(programRegisterEntity);
	}

	@Override
	public Page<ProgramRegisterEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return programRegisterJpaRepository.findAll(request);
	}

	@Override
	public List<ProgramRegister> findAll() {
		Iterable<ProgramRegisterEntity> entities = programRegisterJpaRepository.findAll();
		List<ProgramRegister> beans = new ArrayList<ProgramRegister>();
		for(ProgramRegisterEntity programRegisterEntity : entities) {
			beans.add(programRegisterServiceMapper.mapProgramRegisterEntityToProgramRegister(programRegisterEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = programRegisterJpaRepository.count();
		return count;
	}

	@Override
	public ProgramRegister save(ProgramRegister programRegister) {
		return update(programRegister) ;
	}

	@Override
	public ProgramRegister create(ProgramRegister programRegister) {
/*
		ProgramRegisterEntity programRegisterEntity = programRegisterJpaRepository.findOne(programRegister.getId());
		if( programRegisterEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		programRegisterEntity = new ProgramRegisterEntity();
		programRegisterServiceMapper.mapProgramRegisterToProgramRegisterEntity(programRegister, programRegisterEntity);
		ProgramRegisterEntity programRegisterEntitySaved = programRegisterJpaRepository.save(programRegisterEntity);
		return programRegisterServiceMapper.mapProgramRegisterEntityToProgramRegister(programRegisterEntitySaved);
*/
		ProgramRegisterEntity programRegisterEntity = new ProgramRegisterEntity();
		programRegisterServiceMapper.mapProgramRegisterToProgramRegisterEntity(programRegister, programRegisterEntity);
		ProgramRegisterEntity programRegisterEntitySaved = programRegisterJpaRepository.save(programRegisterEntity);
		return programRegisterServiceMapper.mapProgramRegisterEntityToProgramRegister(programRegisterEntitySaved);
	}

	@Override
	public ProgramRegister update(ProgramRegister programRegister) {
		ProgramRegisterEntity programRegisterEntity = programRegisterJpaRepository.findOne(programRegister.getId());
		programRegisterServiceMapper.mapProgramRegisterToProgramRegisterEntity(programRegister, programRegisterEntity);
		ProgramRegisterEntity programRegisterEntitySaved = programRegisterJpaRepository.save(programRegisterEntity);
		return programRegisterServiceMapper.mapProgramRegisterEntityToProgramRegister(programRegisterEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		programRegisterJpaRepository.delete(id);
	}

	public ProgramRegisterJpaRepository getProgramRegisterJpaRepository() {
		return programRegisterJpaRepository;
	}

	public void setProgramRegisterJpaRepository(ProgramRegisterJpaRepository programRegisterJpaRepository) {
		this.programRegisterJpaRepository = programRegisterJpaRepository;
	}

	public ProgramRegisterServiceMapper getProgramRegisterServiceMapper() {
		return programRegisterServiceMapper;
	}

	public void setProgramRegisterServiceMapper(ProgramRegisterServiceMapper programRegisterServiceMapper) {
		this.programRegisterServiceMapper = programRegisterServiceMapper;
	}

	@Override
	public Page<ProgramRegisterEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return programRegisterJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	

	@Override
	public Page<ProgramRegisterEntity> listPagingByUserId(Integer page, Integer size, Integer userId) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return programRegisterJpaRepository.listPagingByUserId(userId, pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<UserFeed> listPagingFeedUser(Date date, Integer page, Integer size) {
		try {
			Integer from = (page - 1)* size;
			Integer to = size;
			List<Object[]> list = programRegisterJpaRepository.listPagingFeedUser(date, from, to);
			List<UserFeed> result = new ArrayList<>();
			for (Object[] i : list) {
				UserFeed u = new UserFeed();
				u.setUserRegisterId((Integer) i[0]);
				u.setProviderId((Integer) i[1]);
				u.setNickname((String) i[2]);
				u.setPhone((String) i[3]);
				u.setProgramId((Integer) i[4]);
				u.setTitle((String) i[5]);
				u.setTimeLearn((Date) i[6]);
				result.add(u);
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Integer totalFeedUser(Date date) {
		try {
			return programRegisterJpaRepository.totalFeedUser(date);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<UserFeed> listPagingFeedUserByNickname(Date date, String name, Integer page, Integer size) {
		try {
			Integer from = (page - 1)* size;
			Integer to = size;
			List<Object[]> list = programRegisterJpaRepository.listPagingFeedUserByNickname(date, "%"+name+"%", from, to);
			List<UserFeed> result = new ArrayList<>();
			for (Object[] i : list) {
				UserFeed u = new UserFeed();
				u.setUserRegisterId((Integer) i[0]);
				u.setProviderId((Integer) i[1]);
				u.setNickname((String) i[2]);
				u.setPhone((String) i[3]);
				u.setProgramId((Integer) i[4]);
				u.setTitle((String) i[5]);
				u.setTimeLearn((Date) i[6]);
				result.add(u);
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Integer totalFeedUserByNickname(Date date, String nickname) {
		try {
			return programRegisterJpaRepository.totalFeedUser(date);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<UserFeed> listPagingFeedUserByTitle(Date date, String name, Integer page, Integer size) {
		try {
			Integer from = (page - 1)* size;
			Integer to = size;
			List<Object[]> list = programRegisterJpaRepository.listPagingFeedUserByTitle(date, "%"+name+"%", from, to);
			List<UserFeed> result = new ArrayList<>();
			for (Object[] i : list) {
				UserFeed u = new UserFeed();
				u.setUserRegisterId((Integer) i[0]);
				u.setProviderId((Integer) i[1]);
				u.setNickname((String) i[2]);
				u.setPhone((String) i[3]);
				u.setProgramId((Integer) i[4]);
				u.setTitle((String) i[5]);
				u.setTimeLearn((Date) i[6]);
				result.add(u);
			}
			return result;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Integer totalFeedUserByTitle(Date date, String title) {
		try {
			return programRegisterJpaRepository.totalFeedUser(date);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<ProgramRegisterEntity> listByUserIdAndProgramId(Integer userId, Integer programId) {
		try {
			return programRegisterJpaRepository.listByUserIdAndProgramId(userId, programId);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Page<ProgramRegisterEntityCustom> listPagingByUserIdCustom(Integer userId, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return programRegisterJpaRepository.listPagingByUserIdCustom(userId, pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}