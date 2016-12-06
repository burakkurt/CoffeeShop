package controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Order;
import domain.OrderItem;
import service.OrderItemService;
import service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderServiceImpl;
	
	@RequestMapping(value = "order.list", method = RequestMethod.GET)
	public String getCustomerOrderList(Model model) {
		model.addAttribute("ordersTotal", orderServiceImpl.getOrdersTotalPrice());
		model.addAttribute("customerOrders", orderServiceImpl.getOrderList());

		return "orderList";
	}
	
	@RequestMapping(value = "order.detail{id}", method = RequestMethod.GET)
	public String orderDetail(@RequestParam("id") int id, Model model) {
		
		Order order = orderServiceImpl.selectOrderByID(id);
		Set<OrderItem> orderItemSet = order.getOrderItems();
		System.out.println(orderItemSet);
		
		model.addAttribute("orderItemList", orderItemSet);
		model.addAttribute("totalPrice", order.getPrice());
		
		return "orderDetail";
	}
}
