/*
 * Created on 23 thg 8 2016 ( Time 16:20:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.bean.Event;
import org.trams.bean.jpa.EventEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class EventServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public EventServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'EventEntity' to 'Event'
	 * @param eventEntity
	 */
	public Event mapEventEntityToEvent(EventEntity eventEntity) {
		if(eventEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Event event = map(eventEntity, Event.class);

		return event;
	}
	
	/**
	 * Mapping from 'Event' to 'EventEntity'
	 * @param event
	 * @param eventEntity
	 */
	public void mapEventToEventEntity(Event event, EventEntity eventEntity) {
		if(event == null) {
			return;
		}

		//--- Generic mapping 
		map(event, eventEntity);

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