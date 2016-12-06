package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import domain.Product;
import domain.ShoppingCart;
import service.FlavourService;
import service.ProductServiceImpl;
import service.ShoppingCartServiceImpl;


@Controller
@SessionAttributes({"shoppingCart","product"})
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private ShoppingCartServiceImpl shoppingCartServiceImpl;
	
	@Autowired
	private FlavourService flavourServiceImpl;
	
	@ModelAttribute("shoppingCart")
	public void initAndBindSCtoSession(Model model) {
		ShoppingCart shoppingCart = new ShoppingCart();
		model.addAttribute("shoppingCart", shoppingCart);
		
		shoppingCartServiceImpl.setShoppingCart(shoppingCart);
	}
	
	@RequestMapping("/")
	public String listProduct(Model model){
		model.addAttribute("products", productServiceImpl.selectAllProducts());
		return "listProduct";
	}
	
	@RequestMapping("/{category}")
	public String listProductByCategory(@PathVariable("category") String category, Model model, HttpSession httpSession){
		
		System.out.println(httpSession.getAttribute("shoppingCart"));
		System.out.println(shoppingCartServiceImpl);
		model.addAttribute("products", productServiceImpl.selectProductByCategory(category));
		
		return "listProduct";
	}
	
	@RequestMapping(value = "/product.detail{productId}", method = RequestMethod.GET)
	public String productDetail(@RequestParam("productId") int id, 
			Model model, HttpSession httpSession){
		
		System.out.println(shoppingCartServiceImpl);
		
		System.out.println("HttpSession shoppingCart (productDetail) : " + httpSession.getAttribute("shoppingCart"));
		Product product = productServiceImpl.selectProductById(id);
		model.addAttribute("product", product);
		
		model.addAttribute("flavourSC", shoppingCartServiceImpl.getFlavourSC());
		model.addAttribute("allFlavours", flavourServiceImpl.selectAllFlavours());
		
		return "productDetail";
	}
	
}
