package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Category {
	
	@TableGenerator(
			name = "CATEGORY_GEN_DETAILED", 
			table = "CATEGORY_ID_GEN", 
			pkColumnName = "ID_GEN_NAME", 
			valueColumnName = "ID_GEN_COUNT", 
			initialValue = 1, allocationSize = 10)
	@Id
	@GeneratedValue(generator = "CATEGORY_GEN_DETAILED")
	private int id;
	private String name;

	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

	
}
