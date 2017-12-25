package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.CouponUseEntity;
/**
 * Repository : CouponUse.
 */
public interface CouponUseJpaRepository extends PagingAndSortingRepository<CouponUseEntity, Integer> {
	@Query(
			"Select u From CouponUseEntity u "
			)
	Page<CouponUseEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From CouponUseEntity u Where u.userId = ?1 and u.coupon.id != 3 and u.coupon.id != 4"
			)
	Page<CouponUseEntity> listPagingByUserId( Integer userId, Pageable pageable);

	@Query(
			"Select u From CouponUseEntity u Where u.userId = ?1 and u.statusUse = ?2 and u.coupon.id != 3 and u.coupon.id != 4"
			)
	Page<CouponUseEntity> listPagingByUserIdAndType( Integer userId, Integer statusUse, Pageable pageable);
	
	@Query(
			"Select u From CouponUseEntity u Where u.userId = ?1 and u.statusUse = ?2 and u.deadline > ?3 and u.coupon.id != 3 and u.coupon.id != 4"
			)
	Page<CouponUseEntity> listPagingByUserIdAndTypeAndDateline( Integer userId, Integer statusUse, Date date, Pageable pageable);
}
