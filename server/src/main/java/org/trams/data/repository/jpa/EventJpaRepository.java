package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.EventEntity;
/**
 * Repository : Event.
 */
public interface EventJpaRepository extends PagingAndSortingRepository<EventEntity, Integer> {
	@Query(
			"Select u From EventEntity u "
			)
	Page<EventEntity> listPaging(Pageable pageable);


	@Query(
			"Select u From EventEntity u Where u.title like ?1 "
			)
	Page<EventEntity> findByTitle(String title, Pageable pageable);
}
