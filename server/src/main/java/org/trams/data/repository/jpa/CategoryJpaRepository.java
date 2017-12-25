package org.trams.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.bean.jpa.CategoryEntity;
/**
 * Repository : Category.
 */
public interface CategoryJpaRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {
	@Query(
			"Select u From CategoryEntity u "
			)
	Page<CategoryEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From CategoryEntity u "
			)
	List<CategoryEntity> findAllOrder(Pageable pageable);

	@Modifying
	@Query(
			value="UPDATE category SET count=count + 1 WHERE id = ?1",nativeQuery=true
			)
	void updateCategoryCount(Integer category_id);
}
