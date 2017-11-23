package com.lov2code.springdemo.dao;

import java.util.List;

import com.lov2code.springdemo.entity.Customer;

public interface CustomerDao {

	public List<Customer> getCustomers();
	public void saveCustomer(Customer theCustomer);
	public Customer getCustomer(int customerId);
	public void deleteCustomer(int customerId);
}
