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
import domain.Flavour;
import service.CategoryService;
import service.FlavourService;

@Controller
public class FlavourCRUDController {
	
	@Autowired
	private FlavourService flavourServiceImpl;
	
	@RequestMapping(value = "/flavour.add", method = RequestMethod.GET)
	public String listFlavour(Model model){
		List<Flavour> flavourList = flavourServiceImpl.selectAllFlavours();
		
		model.addAttribute("flavourList", flavourList);
		model.addAttribute("newFlavour", new Flavour());
		
		return "addFlavour";
	}
	
	@RequestMapping(value = "/flavour.add", method = RequestMethod.POST)
	public String listFlavour(@ModelAttribute Flavour newFlavour, BindingResult result){

		flavourServiceImpl.addFlavour(newFlavour);
		
		return "redirect:/flavour.add";
	}
	
	@RequestMapping(value = "/flavour.edit{id}", method = RequestMethod.GET)
	public String editProduct(@RequestParam("id") int id, Model model){
		
		model.addAttribute("newFlavour", flavourServiceImpl.selectFlavourById(id));
		model.addAttribute("flavourList", flavourServiceImpl.selectAllFlavours());
		model.addAttribute("update", "update");
		
		return "addFlavour";
	}
	
	@RequestMapping(value = "/flavour.update", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute Flavour flavour, Model model){
		
		flavourServiceImpl.updateFlavour(flavour);
		
		return "redirect:/flavour.add";
	}
	
	@RequestMapping(value = "/flavour.delete{id}", method = RequestMethod.GET)
	public String updateProduct(@RequestParam("id") int id, Model model){
		
		flavourServiceImpl.removeFlavour(id);
		
		return "redirect:/flavour.add";
	}

}
