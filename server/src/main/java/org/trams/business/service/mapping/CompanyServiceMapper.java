/*
 * Created on 26 thg 9 2016 ( Time 11:55:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.bean.Company;
import org.trams.bean.jpa.CompanyEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CompanyServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CompanyServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CompanyEntity' to 'Company'
	 * @param companyEntity
	 */
	public Company mapCompanyEntityToCompany(CompanyEntity companyEntity) {
		if(companyEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Company company = map(companyEntity, Company.class);

		return company;
	}
	
	/**
	 * Mapping from 'Company' to 'CompanyEntity'
	 * @param company
	 * @param companyEntity
	 */
	public void mapCompanyToCompanyEntity(Company company, CompanyEntity companyEntity) {
		if(company == null) {
			return;
		}

		//--- Generic mapping 
		map(company, companyEntity);

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