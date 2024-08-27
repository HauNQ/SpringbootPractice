package com.laptrinhjavaweb.converter;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.impl.NewService;

@Component
public class NewConverter {
	private INewService newservice;
	
	public void abc() {
	   Long[] ids = new Long[] {};
	   newservice.delete(ids);
	}
	
    public NewEntity toEntity(NewDTO dto) {
    	NewEntity entity = new NewEntity();
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		entity.setThumbnail(dto.getThumbnail());
		return entity;
    }
    
    public NewDTO toDTO(NewEntity entity) {
		NewDTO dto = new NewDTO();
		if(entity.getId() != null) dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setContent(entity.getContent());
		dto.setShortDescription(entity.getShortDescription());
		dto.setThumbnail(entity.getThumbnail());
	    dto.setCategoryCode(entity.getCategory().getCode());
	    dto.setCreatedBy(entity.getCreatedBy());
	    dto.setCreatedDate(entity.getCreatedDate());
	    dto.setModifiedBy(entity.getModifiedBy());
	    dto.setModifiedDate(entity.getModifiedDate());
		return dto;
	}
    
    public NewEntity toEntity(NewDTO dto, NewEntity entity) {
    	
		entity.setTitle(dto.getTitle());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		entity.setThumbnail(dto.getThumbnail());
		return entity;
	}
}
