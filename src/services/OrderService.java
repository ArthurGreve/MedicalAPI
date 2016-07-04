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

import DTO.DoctorDTO;
import DTO.HealthEnsuranceDTO;
import DTO.OrderDTO;
import DTO.ProcedureDTO;
import domain.Doctor;
import domain.HealthEnsurance;
import domain.Order;
import domain.Procedure;
import repository.RepositoryBase;

@Path("orders")
public class OrderService {
	private RepositoryBase<Order> orderRepsoitory;

	public OrderService() {
		this.orderRepsoitory = new RepositoryBase<>(Order.class);
	}

	@GET
	@Path("{id}")
	public String getById(@PathParam("id") Long id) {
		OrderDTO orderDto = null;
		Order order = this.orderRepsoitory.GetById(id);

		if (order != null) {
			Doctor doctor = order.getDoctor();
			Procedure procedure = order.getProcedure();
			HealthEnsurance healthEnsurance = order.getHealthEnsurance();

			DoctorDTO doctorDto = new DoctorDTO(doctor.getName(), doctor.getSpecialization());
			doctorDto.setId(doctor.getId());

			ProcedureDTO procedureDto = new ProcedureDTO(procedure.getId(), procedure.getName());

			HealthEnsuranceDTO healthEnsuranceDto = new HealthEnsuranceDTO(healthEnsurance.getId(),
					healthEnsurance.getName());

			orderDto = new OrderDTO();
			orderDto.setId(order.getId());
			orderDto.setDoctor(doctorDto);
			orderDto.setProcedure(procedureDto);
			orderDto.setHealthEnsurance(healthEnsuranceDto);
		}

		Gson gson = new Gson();
		String jsonResult = gson.toJson(orderDto);
		return jsonResult;
	}

	@GET
	@Path("/list")
	public String getAll() {
		OrderDTO orderDto;
		List<OrderDTO> ordersDto = new ArrayList<>();
		List<Order> orders = this.orderRepsoitory.GetAll();

		for (Order order : orders) {
			Doctor doctor = order.getDoctor();
			Procedure procedure = order.getProcedure();
			HealthEnsurance healthEnsurance = order.getHealthEnsurance();

			DoctorDTO doctorDto = new DoctorDTO(doctor.getName(), doctor.getSpecialization());
			doctorDto.setId(doctor.getId());

			ProcedureDTO procedureDto = new ProcedureDTO(procedure.getId(), procedure.getName());

			HealthEnsuranceDTO healthEnsuranceDto = new HealthEnsuranceDTO(healthEnsurance.getId(),
					healthEnsurance.getName());

			orderDto = new OrderDTO();
			orderDto.setId(order.getId());
			orderDto.setDoctor(doctorDto);
			orderDto.setProcedure(procedureDto);
			orderDto.setHealthEnsurance(healthEnsuranceDto);
			ordersDto.add(orderDto);
		}

		Gson gson = new Gson();
		String jsonResult = gson.toJson(ordersDto);
		return jsonResult;
	}

	@POST
	@Path("/add") // android
	public String add(String json) {
		Gson gson = new Gson();
		Order order = gson.fromJson(json, Order.class);

		this.orderRepsoitory.Add(order);

		String jsonResult = gson.toJson(order);
		return jsonResult;
	}

	@PUT
	@Path("/update") // android
	public String update(String json) {
		Gson gson = new Gson();
		Order order = gson.fromJson(json, Order.class);

		this.orderRepsoitory.Update(order);

		String jsonResult = gson.toJson(order);
		return jsonResult;
	}

	@DELETE
	@Path("{id}")
	public String delete(@PathParam("id") Long id) {
		Order order = this.orderRepsoitory.GetById(id);

		this.orderRepsoitory.Delete(order);

		Gson gson = new Gson();
		String jsonResult = gson.toJson(order);
		return jsonResult;
	}
}
