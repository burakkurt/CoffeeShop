package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Flavour;
import domain.Product;
import domain.ShoppingCart;

public interface ShoppingCartService {
	
	public abstract boolean isProductExistInPSC(Product product);
	public abstract Map<Product, Set<Integer>> getProductSC();
	public List<Product> getProductList();
 	public abstract List<Product> getProductRepetitiveList();
 	public abstract Map<Product,Integer> getProductsAndQuantities();
 	public abstract int getQuantityOfProduct(Product product);
	public abstract HashMap<Integer, Double> getOrderIDAndPrices();
	public abstract double getTotalPrice();
	public abstract Set<Integer> getOrderSetOfProduct(Product product);
	public abstract void addOrder(Product product);
	public abstract void removeOrderFromPSC(Product product, int orderId);
	public void removeAllOrdersOfProduct(Product product);
	public abstract void removeAllOrders();
	
	public abstract Map<Integer, Map<Flavour, Integer>> getFlavourSC();
	public abstract void addFlavourToFSC(int orderID, Flavour flavour);
	public abstract void removeFlavourFromFSC(int orderID, Flavour flavour);
	
	public abstract void setTotalPrice(double totalPrice);
	

}
