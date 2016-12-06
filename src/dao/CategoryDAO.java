package dao;

import java.util.List;
import java.util.Map;

import domain.Category;

public interface CategoryDAO {
	
	public abstract List<Category> selectAllCategories();
	public abstract Map<Integer, String> selectAllCategoriesAsMap();
	public abstract Category selectCategoryById(int id);
	public abstract void addCategory(Category category);
	public abstract void updateCategory(Category category);
	public abstract void removeCategory(int id);
	
}
