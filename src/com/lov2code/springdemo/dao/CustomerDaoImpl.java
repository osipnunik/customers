package com.lov2code.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lov2code.springdemo.entity.Customer;
@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query = session.createQuery("from Customer order by lastName",Customer.class);
		List<Customer> customers = query.getResultList();
		return customers;
	}
	@Override
	public void saveCustomer(Customer theCustomer){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(theCustomer);
	}
	@Override
	public Customer getCustomer(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, customerId);
		return customer;
	}
	@Override
	public void deleteCustomer(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		/*Customer customer = session.get(Customer.class, customerId);
		session.delete(customer);*/
		Query<Customer>query = session.createQuery("delete from Customer where id=:theId");
		query.setParameter("theId", customerId);
		query.executeUpdate();
	}
}
