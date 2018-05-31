package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IEstadiaDAO;
import pe.edu.upc.entity.Estadia;
import pe.edu.upc.service.IEstadiaService;

@Service
public class EstadiaService implements IEstadiaService {

	@Autowired
	private IEstadiaDAO estadiaRepo;
	

	@Override
	@Transactional
	public Estadia findById(Long id) {
		// TODO Auto-generated method stub
		return estadiaRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<Estadia> findAll() {
		// TODO Auto-generated method stub
		List<Estadia> estadiasDisponibles = new ArrayList<>();
		
		for(Estadia e : estadiaRepo.findAll()) {
			if(!e.isComprado()) {
				estadiasDisponibles.add(e);
			}
		}
		
		return estadiasDisponibles;
	}

	@Override
	@Transactional
	public void save(Estadia t) {
		// TODO Auto-generated method stub
		t.setComprado(false);
		estadiaRepo.save(t);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		estadiaRepo.deleteById(id);
	}

}
