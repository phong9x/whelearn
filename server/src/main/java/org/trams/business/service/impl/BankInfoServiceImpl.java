/*
 * Created on 3 11 2016 ( Time 16:33:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.trams.bean.BankInfo;
import org.trams.bean.jpa.BankInfoEntity;
import org.trams.business.service.BankInfoService;
import org.trams.business.service.mapping.BankInfoServiceMapper;
import org.trams.data.repository.jpa.BankInfoJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of BankInfoService
 */
@Component
@Transactional
public class BankInfoServiceImpl implements BankInfoService {

	@Resource
	private BankInfoJpaRepository bankInfoJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private BankInfoServiceMapper bankInfoServiceMapper;
	
	@Override
	public BankInfo findById(Integer id) {
		BankInfoEntity bankInfoEntity = bankInfoJpaRepository.findOne(id);
		return bankInfoServiceMapper.mapBankInfoEntityToBankInfo(bankInfoEntity);
	}

	@Override
	public Page<BankInfoEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return bankInfoJpaRepository.findAll(request);
	}

	@Override
	public List<BankInfo> findAll() {
		Iterable<BankInfoEntity> entities = bankInfoJpaRepository.findAll();
		List<BankInfo> beans = new ArrayList<BankInfo>();
		for(BankInfoEntity bankInfoEntity : entities) {
			beans.add(bankInfoServiceMapper.mapBankInfoEntityToBankInfo(bankInfoEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = bankInfoJpaRepository.count();
		return count;
	}

	@Override
	public BankInfo save(BankInfo bankInfo) {
		return update(bankInfo) ;
	}

	@Override
	public BankInfo create(BankInfo bankInfo) {
/*
		BankInfoEntity bankInfoEntity = bankInfoJpaRepository.findOne(bankInfo.getId());
		if( bankInfoEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		bankInfoEntity = new BankInfoEntity();
		bankInfoServiceMapper.mapBankInfoToBankInfoEntity(bankInfo, bankInfoEntity);
		BankInfoEntity bankInfoEntitySaved = bankInfoJpaRepository.save(bankInfoEntity);
		return bankInfoServiceMapper.mapBankInfoEntityToBankInfo(bankInfoEntitySaved);
*/
		BankInfoEntity bankInfoEntity = new BankInfoEntity();
		bankInfoServiceMapper.mapBankInfoToBankInfoEntity(bankInfo, bankInfoEntity);
		BankInfoEntity bankInfoEntitySaved = bankInfoJpaRepository.save(bankInfoEntity);
		return bankInfoServiceMapper.mapBankInfoEntityToBankInfo(bankInfoEntitySaved);
	}

	@Override
	public BankInfo update(BankInfo bankInfo) {
		BankInfoEntity bankInfoEntity = bankInfoJpaRepository.findOne(bankInfo.getId());
		bankInfoServiceMapper.mapBankInfoToBankInfoEntity(bankInfo, bankInfoEntity);
		BankInfoEntity bankInfoEntitySaved = bankInfoJpaRepository.save(bankInfoEntity);
		return bankInfoServiceMapper.mapBankInfoEntityToBankInfo(bankInfoEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		bankInfoJpaRepository.delete(id);
	}

	public BankInfoJpaRepository getBankInfoJpaRepository() {
		return bankInfoJpaRepository;
	}

	public void setBankInfoJpaRepository(BankInfoJpaRepository bankInfoJpaRepository) {
		this.bankInfoJpaRepository = bankInfoJpaRepository;
	}

	public BankInfoServiceMapper getBankInfoServiceMapper() {
		return bankInfoServiceMapper;
	}

	public void setBankInfoServiceMapper(BankInfoServiceMapper bankInfoServiceMapper) {
		this.bankInfoServiceMapper = bankInfoServiceMapper;
	}

	@Override
	public Page<BankInfoEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return bankInfoJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public BankInfo findByBankCode(String bankCode) {
		BankInfoEntity bankInfoEntity = bankInfoJpaRepository.findByBankCode(bankCode);
		return bankInfoServiceMapper.mapBankInfoEntityToBankInfo(bankInfoEntity);
	}
	
	


}
