/*
 * Created on 5 thg 9 2016 ( Time 18:18:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.bean.ProgramRegister;
import org.trams.bean.jpa.ProgramRegisterEntity;
import org.trams.bean.jpa.UserEntity;
import org.trams.bean.jpa.ProgramEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class ProgramRegisterServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public ProgramRegisterServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'ProgramRegisterEntity' to 'ProgramRegister'
	 * @param programRegisterEntity
	 */
	public ProgramRegister mapProgramRegisterEntityToProgramRegister(ProgramRegisterEntity programRegisterEntity) {
		if(programRegisterEntity == null) {
			return null;
		}

		//--- Generic mapping 
		ProgramRegister programRegister = map(programRegisterEntity, ProgramRegister.class);

		//--- Link mapping ( link to User )
		if(programRegisterEntity.getUser() != null) {
			programRegister.setUserId(programRegisterEntity.getUser().getId());
		}
		//--- Link mapping ( link to Program )
		if(programRegisterEntity.getProgram() != null) {
			programRegister.setProgramId(programRegisterEntity.getProgram().getId());
		}
		return programRegister;
	}
	
	/**
	 * Mapping from 'ProgramRegister' to 'ProgramRegisterEntity'
	 * @param programRegister
	 * @param programRegisterEntity
	 */
	public void mapProgramRegisterToProgramRegisterEntity(ProgramRegister programRegister, ProgramRegisterEntity programRegisterEntity) {
		if(programRegister == null) {
			return;
		}

		//--- Generic mapping 
		map(programRegister, programRegisterEntity);

		//--- Link mapping ( link : programRegister )
		if( hasLinkToUser(programRegister) ) {
			UserEntity user1 = new UserEntity();
			user1.setId( programRegister.getUserId() );
			programRegisterEntity.setUser( user1 );
		} else {
			programRegisterEntity.setUser( null );
		}

		//--- Link mapping ( link : programRegister )
		if( hasLinkToProgram(programRegister) ) {
			ProgramEntity program2 = new ProgramEntity();
			program2.setId( programRegister.getProgramId() );
			programRegisterEntity.setProgram( program2 );
		} else {
			programRegisterEntity.setProgram( null );
		}

	}
	
	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(ProgramRegister programRegister) {
		if(programRegister.getUserId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Program id is valid.
	 * @param Program Program
	 * @return boolean
	 */
	private boolean hasLinkToProgram(ProgramRegister programRegister) {
		if(programRegister.getProgramId() != null) {
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