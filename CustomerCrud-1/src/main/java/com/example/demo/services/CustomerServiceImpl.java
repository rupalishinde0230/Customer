package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidAddressException;
import com.example.demo.exception.InvalidIdException;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cr;

	@Override
	public void add(Customer customer) {
		
		if (customer.getId() != null) {

	        if (customer.getId() <= 0) {
	            throw new InvalidIdException("Please enter valid ID");
	        }

	        if (cr.existsById(customer.getId())) {
	            throw new InvalidIdException("ID already present");
	        }
	    }
		
		String address = customer.getAddress();

		if (cr.existsByAddress(address.trim())) {
		    throw new InvalidAddressException("Please enter unique address");
		}


	    String mob = customer.getMob();

	    if (mob != null && mob.length() == 10) {

	        // check first digit (must be 6–9)
	        if (mob.charAt(0) == '0' || mob.charAt(0) == '1' ||
	            mob.charAt(0) == '2' || mob.charAt(0) == '3' ||
	            mob.charAt(0) == '4' || mob.charAt(0) == '5') {

	            throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
	        }
//	        Customer existing = cr.findByMob(customer.getMob());
	        
	        if (cr.existsByMob(customer.getMob())) {
	            throw new InvalidMobileNumber("Mobile number already present");
	        }
	        for (int i = 0; i < mob.length(); i++) {
	            if (!Character.isDigit(mob.charAt(i))) {
	                throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
	            }
	        }

	      

	    } else {
	        throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
	    }
	    

	    cr.save(customer);
	}


	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll();

	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id);
			return temp;
		}
		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		customer.setId(id);
		cr.save(customer);

	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			return temp;
		}
		return null;
	}

	@Override
	public void addall(List<Customer> list) {
		// TODO Auto-generated method stub
		cr.saveAll(list);
		
	}

	@Override
	public Customer findByMob(String mob) {
		// TODO Auto-generated method stub
		return cr.findByMob(mob);
	}

	
}