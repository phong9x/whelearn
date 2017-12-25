package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.CouponEntity;
/**
 * Repository : Coupon.
 */
public interface CouponJpaRepository extends PagingAndSortingRepository<CouponEntity, Integer> {
	@Query(
			"Select u From CouponEntity u "
			)
	Page<CouponEntity> listPaging(Pageable pageable);


	@Query(
			"Select u From CouponEntity u Where u.name like ?1 "
			)
	Page<CouponEntity> findByName(String name, Pageable pageable);
}
