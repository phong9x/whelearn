package org.trams.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.trams.bean.jpa.ProgramEntity;
import org.trams.bean.jpa.ProgramEntityCustom;
/**
 * Repository : Program.
 */
public interface ProgramJpaRepository extends PagingAndSortingRepository<ProgramEntity, Integer> {
	@Query(
			"Select u From ProgramEntity u Where u.isDelete = 0"
			)
	Page<ProgramEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From ProgramEntity u Where u.user.id = ?1 and u.isDelete = 0"
			)
	Page<ProgramEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntityCustom u Where u.user.id = ?1 and u.isDelete = 0"
			)
	Page<ProgramEntityCustom> listPagingByUserIdCustom( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where MONTH(u.deadLine) = ?1 and YEAR(u.deadLine) = ?2 and u.isDelete = 0"
			)
	List<ProgramEntity> findByMonth( Integer month, Integer year);

	@Modifying
	@Query(
			value="UPDATE program SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);
	
	@Modifying
	@Query(
			value="UPDATE program SET point = (SELECT AVG(point) FROM comment where user_id = ?1 and thread_id = ?2 and type =2) WHERE id = ?2",nativeQuery=true
			)
	void updatePointProgram(Integer userId, Integer programId);
	
	@Modifying
	@Query(
			value="UPDATE program SET like_number = (SELECT count(*) FROM likes where user_id = ?1 and thread_id = ?2 and type =2) WHERE id = ?2",nativeQuery=true
			)
	void updateLike(Integer userId, Integer programId);
	
	@Query(
			"Select u From ProgramEntity u Where u.title like ?1 and u.isDelete = 0"
			)
	Page<ProgramEntity> findByTitle(String title, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.categoryName like ?1 and u.isDelete = 0"
			)
	Page<ProgramEntity> findByCategoryName(String categoryName, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.categoryId = ?1 and u.isDelete = 0"
			)
	Page<ProgramEntity> findByCategoryId(Integer catId, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.categoryId in ?1 and u.isDelete = 0"
			)
	Page<ProgramEntity> findByListCategoryId(Integer[] catId, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.id = ?1 and u.isDelete = 0"
			)
	ProgramEntity findByIdEntity(Integer id);
	
	@Query(
			value = "SELECT p.id as id, p.title as title, p.category_name as categoryName, p.summary, p.image_url, p.video_url FROM program p where p.category_id = ?1 and p.id != ?2 and p.is_delete = 0 ORDER BY RAND() LIMIT 3",nativeQuery = true
			)
	List<Object[]> findByProgramRelative(Integer categoryId,Integer id);
	
	@Modifying
	@Query(
			value= "Select p.id, p.category_name, p.title as title, p.summary, p.fee, p.total_people, p.image_url, p.video_url, p.total_time from likes l, program p where l.thread_id = p.id and l.user_id = ?1 and l.type = 2 and p.is_delete = 0 limit ?2,?3 ",nativeQuery = true
			)
	List<Object[]> findProgramLike(Integer userId, Integer from, Integer to);
	
	@Query(
			value= "Select count(*) from likes l, program p where l.thread_id = p.id and l.user_id = ?1 and l.type = 2",nativeQuery = true
			)
	Integer totalCountProgramLike(Integer userId);
	
	@Query(
			"Select u From ProgramEntity u Where u.isDelete = 0"
			)
	Page<ProgramEntity> listPagingCaculate(Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.title like ?1 and  u.isDelete = 0"
			)
	Page<ProgramEntity> listPagingCaculateByTitle(String title, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.statusPayment = ?1 and  u.isDelete = 0"
			)
	Page<ProgramEntity> listPagingCaculateByStatus(Integer status, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.user.id =?1 and u.isDelete = 0"
			)
	Page<ProgramEntity> listPagingCaculateByUserId(Integer userId,Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.user.id = ?1 and u.title like ?2 and  u.isDelete = 0"
			)
	Page<ProgramEntity> listPagingCaculateByTitleAndUserId(Integer userId,String title, Pageable pageable);
	
	@Query(
			"Select u From ProgramEntity u Where u.user.id = ?1 and u.statusPayment = ?2 and  u.isDelete = 0"
			)
	Page<ProgramEntity> listPagingCaculateByStatusAndUserId(Integer userId,Integer status, Pageable pageable);
	@Modifying
	@Query(
			value = "update program p set p.total_money = (select sum(pu.total_money) from purchase pu where pu.program_id = ?1 and pu.order_program = 0) where p.id = ?1",nativeQuery = true
			)
	void updateTotalMoney( Integer programId);
	
	@Query(
			value = "select p.id, p.category_name, p.title , p.summary , p.fee, p.total_people,  p.image_url, p.video_url, "
					+ "(select count(pu.id) from purchase pu where p.id=pu.program_id) as count from program p "
					+ "where p.category_id = (SELECT category.id from category order by category.count DESC limit 1) order by count DESC limit 3",nativeQuery = true
			)
	List<Object[]> listWhelearnB_NotLogin();
	
	@Query(
			value = "select DISTINCT p.id, p.category_name, p.title , p.summary , p.fee, p.total_people,  p.image_url, p.video_url,  count(pu.id) as total_count "
					+ "from purchase pu inner join program p on p.id = pu.program_id where pu.order_program = 0 "
					+ "GROUP BY p.user_id order by total_count DESC limit 3",nativeQuery = true
			)
	List<Object[]> listWhelearnC_NotLogin();
	
	
	@Query(
			value = "select p.id, p.category_name, p.title , p.summary , p.fee, p.total_people,  p.image_url, p.video_url,  count(pu.id) as total_count "
					+ " from (purchase pu inner join program p on p.id = pu.program_id) inner join keyword_program k on k.program_id = p.id where k.time1 = ?1 or k.time2 =?2 and pu.order_program = 0"
					+ " GROUP BY p.user_id order by total_count DESC limit 3",nativeQuery = true
			)
	List<Object[]> listWhelearnD_NotLogin(Integer time1, Integer time2);
	
	@Query(
			value = "select p.id, p.category_name, p.title , p.summary , p.fee, p.total_people,  p.image_url, p.video_url,  count(pu.id) as total_count "
					+ " from (purchase pu inner join program p on p.id = pu.program_id) inner join keyword_program k on k.program_id = p.id where k.fee_study =?1  and pu.order_program = 0"
					+ " GROUP BY p.user_id order by total_count DESC limit 3 ",nativeQuery = true
			)
	List<Object[]> listWhelearnE_NotLogin(Integer fee_study);
	
	@Query(
			value = "select p.id, p.category_name, p.title , p.summary , p.fee, p.total_people,  p.image_url, p.video_url,  count(pu.id) as total_count "
					+ " from (purchase pu inner join program p on p.id = pu.program_id) inner join keyword_program k on k.program_id = p.id where k.place_study =?1 and k.area =?2 and pu.order_program = 0"
					+ " GROUP BY p.user_id order by total_count DESC limit 3 ",nativeQuery = true
			)
 	List<Object[]> listWhelearnF_NotLogin(Integer place_study, Integer area);
 	
 	@Query(
			value = "select p.id, p.category_name, p.title , p.summary , p.fee, p.total_people,  p.image_url, p.video_url,  count(pu.id) as total_count "
					+ " from purchase pu inner join program p on p.id = pu.program_id where p.category_id = (select id from category c order by c.count DESC, c.category_name ASC limit 1)  "
					+ " and pu.order_program = 0 GROUP BY p.id order by total_count DESC limit 3",nativeQuery = true
			)
 	List<Object[]> listWhelearnB_Login();

	
	@Query(
			value = "SELECT k.id , k.keyword,k.type_id, (select count(pu.id) from keyword_program kp inner join purchase pu on pu.program_id = kp.program_id "
					+ " and pu.order_program =0 where k.id in (kp.gender,kp.age,kp.genitive,kp.experience)) as count_2 from keyword k"
					+ " where k.type_id in (1, 2, 3,4 ) GROUP BY k.type_id ORDER BY k.type_id ASC,k.priority DESC, k.keyword ASC ",nativeQuery = true
			)
	List<Object[]> listKeywordWhelearnC();
	
	@Query(
			value = "SELECT k.id , k.keyword,k.type_id, (select count(pu.id) from keyword_program kp inner join purchase pu on pu.program_id = kp.program_id "
					+ " and pu.order_program =0 where k.id in (kp.time1,kp.time2) ) as count_2 from keyword k"
					+ " where k.type_id in ( 7, 8) GROUP BY k.type_id ORDER BY k.type_id ASC,k.priority DESC, k.keyword ASC limit 3",nativeQuery = true
			)
	List<Object[]> listKeywordWhelearnD();
	
	@Query(
			value = "SELECT k.id , k.keyword,k.type_id, (select count(pu.id) from keyword_program kp inner join purchase pu on pu.program_id = kp.program_id "
					+ " and pu.order_program =0 where k.id = kp.fee_study) as count_2 from keyword k"
					+ " where k.type_id = 9 GROUP BY k.type_id ORDER BY k.type_id ASC,k.priority DESC, k.keyword ASC ",nativeQuery = true
			)
	List<Object[]> listKeywordWhelearnE();
	
	@Query(
			value = "SELECT k.id , k.keyword,k.type_id, (select count(pu.id) from keyword_program kp inner join purchase pu on pu.program_id = kp.program_id "
					+ " and pu.order_program =0 where k.id in (kp.place_study, kp.area)) as count_2 from keyword k"
					+ " where k.type_id in (5,6 ) GROUP BY k.type_id ORDER BY k.type_id ASC,k.priority DESC, k.keyword ASC ",nativeQuery = true
			)
	List<Object[]> listKeywordWhelearnF();
	
	
	@Query(
			value = "select id from category order by count DESC, id ASC limit 1",nativeQuery = true
			)
	Integer getCategoryId_Top1();
	
	@Query(
			value = "SELECT p.id, p.category_name, p.title , p.summary , p.fee, p.total_people,  p.image_url, p.video_url, "
					+ " k1.id as id1, k2.id as id2, k3.id as id3, k4.id as id4, k5.id as id5, k6.id as id6, k7.id as id7, k8.id as id8, k9.id as id9, k10.id as id10, k11.id as id11"
					+ " from  keyword_program kp inner join program p on kp.program_id = p.id"
					+ " inner join keyword k1 on k1.id = kp.gender"
					+ " inner join keyword k2 on k2.id = kp.age"
					+ " inner join keyword k3 on k3.id = kp.genitive"
					+ " inner join keyword k4 on k4.id = kp.experience"
					+ " inner join keyword k5 on k5.id = kp.place_study"
					+ " inner join keyword k6 on k6.id = kp.area"
					+ " inner join keyword k7 on k7.id = kp.time1"
					+ " inner join keyword k8 on k8.id = kp.time2"
					+ " inner join keyword k9 on k9.id = kp.fee_study"
					+ " inner join keyword k10 on k10.id = kp.study_mode"
					+ " inner join keyword k11 on k11.id = kp.size_class"
					+ " where  kp.program_id in (select p.id from program p where p.category_id = ?1) ",nativeQuery = true
			)
	List<Object[]> listWhelearn_getProgram_byCategoryId(Integer categoryId);
	
}
