package com.example.CRUD_Student.rest;

import com.example.CRUD_Student.dto.response.PageResponse;
import com.example.CRUD_Student.entity.Address;
import com.example.CRUD_Student.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final IAddressService addressService;

    @GetMapping("/list")
    public PageResponse<?> getAddress(){
        return addressService.getAddress();
    }
}
