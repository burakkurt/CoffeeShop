package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
public class ShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Map<Product, Set<Integer>> productSC;
	private Map<Integer, Map<Flavour, Integer>> flavourSC; 
	private int orderIndex = 1;
	private double totalPrice;
	
	public ShoppingCart() {
		super();
		productSC = new HashMap<Product, Set<Integer>>();
		flavourSC = new HashMap<Integer, Map<Flavour, Integer>>();
	}

	public Map<Product, Set<Integer>> getProductSC() {
		return productSC;
	}

	public void setProductSC(Map<Product, Set<Integer>> productSC) {
		this.productSC = productSC;
	}

	public Map<Integer, Map<Flavour, Integer>> getFlavourSC() {
		return flavourSC;
	}

	public void setFlavourSC(Map<Integer, Map<Flavour, Integer>> flavourSC) {
		this.flavourSC = flavourSC;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	
	public void incrementOrderIndex(){
		this.orderIndex++;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ShoppingCart [productSC=" + productSC + ", flavourSC=" + flavourSC + ", orderIndex=" + orderIndex
				+ ", totalPrice=" + totalPrice + "]";
	}
		
	
}
