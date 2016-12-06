package dao;

import java.util.List;

import domain.Product;

public interface ProductDAO {
	
	public abstract void addProduct(int categoryId, Product product);
	public abstract Product selectProductById(int id);
	public abstract List<Product> selectAllProducts();
	public abstract void updateProduct(Product product);
	public abstract void deleteProduct(int id);
	public abstract List<Product> selectProductByCategory(String category);
	
}
