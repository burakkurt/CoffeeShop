package controller;

import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import domain.Flavour;
import domain.Product;
import domain.ShoppingCart;
import service.FlavourService;
import service.FlavourServiceImpl;
import service.ShoppingCartServiceImpl;

@Controller
@SessionAttributes({"shoppingCart", "product"})
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartServiceImpl shoppingCartServiceImpl;
	
	@Autowired
	FlavourService flavourServiceImpl;

	@RequestMapping("/shopping.cart")
	public String shoppingCartDetail(Model model, HttpSession httpSession){
		
		model.addAttribute("productSC", shoppingCartServiceImpl.getProductSC());
		model.addAttribute("flavourSC", shoppingCartServiceImpl.getFlavourSC());
		model.addAttribute("totalPrices", shoppingCartServiceImpl.getOrderIDAndPrices());
		
		System.out.println(shoppingCartServiceImpl.getOrderIDAndPrices());
		
		shoppingCartServiceImpl.setTotalPrice(shoppingCartServiceImpl.getTotalPrice());
		
		return "shoppingCart";
	}
	
	@RequestMapping(value = "/product.add.to.cart{productId}", method = RequestMethod.GET)
	public String addProductToPSC(@RequestParam("productId") int id,
			@ModelAttribute("product") Product product, 
			Model model, HttpSession httpSession){
		
		System.out.println("controller");
		System.out.println("Product : " + product);
		shoppingCartServiceImpl.addOrder(product);
		System.out.println("HttpSession shoppingCart (addProductToShoppingCart) : " + httpSession.getAttribute("shoppingCart"));
		
		return "redirect:/product.detail?productId="+id;
	}
	
	@RequestMapping(value = "/remove.all.orders.of.product", method = RequestMethod.GET)
	public String removeAllOrdersOfProduct(@ModelAttribute("product") Product product, 
			Model model, HttpSession httpSession){
		
		shoppingCartServiceImpl.removeAllOrdersOfProduct(product);
		System.out.println(httpSession.getAttribute("shoppingCart"));
		
		return "redirect:/product.detail?productId="+product.getId();
	}
	
	@RequestMapping(value = "/remove.order.from.product{orderID}", method = RequestMethod.GET)
	public String removeAnOrderOfProduct(@RequestParam("orderID") int orderID, @ModelAttribute("product") Product product, 
			Model model, HttpSession httpSession){
		
		shoppingCartServiceImpl.removeOrderFromPSC(product, orderID);
		
		System.out.println(httpSession.getAttribute("shoppingCart"));
		
		return "redirect:/product.detail?productId="+product.getId();
	}
	
	@RequestMapping(value = "/add.flavour.to.cart", method = RequestMethod.GET)
	public String addFlavourToFSC(@RequestParam("orderID") int orderID,
			@RequestParam("flavourID") int flavourID,
			@ModelAttribute("product") Product product, Model model){
		
		System.out.println(orderID);
		System.out.println(flavourID);
		
		Flavour flavour = flavourServiceImpl.selectFlavourById(flavourID);
		System.out.println(flavour);
		
		shoppingCartServiceImpl.addFlavourToFSC(orderID, flavour);
		
		return "redirect:/product.detail?productId="+product.getId();
	}
	
	@RequestMapping(value = "/remove.flavour.from.cart", method = RequestMethod.GET)
	public String removeFlavourFromFSC(@RequestParam("orderID") int orderID,
			@RequestParam("flavourID") int flavourID,
			@ModelAttribute("product") Product product, Model model){
		
		System.out.println(orderID);
		System.out.println(flavourID);
		
		Flavour flavour = flavourServiceImpl.selectFlavourById(flavourID);
		System.out.println(flavour);
		
		shoppingCartServiceImpl.removeFlavourFromFSC(orderID, flavour);
		
		return "redirect:/product.detail?productId="+product.getId();
	}
	
}
