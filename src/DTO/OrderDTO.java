package DTO;

public class OrderDTO {
	private Long id;
	
	private DoctorDTO doctor;
	private ProcedureDTO procedure;
	private HealthEnsuranceDTO healthEnsurance;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public void setDoctor(DoctorDTO doctor){
		this.doctor = doctor;
	}
	
	public void setProcedure(ProcedureDTO procedure){
		this.procedure = procedure;
	}
	
	public void setHealthEnsurance(HealthEnsuranceDTO healthEnsurance){
		this.healthEnsurance = healthEnsurance;
	}
}
