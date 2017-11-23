package com.lov2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lov2code.springdemo.dao.CustomerDao;
import com.lov2code.springdemo.dao.CustomerDaoImpl;
import com.lov2code.springdemo.entity.Customer;
import com.lov2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomer(Model model){
		
		List <Customer> customers = customerService.getCustomers();
		
		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model){
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	@GetMapping(value="/showFormForUpdate")
	public String showForm(@RequestParam("customerId") int customerId, Model model){
		Customer theCustomer = customerService.getCustomer(customerId);
		model.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	@GetMapping("/delete")
	public String deleteForm(@RequestParam("customerId") int customerId, Model model){
		customerService.deleteCustomer(customerId);
		return "redirect:/customer/list";
	}
}
