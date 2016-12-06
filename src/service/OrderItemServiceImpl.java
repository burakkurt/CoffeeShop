package service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OrderItemDAO;
import domain.OrderItem;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	OrderItemDAO orderItemDAOImpl;

	@Override
	public void addOrderItem(OrderItem orderItem) {
		orderItemDAOImpl.addOrderItem(orderItem);
	}

	

}
