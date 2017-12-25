package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.ContentEntity;
/**
 * Repository : Content.
 */
public interface ContentJpaRepository extends PagingAndSortingRepository<ContentEntity, Integer> {
	@Query(
			"Select u From ContentEntity u Where u.isDelete = 0"
			)
	Page<ContentEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From ContentEntity u Where u.title like ?1 and u.isDelete = 0"
			)
	Page<ContentEntity> findByTitle(String title, Pageable pageable);
	
	@Query(
			"Select u From ContentEntity u Where u.categoryId = ?1 and u.isDelete = 0"
			)
	Page<ContentEntity> findByCategoryId(Integer categoryId, Pageable pageable);
	
	@Query(
			"Select u From ContentEntity u Where u.categoryName like ?1 and u.isDelete = 0"
			)
	Page<ContentEntity> findByCategoryName(String categoryName, Pageable pageable);
	
	@Modifying
	@Query(
			value="UPDATE content SET like_number = (SELECT count(*) FROM likes where user_id = ?1 and thread_id = ?2 and type =1) WHERE id = ?2",nativeQuery=true
			)
	void updateLike(Integer userId, Integer programId);
	
	@Modifying
	@Query(
			value="UPDATE content SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void deleteContent(Integer programId);
	
	@Query(
			value= "Select c.id, c.category_name, c.title, c.summary, c.image_url, c.video_url from likes l, content c where l.thread_id = c.id and l.user_id = ?1 and l.type = 1 and c.is_delete =0 limit ?2,?3",nativeQuery = true
			)
	List<Object[]> findContentLike(Integer userId, Integer from, Integer to);
	
	@Query(
			value= "Select count(*) from likes l, content c where l.thread_id = c.id and l.user_id = ?1 and l.type = 1 and c.is_delete =0",nativeQuery = true
			)
	Integer totalCountContentLike(Integer userId);
	
}
