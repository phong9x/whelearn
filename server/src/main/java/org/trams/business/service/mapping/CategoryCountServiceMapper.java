package org.trams.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.bean.CategoryCount;
import org.trams.bean.jpa.CategoryCountEntity;
import org.trams.bean.jpa.UserEntity;
import org.trams.bean.jpa.CategoryEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CategoryCountServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CategoryCountServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CategoryCountEntity' to 'CategoryCount'
	 * @param categoryCountEntity
	 */
	public CategoryCount mapCategoryCountEntityToCategoryCount(CategoryCountEntity categoryCountEntity) {
		if(categoryCountEntity == null) {
			return null;
		}

		//--- Generic mapping 
		CategoryCount categoryCount = map(categoryCountEntity, CategoryCount.class);

		//--- Link mapping ( link to User )
		if(categoryCountEntity.getUser() != null) {
			categoryCount.setUserId(categoryCountEntity.getUser().getId());
		}
		//--- Link mapping ( link to Category )
		if(categoryCountEntity.getCategory() != null) {
			categoryCount.setCategoryId(categoryCountEntity.getCategory().getId());
		}
		return categoryCount;
	}
	
	/**
	 * Mapping from 'CategoryCount' to 'CategoryCountEntity'
	 * @param categoryCount
	 * @param categoryCountEntity
	 */
	public void mapCategoryCountToCategoryCountEntity(CategoryCount categoryCount, CategoryCountEntity categoryCountEntity) {
		if(categoryCount == null) {
			return;
		}

		//--- Generic mapping 
		map(categoryCount, categoryCountEntity);

		//--- Link mapping ( link : categoryCount )
		if( hasLinkToUser(categoryCount) ) {
			UserEntity user1 = new UserEntity();
			user1.setId( categoryCount.getUserId() );
			categoryCountEntity.setUser( user1 );
		} else {
			categoryCountEntity.setUser( null );
		}

		//--- Link mapping ( link : categoryCount )
		if( hasLinkToCategory(categoryCount) ) {
			CategoryEntity category2 = new CategoryEntity();
			category2.setId( categoryCount.getCategoryId() );
			categoryCountEntity.setCategory( category2 );
		} else {
			categoryCountEntity.setCategory( null );
		}

	}
	
	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(CategoryCount categoryCount) {
		if(categoryCount.getUserId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Category id is valid.
	 * @param Category Category
	 * @return boolean
	 */
	private boolean hasLinkToCategory(CategoryCount categoryCount) {
		if(categoryCount.getCategoryId() != null) {
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