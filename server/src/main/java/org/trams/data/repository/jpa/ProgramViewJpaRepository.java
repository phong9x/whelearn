package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.ProgramViewEntity;
/**
 * Repository : ProgramView.
 */
public interface ProgramViewJpaRepository extends PagingAndSortingRepository<ProgramViewEntity, Integer> {
	@Query(
			"Select u From ProgramViewEntity u "
			)
	Page<ProgramViewEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From ProgramViewEntity u Where u.user.id = ?1 "
			)
	Page<ProgramViewEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From ProgramViewEntity u Where u.user.id = ?1 and u.program.id = ?2"
			)
	List<ProgramViewEntity> listPagingByUserIdAndProgramId( Integer userId, Integer programId);

	@Query(
			"Select u From ProgramViewEntity u Where u.user.nickname like ?1 and u.program.title like ?2"
			)
	Page<ProgramViewEntity> listPagingByUsernameAndTitle( String nickname, String title , Pageable pageable);

}
