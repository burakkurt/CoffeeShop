package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="CustomerOrder")
public class Order {

	@TableGenerator(
			name = "ORDER_GEN_DETAILED", 
			table = "ORDER_ID_GEN", 
			pkColumnName = "ID_GEN_NAME", 
			valueColumnName = "ID_GEN_COUNT", 
			initialValue = 1, allocationSize = 10)
	@Id
	@GeneratedValue(generator = "ORDER_GEN_DETAILED")
	@Column(name="order_id")
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	private double price;

	@ManyToOne
	private Customer customer;
	
	@OneToMany
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public Order() {
		super();
	}

	public Order(Date orderDate, double price) {
		super();
		this.orderDate = orderDate;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	

}
