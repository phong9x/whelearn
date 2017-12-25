package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Date;
import java.util.List;

import org.joda.time.Hours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.jpa.SettingEntity;
/**
 * Repository : Setting.
 */
public interface SettingJpaRepository extends PagingAndSortingRepository<SettingEntity, Integer> {
	@Query(
			"Select u From SettingEntity u "
			)
	Page<SettingEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From SettingEntity u Where u.id = ?1 "
			)
	Page<SettingEntity> listPagingByUserId( Integer userId, Pageable pageable);

	SettingEntity findById( Integer userId);
	
	@Modifying
	@Query(
			value="select u.fcm_token from setting s INNER JOIN user u ON u.id= s.id where s.upload_content_notification = 1 and u.fcm_token is not null and u.role = 0",nativeQuery = true
			)
	List<String> getFCMTokenBy_UploadContentNotification();
	
	@Modifying
	@Query(
			value="select u.fcm_token from setting s INNER JOIN user u ON u.id= s.id where s.open_course_notification = 1 and u.fcm_token is not null and u.role = 0",nativeQuery = true
			)
	List<String> getFCMTokenBy_OpenCourseNotification();
	
	
	@Query(
			value="select DISTINCT pr.id, pr.title, u.fcm_token from (((setting s INNER JOIN user u ON u.id= s.id)  "
				 +"inner join purchase p on p.user_id = s.id) inner join program pr on pr.id = p.program_id ) "
				 +"where s.comment_notification = 1 and u.fcm_token is not null and u.role = 0  and p.order_program = 0 and "
				 + "DATE(?1) = (SELECT MAX(DATE(tl2.date_learn)) from time_learn tl2 where tl2.program_id = pr.id  and HOUR(tl2.to_time ) = HOUR(?1) and MINUTE(tl2.to_time ) = MINUTE(?1)) order by pr.id ",nativeQuery = true
			)
	List<Object[]> getFCMTokenBy_CommentNotification(String now);
	
	
	
	@Modifying
	@Query(
			value="select u.fcm_token from setting s INNER JOIN user u ON u.id= s.id where s.notice_notification = 1 and u.fcm_token is not null",nativeQuery = true
			)
	List<String> getFCMTokenBy_NoticeNotification();
	
	@Modifying
	@Query(
			value="select p.id, p.title, u.fcm_token from user u inner join program p on p.user_id = u.id where DATE(p.dead_line) = ?1 "
					+ "and HOUR(p.dead_line) = ?2 and u.fcm_token is not null and u.is_delete = 0",nativeQuery = true
			)
	List<Object[]> getFCMTokenBy_FinishRegisterCourseNotification(Date deadLine, Integer hour );
	
	@Modifying
	@Query(
			value="select u.fcm_token from setting s INNER JOIN user u ON u.id= s.id where s.finish_pay_course_notification = 1 and u.fcm_token is not null and u.role = 1",nativeQuery = true
			)
	List<String> getFCMTokenBy_FinishPayCourseNotification();
	
	@Modifying
	@Query(
			value="select u.fcm_token from setting s INNER JOIN user u ON u.id= s.id where s.post_question_course_notification = 1 and u.fcm_token is not null and u.role = 1",nativeQuery = true
			)
	List<String> getFCMTokenBy_PostQuestionCourseNotification();
	
	@Modifying
	@Query(
			value="select u.fcm_token from setting s INNER JOIN user u ON u.id= s.id where s.post_comment_course_notification = 1 and u.fcm_token is not null and u.role = 1",nativeQuery = true
			)
	List<String> getFCMTokenBy_PostCommentCourseNotification();
	
	@Modifying
	@Query(
			value="select DISTINCT u.fcm_token  from user u inner join coupon_use cu on cu.user_id = u.id  "
					+ "where  cu.status_use = 0 and u.fcm_token is not null and u.push_fcm_token = 1 "
					+ "and  DATEDIFF(cu.deadline,NOW() ) = ?1 and (cu.coupon_id =1 or cu.coupon_id =2 or cu.coupon_id =3 or cu.coupon_id =4)",nativeQuery = true
			)
	List<String> getFCMTokenBy_CouponNotification_Join(Integer countDate);
	
	@Modifying
	@Query(
			value="select DISTINCT u.fcm_token  from user u inner join coupon_use cu on cu.user_id = u.id  "
					+ "where  cu.status_use = 0 and u.fcm_token is not null and u.push_fcm_token = 1 "
					+ "and  DATEDIFF(cu.deadline,NOW() ) = ?1 and cu.coupon_id = ?2 ",nativeQuery = true
			)
	List<String> getFCMTokenBy_CouponNotification_Use_Comment(Integer countDate, Integer couponId);
}
