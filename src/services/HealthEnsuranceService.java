package services;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import domain.HealthEnsurance;
import repository.RepositoryBase;

@Path("healthensurances")
public class HealthEnsuranceService {

	private RepositoryBase<HealthEnsurance> healthRepository;
	
	public HealthEnsuranceService() {
		this.healthRepository = new RepositoryBase<>(HealthEnsurance.class);
	}
	
	@GET
	@Path("{id}")
	public String getById(@PathParam("id") Long id) {
		HealthEnsurance he = this.healthRepository.GetById(id);

		Gson gson = new Gson();
		String jsonResult = gson.toJson(he);
		return jsonResult;
	}
	
	@GET
	@Path("/list")
	public String getAll(){
		List<HealthEnsurance> he = this.healthRepository.GetAll();
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(he);
		return jsonResult;
	}
	
	@POST
	@Path("/add")//android
	public String add(String json) {
		Gson gson = new Gson();
		HealthEnsurance he = gson.fromJson(json, HealthEnsurance.class);
		
		this.healthRepository.Add(he);
		
		String jsonResult = gson.toJson(he);
		return jsonResult;
	}

	@PUT
	@Path("/update")//android
	public String update(String json) {
		Gson gson = new Gson();
		HealthEnsurance he = gson.fromJson(json, HealthEnsurance.class);
		
		this.healthRepository.Update(he);

		String jsonResult = gson.toJson(he);
		return jsonResult;
	}
	
	@DELETE
	@Path("{id}")
	public String excluir(@PathParam("id")Long id) {
		HealthEnsurance he = this.healthRepository.GetById(id);
		
		this.healthRepository.Delete(he);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(he);
		return jsonResult;		
	}
}
