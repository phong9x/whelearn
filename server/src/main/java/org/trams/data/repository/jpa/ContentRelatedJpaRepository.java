package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.ContentRelatedEntity;
import org.trams.bean.jpa.ProgramEntity;
/**
 * Repository : ContentRelated.
 */
public interface ContentRelatedJpaRepository extends PagingAndSortingRepository<ContentRelatedEntity, Integer> {
	@Query(
			"Select u From ContentRelatedEntity u "
			)
	Page<ContentRelatedEntity> listPaging(Pageable pageable);

	@Query(
			"Select u.program From ContentRelatedEntity u where u.content.id = ?1 and u.program.isDelete = 0"
			)
	List<ProgramEntity> findProgramRelateByContentId(Integer contentId);
	
	@Modifying
	@Query(
			value= "delete from content_related where content_related_id = ?1", nativeQuery = true
			)
	void deleteByContentId(Integer contentRelatedId);
}
