package com.laptrinhjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.NewDTO;

public interface INewService {
    NewDTO save(NewDTO model);
//    NewDTO update(NewDTO model);
    void delete(Long ids[]);
    List<NewDTO> findAll(Pageable pageable);
    int totalItem();
}
