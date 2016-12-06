package domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Flavour {
	
	@TableGenerator(
			name = "FLAVOUR_GEN_DETAILED", 
			table = "FLAVOUR_ID_GEN", 
			pkColumnName = "ID_GEN_NAME", 
			valueColumnName = "ID_GEN_COUNT", 
			initialValue = 1, allocationSize = 10)
	@GeneratedValue(generator = "FLAVOUR_GEN_DETAILED")
	@Id
	private int id;

	private String name;
	
	private double price;
	
//	@OneToMany
//	private Set<Product> products;

	public Flavour() {
		super();
	}

	public Flavour(int id, String name, double price, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
//		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flavour [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
	
}
