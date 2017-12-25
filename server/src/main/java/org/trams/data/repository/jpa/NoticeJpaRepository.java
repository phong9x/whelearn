package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.NoticeEntity;
/**
 * Repository : Notice.
 */
public interface NoticeJpaRepository extends PagingAndSortingRepository<NoticeEntity, Integer> {
	@Query(
			"Select u From NoticeEntity u "
			)
	Page<NoticeEntity> listPaging(Pageable pageable);


	@Query(
			"Select u From NoticeEntity u Where u.title like ?1 "
			)
	Page<NoticeEntity> findByTitle(String title, Pageable pageable);
}
