package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	EntityManager entityManager;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Category> selectAllCategories() {
		TypedQuery<Category> typedQuery = entityManager.createQuery("Select c from Category c", Category.class);
		return typedQuery.getResultList();
	}

	@Override
	public Map<Integer, String> selectAllCategoriesAsMap() {
		Map<Integer, String> categoryMap = new HashMap<Integer, String>();
		
		for(Category category : selectAllCategories())
			categoryMap.put(category.getId(), category.getName());
		
		return categoryMap;
	}

	@Override
	public Category selectCategoryById(int id) {
		return entityManager.find(Category.class, id);
	}

	@Override
	public void addCategory(Category category) {
		entityManager.getTransaction().begin();
		entityManager.persist(category);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void updateCategory(Category category) {
		Category foundCategory = selectCategoryById(category.getId());
		
		if(foundCategory != null){
			entityManager.getTransaction().begin();
			foundCategory.setName(category.getName());
			entityManager.getTransaction().commit();
		}
	}

	@Override
	public void removeCategory(int id) {
		
		Category category = selectCategoryById(id);
		
		entityManager.getTransaction().begin();
		entityManager.remove(category);
		entityManager.getTransaction().commit();
	}

}
