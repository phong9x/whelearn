package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.CommentEntity;
/**
 * Repository : Comment.
 */
public interface CommentJpaRepository extends PagingAndSortingRepository<CommentEntity, Integer> {
	@Query(
			"Select u From CommentEntity u "
			)
	Page<CommentEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From CommentEntity u where u.type =?1 and u.threadId=?2"
			)
	Page<CommentEntity> listPagingByTypeAndThreadId(Integer type, Integer threadId, Pageable pageable);
	
	@Query(
			value = "select sum(c.point)/count(id) from comment c where c.thread_id = ?1 and c.type = ?2", nativeQuery = true
			)
	Double averagePointByProgramIdAndType(Integer threadId, Integer type);
}
