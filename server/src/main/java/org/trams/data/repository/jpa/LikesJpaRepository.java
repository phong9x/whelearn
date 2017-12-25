package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.LikesEntity;
/**
 * Repository : Likes.
 */
public interface LikesJpaRepository extends PagingAndSortingRepository<LikesEntity, Integer> {
	@Query(
			"Select u From LikesEntity u "
			)
	Page<LikesEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From LikesEntity u Where u.compositePrimaryKey.userId = ?1 "
			)
	Page<LikesEntity> listPagingByUserId( Integer userId, Pageable pageable);

	@Query(
			"Select u From LikesEntity u Where u.compositePrimaryKey.userId = ?1 and u.compositePrimaryKey.threadId = ?2 and u.compositePrimaryKey.type = ?3"
			)
	LikesEntity findByUserIdAndThreadIdAndType( Integer userId, Integer contentId , Integer type);
	
	@Modifying
	@Query(
			value = "delete from likes where user_id = ?1 and thread_id = ?2 and type = ?3",nativeQuery = true
			)
	void deleteLike( Integer userId, Integer threadId, Integer type);
}
