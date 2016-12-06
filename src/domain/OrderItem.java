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

@Entity
public class OrderItem {
	
	
	@TableGenerator(
			name = "ORDERITEM_GEN_DETAILED", 
			table = "ORDERITEM_ID_GEN", 
			pkColumnName = "ID_GEN_NAME", 
			valueColumnName = "ID_GEN_COUNT", 
			initialValue = 1, allocationSize = 10)
	@GeneratedValue(generator = "ORDERITEM_GEN_DETAILED")
	@Id
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderItemDate;
	
	private double unitSalePrice;
	
	@ManyToOne
	private Product product;

	public OrderItem() {
		super();
	}

	public OrderItem(Date orderItemDate, double unitSalePrice) {
		super();
		this.orderItemDate = orderItemDate;
		this.unitSalePrice = unitSalePrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderItemDate() {
		return orderItemDate;
	}

	public void setOrderItemDate(Date orderItemDate) {
		this.orderItemDate = orderItemDate;
	}
	
	public double getUnitSalePrice() {
		return unitSalePrice;
	}

	public void setUnitSalePrice(double unitSalePrice) {
		this.unitSalePrice = unitSalePrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

			
}
