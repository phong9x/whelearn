package org.trams.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.trams.bean.jpa.KeywordEntity;
/**
 * Repository : Keyword.
 */
public interface KeywordJpaRepository extends PagingAndSortingRepository<KeywordEntity, Integer> {
	@Query(
			"Select u From KeywordEntity u "
			)
	Page<KeywordEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From KeywordEntity u "
			)
	List<KeywordEntity> findAllAndorder(Pageable pageable);
	
	
	@Query(
			value = "SELECT * FROM (" +	
							"(select gender as id, k.keyword , count(gender) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.gender = k.id) "+
							"where pu.user_id =?1 and k.type_id = 1 and pu.order_program = 0 group by gender order by total_count DESC, k.priority DESC limit 1) "+
		
							"union "+
							
							"(select age as id, k.keyword , count(age) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.age = k.id) "+
							"where pu.user_id =?1 and k.type_id = 2 and pu.order_program = 0 group by kp.age order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select genitive as id, k.keyword , count(genitive) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.genitive = k.id) "+
							"where pu.user_id =?1 and k.type_id = 3 and pu.order_program = 0 group by kp.genitive order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select experience as id, k.keyword , count(experience) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.experience = k.id) "+
							"where pu.user_id =?1 and k.type_id = 4 and pu.order_program = 0 group by kp.experience order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select place_study as id, k.keyword , count(place_study) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.place_study = k.id) "+
							"where pu.user_id =?1 and k.type_id = 5 and pu.order_program = 0 group by kp.place_study order by total_count DESC, k.priority DESC limit 1) "+
		
							"union "+
							
							"(select area as id, k.keyword , count(area) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.area = k.id) "+
							"where pu.user_id =?1 and k.type_id = 6 and pu.order_program = 0 group by kp.area order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select time1 as id, k.keyword , count(time1) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.time1 = k.id) "+
							"where pu.user_id =?1 and k.type_id = 7 and pu.order_program = 0 group by kp.time1 order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select time2 as id, k.keyword , count(time2) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.time2 = k.id) "+
							"where pu.user_id =?1 and k.type_id = 8 and pu.order_program = 0 group by kp.time2 order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select fee_study as id, k.keyword , count(fee_study) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.fee_study = k.id) "+
							"where pu.user_id =?1 and k.type_id = 9 and pu.order_program = 0 group by kp.fee_study order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select study_mode as id, k.keyword , count(study_mode) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.study_mode = k.id) "+
							"where pu.user_id =?1 and k.type_id = 10 and pu.order_program = 0 group by kp.study_mode order by total_count DESC, k.priority DESC limit 1) "+
							
							"union "+
							
							"(select size_class as id, k.keyword , count(size_class) as total_count, k.type_id , k.priority "+
							"from ((keyword_program kp inner join purchase pu on pu.program_id = kp.program_id ) "+
							"inner join keyword k on kp.size_class = k.id) "+
							"where pu.user_id =?1 and k.type_id = 11 and pu.order_program = 0 group by kp.size_class order by total_count DESC, k.priority DESC limit 1)"+
					"ORDER BY A.total_count DESC, A.id ASC", 
					nativeQuery = true
			)
	List<Object[]> listKeyWordByUserId(Integer userId);
	
	@Query(
			value = "select k.keyword from keyword k group by k.type_id order by k.type_name ASC, k.priority DESC ", nativeQuery = true
			)
	List<String> listKeyWordByUserId();
}
