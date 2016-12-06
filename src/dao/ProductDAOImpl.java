package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Category;
import domain.Product;

@Repository
public class ProductDAOImpl implements ProductDAO{
	
	EntityManager entityManager;
	
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void addProduct(int categoryId, Product product) {
		
		Category category = entityManager.find(Category.class, categoryId);
		product.setCategory(category);
		
		entityManager.getTransaction().begin();
		entityManager.persist(product);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public Product selectProductById(int id) {
		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> selectAllProducts() {
		TypedQuery<Product> typedQuery = entityManager.createQuery("Select p from Product p", Product.class);
		return typedQuery.getResultList();
	}

	@Override
	public void updateProduct(Product product) {
		Product foundProduct = selectProductById(product.getId());
		
		if(foundProduct != null){
			entityManager.getTransaction().begin();
			foundProduct.setName(product.getName());
			foundProduct.setDescription(product.getDescription());
			foundProduct.setCategory(product.getCategory());
			foundProduct.setPrice(product.getPrice());
			entityManager.getTransaction().commit();
		}
	
	}

	@Override
	public void deleteProduct(int id) {
		Product foundProduct = selectProductById(id);
		
		if(foundProduct != null){
			entityManager.getTransaction().begin();
			entityManager.remove(foundProduct);
			entityManager.getTransaction().commit();	
		}
		
	}

	@Override
	public List<Product> selectProductByCategory(String category) {
		
		TypedQuery<Product> typedQuery = entityManager.createQuery("Select p from Product p Where p.category.name = :productCategory", Product.class)
				.setParameter("productCategory", category);
		return typedQuery.getResultList();
		
	}

}
