package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="Orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne()
    @JoinColumn(name = "Procedure_Id")
	private Procedure procedure;
	
	@ManyToOne()
    @JoinColumn(name = "Doctor_Id")
	private Doctor doctor;
	
	@ManyToOne()
    @JoinColumn(name = "HealthEnsurance_Id")
	private HealthEnsurance healthEnsurance;
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setProcedure(Procedure procedure){
		this.procedure = procedure;
	}
	
	public Procedure getProcedure(){
		return procedure;
	}
	
	public void setDoctor(Doctor doctor){
		this.doctor = doctor;
	}
	
	public Doctor getDoctor(){
		return doctor;
	}
	
	public void setHealthEnsurance(HealthEnsurance healthEnsurance){
		this.healthEnsurance = healthEnsurance;
	}
	
	public HealthEnsurance getHealthEnsurance(){
		return healthEnsurance;
	}
}
