package org.trams.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.bean.jpa.BankInfoEntity;
/**
 * Repository : BankInfo.
 */
public interface BankInfoJpaRepository extends PagingAndSortingRepository<BankInfoEntity, Integer> {
	@Query(
			"Select u From BankInfoEntity u "
			)
	Page<BankInfoEntity> listPaging(Pageable pageable);


	BankInfoEntity findByBankCode(String bankCode);
}
