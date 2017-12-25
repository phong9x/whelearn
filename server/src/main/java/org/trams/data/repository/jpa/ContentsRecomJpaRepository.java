package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.Content;
import org.trams.bean.jpa.ContentEntity;
import org.trams.bean.jpa.ContentsRecomEntity;
/**
 * Repository : ContentsRecom.
 */
public interface ContentsRecomJpaRepository extends PagingAndSortingRepository<ContentsRecomEntity, Integer> {
	@Query(
			"Select u From ContentsRecomEntity u "
			)
	Page<ContentsRecomEntity> listPaging(Pageable pageable);

	@Query(
			"Select u.content From ContentsRecomEntity u "
			)
	List<ContentEntity> listPagingAllContent(Pageable pageable);

	@Modifying
	@Query(
			value = "delete from contents_recom",nativeQuery = true
			)
	void deleteAll();
}
