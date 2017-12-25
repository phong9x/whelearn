/*
 * Created on 23 thg 8 2016 ( Time 16:20:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.trams.bean.Notice;
import org.trams.bean.jpa.NoticeEntity;
import java.util.Date;
import org.trams.business.service.NoticeService;
import org.trams.business.service.mapping.NoticeServiceMapper;
import org.trams.data.repository.jpa.NoticeJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of NoticeService
 */
@Component
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private NoticeJpaRepository noticeJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private NoticeServiceMapper noticeServiceMapper;
	
	@Override
	public Notice findById(Integer id) {
		NoticeEntity noticeEntity = noticeJpaRepository.findOne(id);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntity);
	}

	@Override
	public Page<NoticeEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return noticeJpaRepository.findAll(request);
	}

	@Override
	public List<Notice> findAll() {
		Iterable<NoticeEntity> entities = noticeJpaRepository.findAll();
		List<Notice> beans = new ArrayList<Notice>();
		for(NoticeEntity noticeEntity : entities) {
			beans.add(noticeServiceMapper.mapNoticeEntityToNotice(noticeEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = noticeJpaRepository.count();
		return count;
	}

	@Override
	public Notice save(Notice notice) {
		return update(notice) ;
	}

	@Override
	public Notice create(Notice notice) {
/*
		NoticeEntity noticeEntity = noticeJpaRepository.findOne(notice.getId());
		if( noticeEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		noticeEntity = new NoticeEntity();
		noticeServiceMapper.mapNoticeToNoticeEntity(notice, noticeEntity);
		NoticeEntity noticeEntitySaved = noticeJpaRepository.save(noticeEntity);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntitySaved);
*/
		NoticeEntity noticeEntity = new NoticeEntity();
		noticeServiceMapper.mapNoticeToNoticeEntity(notice, noticeEntity);
		NoticeEntity noticeEntitySaved = noticeJpaRepository.save(noticeEntity);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntitySaved);
	}

	@Override
	public Notice update(Notice notice) {
		NoticeEntity noticeEntity = noticeJpaRepository.findOne(notice.getId());
		noticeServiceMapper.mapNoticeToNoticeEntity(notice, noticeEntity);
		NoticeEntity noticeEntitySaved = noticeJpaRepository.save(noticeEntity);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		noticeJpaRepository.delete(id);
	}

	public NoticeJpaRepository getNoticeJpaRepository() {
		return noticeJpaRepository;
	}

	public void setNoticeJpaRepository(NoticeJpaRepository noticeJpaRepository) {
		this.noticeJpaRepository = noticeJpaRepository;
	}

	public NoticeServiceMapper getNoticeServiceMapper() {
		return noticeServiceMapper;
	}

	public void setNoticeServiceMapper(NoticeServiceMapper noticeServiceMapper) {
		this.noticeServiceMapper = noticeServiceMapper;
	}

	@Override
	public Page<NoticeEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return noticeJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	

	@Override
	public Page<NoticeEntity> findByTitle(String title, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return noticeJpaRepository.findByTitle(title, pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}