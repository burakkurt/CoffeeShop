package dao;

import java.util.Date;
import java.util.List;

import domain.Order;

public interface OrderDAO {

	public abstract void insertOrder(Order order);
	public abstract Order selectOrderByID(int id);
	
	public List<Order> getOrderList();
	public double getOrdersTotalPrice();
	public List<Object[]> getOrdersTotalAmountByCustomerName();
	
}
