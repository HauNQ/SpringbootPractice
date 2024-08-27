package com.example.CRUD_Student.service;

import com.example.CRUD_Student.dto.response.PageResponse;
import com.example.CRUD_Student.entity.Address;

import java.util.List;

public interface IAddressService {
    PageResponse<?> getAddress();
}
