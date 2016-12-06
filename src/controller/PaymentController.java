package controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sun.jmx.snmp.Timestamp;

import domain.Customer;
import domain.Order;
import domain.OrderItem;
import domain.PaymentFormData;
import domain.Product;
import service.CustomerService;
import service.OrderItemService;
import service.OrderService;
import service.ShoppingCartServiceImpl;

@Controller
@SessionAttributes(value = { "shoppingCart" })
public class PaymentController {
	
	@Autowired
	private ShoppingCartServiceImpl shoppingCartServiceImpl;

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerServiceImpl;
	
	@Autowired
	private OrderItemService orderItemServiceImpl;

	@RequestMapping(value = "/order.payment", method = RequestMethod.POST)
	public String payment(Model model) {
		
		model.addAttribute("calculatedAmount", shoppingCartServiceImpl.getShoppingCart().getTotalPrice());
		model.addAttribute("paymentFormData", new PaymentFormData());
		
		return "paymentForm";
	}
	
	@RequestMapping(value = "/order.payment.confirm", method = RequestMethod.POST)
	public String paymentConfirm(@ModelAttribute("paymentFormData") PaymentFormData paymentFormData) {

		System.out.println(paymentFormData);
		
		//Temporary Solution
		Customer customer = new Customer("burak");
		if(customerServiceImpl.selectCustomerById(1) == null)
			customerServiceImpl.addCustomer(customer);
		else
			customer = customerServiceImpl.selectCustomerById(1);
		//
		
		Order order = new Order(new Date(), shoppingCartServiceImpl.getShoppingCart().getTotalPrice());
		order.setCustomer(customer);
		
		OrderItem orderItem;
		for(Product product : shoppingCartServiceImpl.getProductRepetitiveList()){
			orderItem = new OrderItem(new Date(), product.getPrice());
			orderItem.setProduct(product);
			orderItemServiceImpl.addOrderItem(orderItem);
			order.getOrderItems().add(orderItem);
		}

		orderService.insertOrder(order);

		return "redirect:/order.payment.success";

	}

	@RequestMapping(value = "/order.payment.success", method = RequestMethod.GET)
	public String paymentSuccess(SessionStatus status) {
		status.setComplete();
		return "paymentSuccess";

	}
	
}
