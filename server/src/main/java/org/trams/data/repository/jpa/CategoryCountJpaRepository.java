package org.trams.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.trams.bean.jpa.CategoryCountEntity;
/**
 * Repository : CategoryCount.
 */
public interface CategoryCountJpaRepository extends PagingAndSortingRepository<CategoryCountEntity, Integer> {
	@Query(
			"Select u From CategoryCountEntity u "
			)
	Page<CategoryCountEntity> listPaging(Pageable pageable);

	CategoryCountEntity findByUserIdAndCategoryId(Integer userId, Integer categoryId);
	
	@Query(value = "SELECT category_id "
					+ "FROM category_count "
					+ "WHERE user_id = :userId "
					+ "ORDER BY count DESC, category_id ASC "
					+ "LIMIT 1",
	nativeQuery = true)
	Integer findFirstByUserId(@Param("userId") Integer userId);
}
