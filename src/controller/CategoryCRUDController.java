package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Category;
import service.CategoryService;

@Controller
public class CategoryCRUDController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/category.add", method = RequestMethod.GET)
	public String listCategory(Model model){
		List<Category> categoryList = categoryService.selectAllCategories();
		
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("newCategory", new Category());
		
		return "addCategory";
	}
	
	@RequestMapping(value = "/category.add", method = RequestMethod.POST)
	public String listCategory(@ModelAttribute Category newCategory, BindingResult result){
		
		categoryService.addCategory(newCategory);
		
		return "redirect:/category.add";
	}
	
	@RequestMapping(value = "/category.edit{id}", method = RequestMethod.GET)
	public String editProduct(@RequestParam("id") int id, Model model){
		
		model.addAttribute("newCategory", categoryService.selectCategoryById(id));
		model.addAttribute("categoryList", categoryService.selectAllCategories());
		model.addAttribute("update", "update");
		
		return "addCategory";
	}
	
	@RequestMapping(value = "/category.update{id}", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("newCategory") Category category, Model model){
		
		categoryService.updateCategory(category);
		
		return "redirect:/category.add";
	}
	
	@RequestMapping(value = "/category.delete{id}", method = RequestMethod.GET)
	public String updateProduct(@RequestParam("id") int id, Model model){
		
		categoryService.removeCategory(id);
		
		return "redirect:/category.add";
	}
	
}
