package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.QuestionAnswerEntity;
/**
 * Repository : QuestionAnswer.
 */
public interface QuestionAnswerJpaRepository extends PagingAndSortingRepository<QuestionAnswerEntity, Integer> {
	@Query(
			"Select u From QuestionAnswerEntity u "
			)
	Page<QuestionAnswerEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From QuestionAnswerEntity u where u.programId = ?1 "
			)
	Page<QuestionAnswerEntity> listPagingByProgramId(Integer programId, Pageable pageable);
	
	@Query(
			value ="select a.nickname as ask_nickname,b.nickname as answer_nickname, c.ask_date, c.answer_date, c.questioin, c.answer, c.id ,c.ask_user_id, c.answer_user_id, "
					+ "c.program_id, a.email as ask_email, b.email as answer_email from (user a inner join question_answer c on a.id =c.ask_user_id) left join user b on b.id = c.answer_user_id "
					+ "where  c.program_id =?1 order by c.create_date DESC limit ?2,?3", nativeQuery = true
			)
	List<Object[]> listByProgramId(Integer programId, Integer from, Integer to);
	
	@Query(
			value ="select count(*) from "
					+ "question_answer c where c.program_id =?1", nativeQuery = true
			)
	Integer totalByProgramId(Integer programId);
}
