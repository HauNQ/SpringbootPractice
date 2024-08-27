package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.converter.NewConverter;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.service.INewService;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Autowired
	private NewConverter newConverter;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public NewDTO save(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();

		if (newDTO.getId() != null) {
			newEntity = newRepository.findOne(newDTO.getId());
			newEntity = newConverter.toEntity(newDTO, newEntity);
		} else {
			newEntity = newConverter.toEntity(newDTO);
		}
		newEntity.setCategory(categoryRepository.findOneByCode(newDTO.getCategoryCode()));
		newEntity = newRepository.save(newEntity);

		return newConverter.toDTO(newEntity);
	}

	@Override
	public void delete(Long ud) {
		for (Long id : ids) {
			newRepository.delete(id);
		}
	}

	@Override
	public int totalItem() {
		return (int) newRepository.count();
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewDTO> result = new ArrayList<>();
		List<NewEntity> list = newRepository.findAll(pageable).getContent();
		list.forEach(newEntity -> result.add(newConverter.toDTO(newEntity)));

		return result;
	}

	@Override
	public void delete(Long[] ids) {
	dasdasdasdadasdasda;asdasdad
	}

//	@Override
//	public NewDTO update(NewDTO model) {
//		NewEntity oldNew = newRepository.findOne(model.getId());
//		NewEntity updatedNew = newConverter.toEntity(model, oldNew);
//		updatedNew.setCategory(categoryRepository.findOneByCode(model.getCategoryCode()));
//		updatedNew = newRepository.save(updatedNew);
//		return newConverter.toDTO(updatedNew);
//	}

}
