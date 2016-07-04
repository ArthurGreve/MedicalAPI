package domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="Procedures")
public class Procedure {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@OneToMany(mappedBy = "procedure")
	private Set<ProcedureProduct> procedureProducts = new HashSet<ProcedureProduct>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProcedureProduct> getProcedureProducts() {
        return procedureProducts;
    }
 
    public void setProcedureProducts(Set<ProcedureProduct> procedureProducts) {
        this.procedureProducts = procedureProducts;
    }


}
