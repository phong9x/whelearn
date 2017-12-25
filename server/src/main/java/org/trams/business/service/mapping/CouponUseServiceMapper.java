/*
 * Created on 31 thg 8 2016 ( Time 16:06:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.bean.CouponUse;
import org.trams.bean.jpa.CouponUseEntity;
import org.trams.bean.jpa.CouponEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CouponUseServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CouponUseServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CouponUseEntity' to 'CouponUse'
	 * @param couponUseEntity
	 */
	public CouponUse mapCouponUseEntityToCouponUse(CouponUseEntity couponUseEntity) {
		if(couponUseEntity == null) {
			return null;
		}

		//--- Generic mapping 
		CouponUse couponUse = map(couponUseEntity, CouponUse.class);

		//--- Link mapping ( link to Coupon )
		if(couponUseEntity.getCoupon() != null) {
			couponUse.setCouponId(couponUseEntity.getCoupon().getId());
		}
		return couponUse;
	}
	
	/**
	 * Mapping from 'CouponUse' to 'CouponUseEntity'
	 * @param couponUse
	 * @param couponUseEntity
	 */
	public void mapCouponUseToCouponUseEntity(CouponUse couponUse, CouponUseEntity couponUseEntity) {
		if(couponUse == null) {
			return;
		}

		//--- Generic mapping 
		map(couponUse, couponUseEntity);

		//--- Link mapping ( link : couponUse )
		if( hasLinkToCoupon(couponUse) ) {
			CouponEntity coupon1 = new CouponEntity();
			coupon1.setId( couponUse.getCouponId() );
			couponUseEntity.setCoupon( coupon1 );
		} else {
			couponUseEntity.setCoupon( null );
		}

	}
	
	/**
	 * Verify that Coupon id is valid.
	 * @param Coupon Coupon
	 * @return boolean
	 */
	private boolean hasLinkToCoupon(CouponUse couponUse) {
		if(couponUse.getCouponId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}