package com.example.CRUD_Student.dao;

import com.example.CRUD_Student.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
