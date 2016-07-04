package services;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import domain.Doctor;
import repository.RepositoryBase;

@Path("doctors")
public class DoctorService {

	private RepositoryBase<Doctor> doctorRepsoitory;
	
	public DoctorService(){
		this.doctorRepsoitory = new RepositoryBase<>(Doctor.class);
	}
	
	@GET
	@Path("{id}")
	public String getById(@PathParam("id") Long id) {
		Doctor doctor = this.doctorRepsoitory.GetById(id);

		Gson gson = new Gson();
		String jsonResult = gson.toJson(doctor);
		return jsonResult;
	}
	
	@GET
	@Path("/list")
	public String getAll(){
		List<Doctor> doctors = this.doctorRepsoitory.GetAll();
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(doctors);
		return jsonResult;
	}
	
	@POST
	@Path("/add")//android
	public String add(String json) {
		Gson gson = new Gson();
		Doctor doctor = gson.fromJson(json, Doctor.class);
		
		this.doctorRepsoitory.Add(doctor);
		
		String jsonResult = gson.toJson(doctor);
		return jsonResult;
	}

	@PUT
	@Path("/update")//android
	public String update(String json) {
		Gson gson = new Gson();
		Doctor doctor = gson.fromJson(json, Doctor.class);
		
		this.doctorRepsoitory.Update(doctor);

		String jsonResult = gson.toJson(doctor);
		return jsonResult;
	}
	
	@DELETE
	@Path("{id}")
	public String excluir(@PathParam("id")Long id) {
		Doctor doctor = this.doctorRepsoitory.GetById(id);
		
		this.doctorRepsoitory.Delete(doctor);
		
		Gson gson = new Gson();
		String jsonResult = gson.toJson(doctor);
		return jsonResult;		
	}
}