package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import domain.Product;
import repository.ProductRepository;

@Path("/products")
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(){
		this.productRepository = new ProductRepository();
	}
	
	@GET
	@Path("{id}")
	public String getById(@PathParam("id") String code) {
		Product p = new Product();
		Product product = this.productRepository.GetById(code);
		
		if(product != null){
			p.setCode(product.getCode());
			p.setName(product.getName());
			p.setDescription(product.getDescription());
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(p);
		return json;
	}

	@Path("/list")
	@GET
	public String getAll(){
		List<Product> model = new ArrayList<>();
		List<Product> products = this.productRepository.GetAll();
		
		for (Product product : products) {
			Product p = new Product();
			p.setCode(product.getCode());
			p.setName(product.getName());
			p.setDescription(product.getDescription());
			model.add(p);
		}
		
		Gson gson = new Gson();
		String productsJson = gson.toJson(model);
		return productsJson;
	}
	
	@POST
	@Path("/add")//android
	public String add(String json) {
		Gson gson = new Gson();
		Product product = gson.fromJson(json, Product.class);
		
		this.productRepository.Add(product);
		
		String jsonResult = gson.toJson(product);
		return jsonResult;
	}

	@PUT
	@Path("/update")//android
	public String update(String json) {
		Gson gson = new Gson();
		Product product = gson.fromJson(json, Product.class);
		
		this.productRepository.Update(product);

		String jsonResult = gson.toJson(product);
		return jsonResult;
	}
	
	@DELETE
	@Path("{id}")
	public String excluir(@PathParam("id")String id) {
		Product product = this.productRepository.GetById(id);
		
		this.productRepository.Delete(product);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(product);

		return jsonResult;		
	}

}
