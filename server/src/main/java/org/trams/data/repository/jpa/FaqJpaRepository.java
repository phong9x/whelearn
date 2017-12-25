package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.FaqEntity;
/**
 * Repository : Faq.
 */
public interface FaqJpaRepository extends PagingAndSortingRepository<FaqEntity, Integer> {
	@Query(
			"Select u From FaqEntity u "
			)
	Page<FaqEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From FaqEntity u where u.categoryId = ?1"
			)
	Page<FaqEntity> listPagingByCategoryId(Integer categoryId,Pageable pageable);
	
	@Query(
			"Select u From FaqEntity u where u.question like ?1"
			)
	Page<FaqEntity> listPagingByQuestion(String question,Pageable pageable);
	
	@Query(
			"Select u From FaqEntity u where u.categoryName like ?1"
			)
	Page<FaqEntity> listPagingByCategoryName(String categoryName,Pageable pageable);

}
