package pe.edu.upc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "empresavuelo")
public class Empresavuelo{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@NotNull
	String nombre;
	
	@NotNull
	int ruc;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="empresavuelo")
	@Fetch(value = FetchMode.SUBSELECT)
	List<Vuelo> vuelos;
	
	public Empresavuelo() {
		// TODO Auto-generated constructor stub
		vuelos=new ArrayList<>();
	}
	
	public void addVuelo(Vuelo vuelo) {
		vuelos.add(vuelo);
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getRuc() {
		return ruc;
	}


	public void setRuc(int ruc) {
		this.ruc = ruc;
	}
	
	
	
}
