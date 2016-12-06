package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductDAOImpl;
import domain.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAOImpl productDAOImpl;

	@Override
	public void addProduct(int categoryId, Product product) {
		productDAOImpl.addProduct(categoryId, product);
	}

	@Override
	public Product selectProductById(int id) {
		return productDAOImpl.selectProductById(id);
	}

	@Override
	public List<Product> selectAllProducts() {
		return productDAOImpl.selectAllProducts();
	}

	@Override
	public void updateProduct(Product product) {
		productDAOImpl.updateProduct(product);
	}

	@Override
	public void deleteProduct(int id) {
		productDAOImpl.deleteProduct(id);
	}

	@Override
	public List<Product> selectProductByCategory(String category) {
		return productDAOImpl.selectProductByCategory(category);
	}
	
}
