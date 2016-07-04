package domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Products")
public class Product {

	@Id
	private String code;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "description", nullable = false, unique = true, length = 200)
	private String description;

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
	private Set<ProcedureProduct> procedureProducts = new HashSet<ProcedureProduct>();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ProcedureProduct> getProcedureProducts() {
        return procedureProducts;
    }
 
    public void setProcedureProducts(Set<ProcedureProduct> procedureProducts) {
        this.procedureProducts = procedureProducts;
    }
}
