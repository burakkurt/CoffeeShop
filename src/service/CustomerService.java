package service;

import domain.Customer;

public interface CustomerService {
	
	public abstract void addCustomer(Customer customer);
	public abstract Customer selectCustomerById(int id);

}
