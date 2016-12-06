package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CustomerDAO;
import domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDAO customerDAOImpl;

	@Override
	public void addCustomer(Customer customer) {
		customerDAOImpl.addCustomer(customer);
	}

	@Override
	public Customer selectCustomerById(int id) {
		return customerDAOImpl.selectCustomerById(id);
	}

}
