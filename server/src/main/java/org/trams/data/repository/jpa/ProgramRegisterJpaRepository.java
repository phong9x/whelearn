package org.trams.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.bean.jpa.ProgramRegisterEntity;
import org.trams.bean.jpa.ProgramRegisterEntityCustom;
/**
 * Repository : ProgramRegister.
 */
public interface ProgramRegisterJpaRepository extends PagingAndSortingRepository<ProgramRegisterEntity, Integer> {
	@Query(
			"Select u From ProgramRegisterEntity u "
			)
	Page<ProgramRegisterEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From ProgramRegisterEntity u  Where u.user.id = ?1 "
			)
	Page<ProgramRegisterEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From ProgramRegisterEntityCustom u  Where u.user.id = ?1"
			)
	Page<ProgramRegisterEntityCustom> listPagingByUserIdCustom( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From ProgramRegisterEntity u Where u.user.id = ?1 "
			)
	List<ProgramRegisterEntity> listPagingRegisterByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From ProgramRegisterEntity u Where u.user.id = ?1 and u.program.id = ?2"
			)
	List<ProgramRegisterEntity> listByUserIdAndProgramId( Integer userId, Integer programId);

	@Query(
			value = "select DISTINCT u.id as register_id, p.user_id as author_id, u.nickname, u.phone, p.id, p.title, tl.date_learn "
					+ "from ((program p inner join purchase pu on p.id = pu.program_id) inner join user u on u.id = pu.user_id) left join time_learn tl on tl.program_id = p.id and tl.opening_day =1 "
					+ "where Date(tl.date_learn) = Date(?1) limit ?2, ?3",nativeQuery = true
			)
	List<Object[]> listPagingFeedUser( Date date, Integer from, Integer to);
	
	@Query(
			value = "select count(*) "
					+ "from ((program p inner join purchase pu on p.id = pu.program_id) inner join user u on u.id = pu.user_id) left join time_learn tl on tl.program_id = p.id and tl.opening_day =1  "
					+ "where Date(tl.date_learn) = Date(?1)",nativeQuery = true
			)
	Integer totalFeedUser( Date date);
	
	@Query(
			value = "select DISTINCT u.id as register_id, p.user_id as author_id, u.nickname, u.phone, p.id, p.title, tl.date_learn "
					+ "from ((program p inner join purchase pu on p.id = pu.program_id) inner join user u on u.id = pu.user_id) left join time_learn tl on tl.program_id = p.id and tl.opening_day =1 "
					+ "where Date(tl.date_learn) = Date(?1) and u.nickname like ?2 limit ?3, ?4",nativeQuery = true
			)
	List<Object[]> listPagingFeedUserByNickname( Date date, String name, Integer from, Integer to);
	
	@Query(
			value = "select count(*) "
					+ "from ((program p inner join purchase pu on p.id = pu.program_id) inner join user u on u.id = pu.user_id) left join time_learn tl on tl.program_id = p.id and tl.opening_day =1  "
					+ "where Date(tl.date_learn) = Date(?1) and u.nickname like ?2",nativeQuery = true
			)
	Integer totalFeedUserByNickname( Date date, String name);
	
	@Query(
			value = "select DISTINCT u.id as register_id, p.user_id as author_id, u.nickname, u.phone, p.id, p.title, tl.date_learn "
					+ "from ((program p inner join purchase pu on p.id = pu.program_id) inner join user u on u.id = pu.user_id) left join time_learn tl on tl.program_id = p.id and tl.opening_day =1 "
					+ "where Date(tl.date_learn) = Date(?1) and p.title like ?2 limit ?3, ?4",nativeQuery = true
			)
	List<Object[]> listPagingFeedUserByTitle( Date date, String name, Integer from, Integer to);
	
	@Query(
			value="select count(*) "
					+ "from ((program p inner join purchase pu on p.id = pu.program_id) inner join user u on u.id = pu.user_id) left join time_learn tl on tl.program_id = p.id and tl.opening_day =1  "
					+ "where Date(tl.date_learn) = Date(?1) and p.title like ?2",nativeQuery = true
			)
	Integer totalFeedUserByTitle( Date date, String name);
	
}
