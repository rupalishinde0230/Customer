package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Customer;

public interface CustomerService {
	
	void add(Customer customer);
	List<Customer>display();
	Customer delete(Integer id);
	void update(Customer customer,Integer id);
	Customer search(Integer id);
	void addall(List<Customer>list);
	Customer findByMob(String mob);

}