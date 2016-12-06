package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Customer {

	@TableGenerator(
			name = "CUSTOMER_GEN_DETAILED", 
			table = "CUSTOMER_ID_GEN", 
			pkColumnName = "ID_GEN_NAME", 
			valueColumnName = "ID_GEN_COUNT", 
			initialValue = 1, allocationSize = 10)
	@Id
	@GeneratedValue(generator = "CUSTOMER_GEN_DETAILED")
	@Column(name = "customer_id")
	private int id;

	private String customerName;

	public Customer(String customerName) {
		super();
		this.customerName = customerName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + "]";
	}

}
