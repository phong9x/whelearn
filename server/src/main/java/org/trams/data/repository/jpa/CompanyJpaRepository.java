package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.CompanyEntity;
/**
 * Repository : Company.
 */
public interface CompanyJpaRepository extends PagingAndSortingRepository<CompanyEntity, Integer> {
	@Query(
			"Select u From CompanyEntity u "
			)
	Page<CompanyEntity> listPaging(Pageable pageable);


}
