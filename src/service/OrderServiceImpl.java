package service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OrderDAO;
import domain.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAOImpl;

	public void insertOrder(Order order) {
		orderDAOImpl.insertOrder(order);
	}
	
	@Override
	public Order selectOrderByID(int id) {
		return orderDAOImpl.selectOrderByID(id);
	}

	@Override
	public List<Order> getOrderList() {
		return orderDAOImpl.getOrderList();
	}

	@Override
	public double getOrdersTotalPrice() {
		return orderDAOImpl.getOrdersTotalPrice();
	}

	@Override
	public List<Object[]> getOrdersTotalAmountByCustomerName() {
		return orderDAOImpl.getOrdersTotalAmountByCustomerName();
	}



	

	
}
