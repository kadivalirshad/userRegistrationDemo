package com.example.registrationlogindemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.registrationlogindemo.entity.Address;

public interface AddresRepository extends JpaRepository<Address,Integer>{

}
