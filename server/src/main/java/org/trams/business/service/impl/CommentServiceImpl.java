/*
 * Created on 24 thg 8 2016 ( Time 10:18:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.trams.bean.Comment;
import org.trams.bean.jpa.CommentEntity;
import java.util.Date;
import org.trams.business.service.CommentService;
import org.trams.business.service.mapping.CommentServiceMapper;
import org.trams.data.repository.jpa.CommentJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of CommentService
 */
@Component
@Transactional
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentJpaRepository commentJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private CommentServiceMapper commentServiceMapper;
	
	@Override
	public Comment findById(Integer id) {
		CommentEntity commentEntity = commentJpaRepository.findOne(id);
		return commentServiceMapper.mapCommentEntityToComment(commentEntity);
	}

	@Override
	public Page<CommentEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return commentJpaRepository.findAll(request);
	}

	@Override
	public List<Comment> findAll() {
		Iterable<CommentEntity> entities = commentJpaRepository.findAll();
		List<Comment> beans = new ArrayList<Comment>();
		for(CommentEntity commentEntity : entities) {
			beans.add(commentServiceMapper.mapCommentEntityToComment(commentEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = commentJpaRepository.count();
		return count;
	}

	@Override
	public Comment save(Comment comment) {
		return update(comment) ;
	}

	@Override
	public Comment create(Comment comment) {
/*
		CommentEntity commentEntity = commentJpaRepository.findOne(comment.getId());
		if( commentEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		commentEntity = new CommentEntity();
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentJpaRepository.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
*/
		CommentEntity commentEntity = new CommentEntity();
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentJpaRepository.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
	}

	@Override
	public Comment update(Comment comment) {
		CommentEntity commentEntity = commentJpaRepository.findOne(comment.getId());
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentJpaRepository.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		commentJpaRepository.delete(id);
	}

	public CommentJpaRepository getCommentJpaRepository() {
		return commentJpaRepository;
	}

	public void setCommentJpaRepository(CommentJpaRepository commentJpaRepository) {
		this.commentJpaRepository = commentJpaRepository;
	}

	public CommentServiceMapper getCommentServiceMapper() {
		return commentServiceMapper;
	}

	public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
		this.commentServiceMapper = commentServiceMapper;
	}

	@Override
	public Page<CommentEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return commentJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Page<CommentEntity> listPagingByTypeAndThreadId(Integer type, Integer threadId, Integer page,Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return commentJpaRepository.listPagingByTypeAndThreadId(type, threadId, pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Double averagePointByProgramIdAndType(Integer threadId, Integer type) {
		try {
			Double point = commentJpaRepository.averagePointByProgramIdAndType(threadId,type );
			if(point == null){
				return (double)0;
			}
			return point;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	
	


}
