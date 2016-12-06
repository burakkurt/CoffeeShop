package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CategoryDAO;
import domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDAOImpl;
	
	@Override
	public List<Category> selectAllCategories() {
		return categoryDAOImpl.selectAllCategories();
	}

	@Override
	public Map<Integer, String> selectAllCategoriesAsMap() {
		return categoryDAOImpl.selectAllCategoriesAsMap();
	}

	@Override
	public Category selectCategoryById(int id) {
		return categoryDAOImpl.selectCategoryById(id);
	}

	@Override
	public void addCategory(Category category) {
		categoryDAOImpl.addCategory(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDAOImpl.updateCategory(category);
	}

	@Override
	public void removeCategory(int id) {
		categoryDAOImpl.removeCategory(id);
	}

}
