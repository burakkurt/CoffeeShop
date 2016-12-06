package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Category;
import domain.Product;
import service.CategoryService;
import service.ProductServiceImpl;
import validator.ProductValidator;

@Controller
public class ProductCRUDController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private ProductValidator productValidator;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/product.add", method = RequestMethod.GET)
	public String addProduct(@ModelAttribute("newProduct") Product product, Model model){
		List<Product> personList = productServiceImpl.selectAllProducts();
		model.addAttribute("allProducts", personList);
		return "addProduct";
	}
	
	@RequestMapping(value = "/product.add", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("newProduct") Product product, BindingResult result){
		
		productValidator.validate(product, result);
		if(result.hasErrors()){
			return "addProduct";
		}
		
		System.out.println(product);
		System.out.println(product.getCategory());
		
		int categoryId = product.getCategory().getId();
		Category category = categoryService.selectCategoryById(categoryId);
		System.out.println(category);
		
		
		System.out.println("PRODUCT : " + product);
		
		productServiceImpl.addProduct(categoryId, product);
		
		return "redirect:/product.add";
	}
	
	@RequestMapping(value = "/product.delete{id}", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") String id){

		productServiceImpl.deleteProduct(Integer.parseInt(id));

		return "redirect:/product.add";
	}
	
	@RequestMapping(value = "/product.edit{id}", method = RequestMethod.GET)
	public String editProduct(@RequestParam("id") int id, Model model){
		
		model.addAttribute("newProduct", productServiceImpl.selectProductById(id));
		model.addAttribute("allProducts", productServiceImpl.selectAllProducts());
		model.addAttribute("update", "update");
		
		return "addProduct";
	}
	
	@RequestMapping(value = "/product.update{id}", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("newPerson") Product product, Model model){
		
		productServiceImpl.updateProduct(product);
		
		System.out.println("Product category id : " + product.getCategory().getId());
		
		return "redirect:/product.add";
	}
	
	@ModelAttribute("productCategories")
	public Map<Integer, String> prepareCategoryList() {
		System.out.println(categoryService.selectAllCategoriesAsMap());
		return categoryService.selectAllCategoriesAsMap();
	}
	
}
