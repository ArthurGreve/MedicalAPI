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

import DTO.ProcedureDTO;
import DTO.ProductQuantityDTO;
import domain.Procedure;
import domain.ProcedureProduct;
import domain.Product;
import repository.ProcedureRepository;
import repository.RepositoryBase;

@Path("procedures")
public class ProcedureService {

	private ProcedureRepository procedureRepository;
	private RepositoryBase<ProcedureProduct> procedureProductRepository;
	
	public ProcedureService() {
		this.procedureRepository = new ProcedureRepository();
		this.procedureProductRepository = new RepositoryBase<>(ProcedureProduct.class);
	}
	
	@GET
	@Path("{id}")
	public String getById(@PathParam("id") Long id) {
		ProductQuantityDTO productDto = null;
		ProcedureDTO procedureDto = null;
		Procedure procedure = this.procedureRepository.GetById(id);
		
		if(procedure != null){
			procedureDto = new ProcedureDTO(procedure.getId(), procedure.getName());
			
			for (ProcedureProduct pp : procedure.getProcedureProducts()) {
				Product p = pp.getProduct();
				productDto = new ProductQuantityDTO(p.getCode(), p.getName(), p.getDescription(), pp.getQuantity());
				procedureDto.AddProduct(productDto);
			}
		}
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(procedureDto);
		return jsonResult;
	}
	
	@GET
	@Path("/list")
	public String getAll(){
		ProcedureDTO procedureDto = null;
		List<ProcedureDTO> proceduresDto = new ArrayList<>();
		List<Procedure> procedures = this.procedureRepository.GetAll();
		
		for (Procedure procedure : procedures) {
			procedureDto = new ProcedureDTO(procedure.getId(), procedure.getName());
			proceduresDto.add(procedureDto);
		}
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(proceduresDto);
		return jsonResult;
	}
	
	@POST
	@Path("/add")//android
	public String add(String json) {
		Gson gson = new Gson();
		
		Product product;
		Procedure procedure;
		ProcedureProduct pp;
		List<ProcedureProduct> pps = new ArrayList<>();
		ProcedureDTO procedureDto = gson.fromJson(json, ProcedureDTO.class);
		
		procedure = new Procedure();
		procedure.setName(procedureDto.getName());
		
		for (ProductQuantityDTO pDto : procedureDto.getProducts()) {
			product = new Product();
			product.setCode(pDto.getCode());
			product.setName(pDto.getName());
			product.setDescription(pDto.getDescription());
			
			pp = new ProcedureProduct();
			pp.setProcedure(procedure);
			pp.setProduct(product);
			pp.setQuantity(pDto.getQuantity());
			
			pps.add(pp);
		}
		
		for (ProcedureProduct procedureProduct : pps) {
			this.procedureProductRepository.Add(procedureProduct);
		}
		
		String jsonResult = gson.toJson(procedureDto);
		return jsonResult;
	}

	@PUT
	@Path("/update")//android
	public String update(String json) {
		Gson gson = new Gson();
		ProcedureProduct pp = gson.fromJson(json, ProcedureProduct.class);
		
		this.procedureProductRepository.Update(pp);

		String jsonResult = gson.toJson(pp);
		return jsonResult;
	}
	
	@DELETE
	@Path("{id}")
	public String excluir(@PathParam("id")Long id) {
		Procedure procedure = this.procedureRepository.GetById(id);
		
		this.procedureRepository.Delete(procedure);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(procedure);
		return jsonResult;		
	}
}