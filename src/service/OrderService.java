package service;

import java.util.Date;
import java.util.List;

import domain.Order;

public interface OrderService {
	
	public abstract void insertOrder(Order order);
	public abstract Order selectOrderByID(int id);
	
	public List<Order> getOrderList();
	
	public double getOrdersTotalPrice();

	public List<Object[]> getOrdersTotalAmountByCustomerName();
}
