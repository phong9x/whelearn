package org.trams.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trams.bean.User;
import org.trams.bean.jpa.UserEntity;
/**
 * Repository : User.
 */
public interface UserJpaRepository extends PagingAndSortingRepository<UserEntity, Integer> {
	@Query(
			"Select u From UserEntity u Where u.isDelete = 0"
			)
	Page<UserEntity> listPaging(Pageable pageable);

	@Modifying
	@Query(
			value="UPDATE user SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);
	
	@Query(
			"Select u From UserEntity u Where u.email = ?1 and u.password = ?2 and u.isDelete = 0"
			)
	UserEntity login(String username, String password);
	
	@Query(
			"Select u From UserEntity u Where u.email = ?1 and u.password = ?2 and (u.role = 1 or u.role = 2) and u.isDelete = 0"
			)
	UserEntity loginAdminPage(String username, String password);
	
	@Query(
			"Select u From UserEntity u Where u.email = ?1 "
			)
	List<UserEntity> findByEmail(String username);
	
	@Query(
			"Select u From UserEntity u Where u.fullname like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByFullname(String fullname, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.dayOfBirth = ?1 and u.gender = ?2 and u.isDelete = 0"
			)
	List<UserEntity> findByDayOfBirthAndGender(Date dayOfBirth, Short gender);
	
	@Query(
			"Select u From UserEntity u Where u.dayOfBirth = ?1 and u.gender = ?2 and u.email = ?3 and u.isDelete = 0"
			)
	UserEntity findByDayOfBirthAndGenderAndEmail(Date dayOfBirth, Short gender, String email);
	
	@Query(
			"Select u From UserEntity u Where u.nickname like ?1 and u.email like ?2 and u.isDelete = 0"
			)
	Page<UserEntity> findByNicknameAndEmail(String nickname, String email, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.nickname like ?1 and u.email like ?2 and u.role = ?3 and u.isDelete = 0"
			)
	Page<UserEntity> findByNicknameAndEmailAndType(String nickname, String email,Integer role, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.role = ?1 and u.isDelete = 0 "
			)
	Page<UserEntity> listPagingByType(Integer role, Pageable pageable);
	
	@Modifying
	@Query(
			value = "update user set push_fcm_token = 0 where fcm_token = ?1", nativeQuery = true
			)
	void updatePushFcmToken(String fcm_token);
	
	
	
	
}
