package DTO;

import java.util.ArrayList;
import java.util.List;

public class ProcedureDTO {
	private Long id;
	private String name;
	private List<ProductQuantityDTO> products;
	
	public ProcedureDTO(){
		this.products = new ArrayList<ProductQuantityDTO>();
	}
	
	public ProcedureDTO(Long id, String name){
		this.id = id;
		this.name = name;
		this.products = new ArrayList<ProductQuantityDTO>();
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void AddProduct(ProductQuantityDTO product){
		this.products.add(product);
	}
	
	public List<ProductQuantityDTO> getProducts(){
		return products;
	}
			
}
