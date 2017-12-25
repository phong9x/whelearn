package org.trams.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.bean.jpa.PurchaseEntity;
/**
 * Repository : Purchase.
 */
public interface PurchaseJpaRepository extends PagingAndSortingRepository<PurchaseEntity, Integer> {
	@Query(
			"Select u From PurchaseEntity u where u.orderProgram =0"
			)
	Page<PurchaseEntity> listPaging(Pageable pageable);

	@Modifying
	@Query(
			value="UPDATE purchase SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);

	@Query(
			"Select u From PurchaseEntity u Where u.fullname like ?1 and u.orderProgram =0"
			)
	Page<PurchaseEntity> findByFullname(String fullname, Pageable pageable);
	
	@Query(
			"Select u From PurchaseEntity u Where u.program.title like ?1 and u.orderProgram =0"
			)
	Page<PurchaseEntity> findByProgramTitle(String fullname, Pageable pageable);
	
	@Query(
			"Select u From PurchaseEntity u Where u.user.id = ?1 and u.orderProgram =0"
			)
	List<PurchaseEntity> findByUserId(Integer userId, Pageable pageable);
	

	@Query(
			value="select u.nickname, u.email, p.create_date, c.content, p.total_money, p.refund_money, p.refund_date, p.fullname from  "
					+ "user u inner join purchase p on u.id = p.user_id "
					+ "left join coupon c on p.coupon_id = c.id  "
					+ "where p.program_id = ?1 and p.order_program =0",nativeQuery=true
			)
	List<Object[]> listMemberJoinProgram(Integer id);
	
	@Query(
			"Select u From PurchaseEntity u where u.user.id = ?1 and u.orderProgram =0"
			)
	Page<PurchaseEntity> listPagingByUserId(Integer userId,Pageable pageable);
	
	
	@Query(
			"Select u From PurchaseEntity u where u.user.id = ?1 and u.program.id = ?2 and  u.orderProgram =0"
			)
	Page<PurchaseEntity> listPagingByUserIdAndProgramId(Integer userId, Integer programId, Pageable pageable);
	
	@Query(
			"Select u From PurchaseEntity u where u.user.id = ?1  and u.program.id = ?2  and u.orderProgram = 0"
			)
	List<PurchaseEntity> findByUserIdAndProgramId(Integer userId, Integer programId);
	
	@Query(
			"Select u From PurchaseEntity u Where u.program.title like ?1 and u.user.id = ?2  and u.orderProgram =0"
			)
	Page<PurchaseEntity> findByProgramTitleAndUserIdAndProgramId(String fullname,Integer userId, Pageable pageable);
	
	
	@Query(
			"Select u From PurchaseEntity u Where u.program.title like ?1 and u.user.id = ?2 and u.orderProgram =0"
			)
	Page<PurchaseEntity> findByProgramTitleAndUserId(String fullname,Integer userId, Pageable pageable);
	
	
	@Query(
			value="select sum(money_paid) from purchase where program_id =?1 and tid is null and order_program =0",nativeQuery=true
			)
	Long totalMoneyPaidByProgramId(Integer programId);
	
	@Query(
			"Select u From PurchaseEntity u where u.program.id = ?1 and u.orderProgram =0"
			)
	PurchaseEntity findByProgramIdAndTeacer(Integer programId,Integer teacher);
	
	@Modifying
	@Query(
			value = "update purchase c set c.refund_money = 2 where c.refund_money = 1 ", nativeQuery = true
			)
	void updateRefundEndday();
	
	
	PurchaseEntity findByTid(String tid);
	
	@Query(value="SELECT A.user_id "+
				"FROM "+
				"(SELECT user_id, COUNT(id) as p_count " +
				   "FROM purchase " +
				  "WHERE order_program = 0 " +
				  "GROUP BY user_id " +
				  "ORDER BY p_count DESC " +
				  "LIMIT 3) A"
			,nativeQuery=true)
	List<Integer> findTop3Users();
	
}
