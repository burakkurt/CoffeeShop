package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Flavour;
import domain.Product;
import domain.ShoppingCart;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	@Autowired
	ShoppingCart shoppingCart;
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	@Override
	public boolean isProductExistInPSC(Product product) {
		if(shoppingCart.getProductSC().get(product) != null)
			return true;
		
		return false;
	}
	
	@Override
	public Map<Product, Set<Integer>> getProductSC() {
		return getShoppingCart().getProductSC();
	}
	
	@Override
	public List<Product> getProductList() {
		List<Product> productSet = new ArrayList<Product>(shoppingCart.getProductSC().keySet());
		Collections.sort(productSet);
		return productSet;
	}
	
	
	//silinecek
	@Override
	public List<Product> getProductRepetitiveList() {
		Set<Product> productSet = shoppingCart.getProductSC().keySet();
		List<Product> productList = new ArrayList<Product>();
		
		for(Product product : productSet){
			int quantity = this.getQuantityOfProduct(product);
			
			for(int i=0;i<quantity;i++)
				productList.add(product);
		}
		
		return productList;
	}
	
	//silinecek
	@Override
	public Map<Product,Integer> getProductsAndQuantities() {
		Map<Product, Integer> productsAndQuantities = new HashMap<Product, Integer>();
		
		for(Entry<Product, Set<Integer>> entry : shoppingCart.getProductSC().entrySet()){
			Product product = entry.getKey();
			
			if(this.isProductExistInPSC(product))
				productsAndQuantities.put(product, getQuantityOfProduct(product));
		}
		
		return productsAndQuantities;
	}
	
	//silinecek
	@Override
	public int getQuantityOfProduct(Product product) {
		return shoppingCart.getProductSC().get(product).size();		
	}
	
	
	//silinecek
	@Override
	public Set<Integer> getOrderSetOfProduct(Product product) {
		Set<Integer> shoppingCartProductId = getShoppingCart().getProductSC().get(product);
		if(shoppingCartProductId != null)
			return shoppingCartProductId;
		
		return new HashSet<Integer>();
	}
	
	@Override
	public HashMap<Integer, Double> getOrderIDAndPrices() {
		HashMap<Integer, Double> orderIDAndPrices = new HashMap<Integer, Double>();
		List<Product> productList = new ArrayList<Product>(shoppingCart.getProductSC().keySet());
		
		for(Product product  : productList) {
			Set<Integer> orderIDSet = getShoppingCart().getProductSC().get(product);
			
			for(int orderID : orderIDSet){
				double totalProductPrice = 0;
				totalProductPrice += product.getPrice();
				
				Map<Flavour, Integer> flavourQuantityMap = shoppingCart.getFlavourSC().get(orderID);
				
				for(Map.Entry<Flavour, Integer> entry : flavourQuantityMap.entrySet())
					totalProductPrice += entry.getKey().getPrice() * entry.getValue();
				
				orderIDAndPrices.put(orderID, totalProductPrice);
			}
			
		}
		
		return orderIDAndPrices;
	}
	
	@Override
	public double getTotalPrice() {
		double totalPrice = 0;
		
		HashMap<Integer, Double> orderIDAndPrices = getOrderIDAndPrices();
		for(Map.Entry<Integer,Double> entry : orderIDAndPrices.entrySet())
			totalPrice += entry.getValue();
		
		return totalPrice;
	}

	@Override
	public void addOrder(Product product){
		Map<Product,Set<Integer>> psc = getShoppingCart().getProductSC();
		Map<Integer,Map<Flavour,Integer>> fsc = getShoppingCart().getFlavourSC();
		
		if(!psc.containsKey(product))
			psc.put(product, new HashSet<Integer>());
		
		psc.get(product).add(shoppingCart.getOrderIndex());
		fsc.put(shoppingCart.getOrderIndex(), new HashMap<Flavour, Integer>());
		shoppingCart.incrementOrderIndex();
	}
	
	@Override
	public void removeOrderFromPSC(Product product, int orderID){
		if(getShoppingCart().getProductSC().containsKey(product)){
			Set<Product> productSetInPSC = getShoppingCart().getProductSC().keySet();
			Set<Integer> orderSetInPSC = getShoppingCart().getProductSC().get(product);
			Set<Integer> orderSetInASC = getShoppingCart().getFlavourSC().keySet();
			
			if(orderSetInPSC.size() > 1)
				orderSetInPSC.remove(orderID);
			else if(orderSetInPSC.size() == 1)
				productSetInPSC.remove(product);
			else
				throw new NullPointerException("There is no such orderId to remove");
			
			if(orderSetInASC.contains(orderID))
				orderSetInASC.remove(orderID);
			
		}
		else
			throw new NullPointerException("There is no such productId");
	}
	
	@Override
	public void removeAllOrdersOfProduct(Product product) {
		Set<Integer> orderSetOfProduct = getShoppingCart().getProductSC().get(product);
		for(int orderID : orderSetOfProduct)
			getShoppingCart().getFlavourSC().remove(orderID);
		
		getShoppingCart().getProductSC().remove(product);
		
	}
	
	@Override
	public void removeAllOrders() {
		getShoppingCart().setProductSC(new HashMap<Product, Set<Integer>>());
		getShoppingCart().setFlavourSC(new HashMap<Integer,Map<Flavour, Integer>>());
	}

	@Override
	public Map<Integer, Map<Flavour, Integer>> getFlavourSC() {
		return getShoppingCart().getFlavourSC();
	}

	@Override
	public void addFlavourToFSC(int orderID, Flavour flavour) {
		Map<Flavour, Integer> flavourMap = shoppingCart.getFlavourSC().get(orderID);
		
		int quantity=0;
		if(flavourMap.get(flavour) != null)
			quantity = flavourMap.get(flavour);
		
		flavourMap.put(flavour, quantity+1);
	}

	@Override
	public void removeFlavourFromFSC(int orderID, Flavour flavour) {
		Map<Flavour, Integer> flavourMap = shoppingCart.getFlavourSC().get(orderID);
		
		if(flavourMap != null)
			flavourMap.remove(flavour);
	}

	@Override
	public void setTotalPrice(double totalPrice) {
		shoppingCart.setTotalPrice(totalPrice);
	}


}
