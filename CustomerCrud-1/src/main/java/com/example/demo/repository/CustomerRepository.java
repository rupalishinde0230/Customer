package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	Customer findByMob(String mob);

	boolean existsByAddress(String address);

	 boolean  existsByMob(String mob);

	
}