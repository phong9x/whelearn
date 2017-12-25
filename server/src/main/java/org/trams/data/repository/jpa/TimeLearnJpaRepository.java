package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.TimeLearnEntity;
/**
 * Repository : TimeLearn.
 */
public interface TimeLearnJpaRepository extends PagingAndSortingRepository<TimeLearnEntity, Integer> {
	@Query(
			"Select u From TimeLearnEntity u "
			)
	Page<TimeLearnEntity> listPaging(Pageable pageable);

	
	@Query(
			"Select u From TimeLearnEntity u where MONTH(u.dateLearn) = ?1 and YEAR(u.dateLearn) = ?2 and u.program.isDelete = 0"
			)
	List<TimeLearnEntity> findByMontYear( Integer month, Integer year, Pageable pageable);
	
	@Query(
			"Select u From TimeLearnEntity u where MONTH(u.dateLearn) = ?1 and YEAR(u.dateLearn) = ?2 and u.program.user.id = ?3 and u.program.isDelete = 0"
			)
	List<TimeLearnEntity> findByMontYearAndUserId( Integer month, Integer year, Integer user_id, Pageable pageable);

	@Query(
			"Select u From TimeLearnEntity u where u.program.id = ?1"
			)
	List<TimeLearnEntity> findByProgramId( Integer program_id);
	
	@Modifying
	@Query(
			value = "delete from time_learn  where program_id = ?1", nativeQuery = true
			)
	void deleteByProgramId( Integer program_id);
	
	@Query(
			value = "select p.title from time_learn tl1 inner join program p on p.id = tl1.program_id where  DATE(?1) = (SELECT MAX(tl2.date_learn) from time_learn tl2 where tl2.program_id = p.id ) and HOUR(tl1.to_time) = HOUR(?2)", nativeQuery = true
			)
	List<String> findTitleFinishProgram( Date date, Integer hour);
	
	@Query(
			value = "INSERT into time_learn  ('program_id',	date_learn	, 'from', 'to', opening_day,create_date, update_date) values (?1, ?2,?3, ?4, ?5, ?6, ?7 ) ",nativeQuery =true
			)
	void createTimeLearn(Integer programId, Date date_learn, Date from, Date to, Integer opening_day, Date create_date, Date update_date);
}
