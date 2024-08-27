package com.example.CRUD_Student.service;

import com.example.CRUD_Student.dao.AddressRepository;
import com.example.CRUD_Student.dao.SearchRepository;
import com.example.CRUD_Student.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressService implements IAddressService{
    private final AddressRepository addressRepository;
    private final SearchRepository searchRepository;

    @Override
    public PageResponse<?> getAddress() {
       return searchRepository.getSearchedAddress();
    }
}
