package dao;

import domain.Customer;

public interface CustomerDAO {

	public abstract void addCustomer(Customer customer);
	public abstract Customer selectCustomerById(int id);
	
}
