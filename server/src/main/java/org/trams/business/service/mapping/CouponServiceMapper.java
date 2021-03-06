/*
 * Created on 31 thg 8 2016 ( Time 16:06:59 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.bean.Coupon;
import org.trams.bean.jpa.CouponEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CouponServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CouponServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CouponEntity' to 'Coupon'
	 * @param couponEntity
	 */
	public Coupon mapCouponEntityToCoupon(CouponEntity couponEntity) {
		if(couponEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Coupon coupon = map(couponEntity, Coupon.class);

		return coupon;
	}
	
	/**
	 * Mapping from 'Coupon' to 'CouponEntity'
	 * @param coupon
	 * @param couponEntity
	 */
	public void mapCouponToCouponEntity(Coupon coupon, CouponEntity couponEntity) {
		if(coupon == null) {
			return;
		}

		//--- Generic mapping 
		map(coupon, couponEntity);

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