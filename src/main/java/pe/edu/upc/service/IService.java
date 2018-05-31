package pe.edu.upc.service;

import java.util.List;

public interface IService <T,ID>{
	public void save(T t);
	public void delete(ID id);
	public T findById(ID id);
	public List<T> findAll();
}
