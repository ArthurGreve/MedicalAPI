package DTO;

public class ProductQuantityDTO extends ProductDTO {
	private int quantity;

    public ProductQuantityDTO(String code, String name, String description, int quantity){
        this.setCode(code);
        this.setName(name);
        this.setDescription(description);
        this.setQuantity(quantity);
    }

    public ProductQuantityDTO(ProductDTO p, int quantity){
        this(p.getCode(), p.getName(), p.getDescription(), quantity);
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
