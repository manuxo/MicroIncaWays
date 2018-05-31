package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Vuelo;

public interface IService <T,ID>{
	public void saveVuelo(T t);
	public void deleteVuelo(ID id);
	public Vuelo findById(ID id);
	public List<T> findAll();
}
