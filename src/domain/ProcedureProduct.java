package domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProcedureProducts")
public class ProcedureProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Procedure_Id")
	private Procedure procedure;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Product_Id")
	private Product product;
	
	@Column(name = "Quantity", nullable = false)
	private Integer Quantity;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Procedure getProcedure() {
        return procedure;
    }
 
    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
    
    public Product getProduct() {
        return product;
    }
 
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public int getQuantity(){
    	return Quantity;
    }
    
    public void setQuantity(int quantity){
    	this.Quantity = quantity;
    }
}
