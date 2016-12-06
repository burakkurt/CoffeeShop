package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrderItemFlavour {

	@TableGenerator(
			name = "ORDERFLAVOURITEM_GEN_DETAILED", 
			table = "ORDEFLAVOURRITEM_ID_GEN", 
			pkColumnName = "ID_GEN_NAME", 
			valueColumnName = "ID_GEN_COUNT", 
			initialValue = 1, allocationSize = 10)
	@GeneratedValue(generator = "ORDERFLAVOURITEM_GEN_DETAILED")
	@Id
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderFlavourItemDate;
	
	private double unitSalePrice;
	
	private int quantity;
	
	@ManyToOne
	private Flavour flavour;

	public OrderItemFlavour() {
		super();
	}

	public OrderItemFlavour(Date orderFlavourItemDate, double unitSalePrice, int quantity) {
		super();
		this.orderFlavourItemDate = orderFlavourItemDate;
		this.unitSalePrice = unitSalePrice;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderFlavourItemDate() {
		return orderFlavourItemDate;
	}

	public void setOrderFlavourItemDate(Date orderFlavourItemDate) {
		this.orderFlavourItemDate = orderFlavourItemDate;
	}

	public double getUnitSalePrice() {
		return unitSalePrice;
	}

	public void setUnitSalePrice(double unitSalePrice) {
		this.unitSalePrice = unitSalePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Flavour getFlavour() {
		return flavour;
	}

	public void setFlavour(Flavour flavour) {
		this.flavour = flavour;
	}

	
	
}
