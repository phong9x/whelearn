package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.sql.Time;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.KeywordProgramEntity;
/**
 * Repository : KeywordProgram.
 */
public interface KeywordProgramJpaRepository extends PagingAndSortingRepository<KeywordProgramEntity, Integer> {
	@Query(
			"Select u From KeywordProgramEntity u "
			)
	Page<KeywordProgramEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From KeywordProgramEntity u where u.program.id = ?1 "
			)
	KeywordProgramEntity findByProgramId(Integer programId);
	
	
}
