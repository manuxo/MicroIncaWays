package pe.edu.upc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.entity.Vuelo;
import pe.edu.upc.service.IVueloService;

@RestController
@RequestMapping("/vuelo")
public class VueloController {
	
	@Autowired
	private IVueloService vueloService;
	
	@GetMapping
	public Iterable<Vuelo> list(Model model) {
		Iterable<Vuelo> productList = vueloService.findAll();
		return productList;
	}
	
	@GetMapping(value="/{id}")
	public Vuelo getVuelo(@PathVariable Long id,Model model) {
		Vuelo vuelo = vueloService.findById(id);
		return vuelo;
	}
	
	@PostMapping(value="/guardar")
	public ResponseEntity<String> guardar(@RequestBody Vuelo vuelo,Model model){
		vueloService.saveVuelo(vuelo);
		return new ResponseEntity<String>("Vuelo publicado.",HttpStatus.OK);
	}
	
	@PutMapping(value="/editar/{id}")
	public ResponseEntity<String> editar(@PathVariable Long id, @RequestBody Vuelo vuelo, Model model){
		Vuelo vueloGuardado = vueloService.findById(id);
		vueloGuardado.setAvion(vuelo.getAvion());
		vueloGuardado.setComprado(vuelo.isComprado());
		vueloGuardado.setEmpresavuelo(vuelo.getEmpresavuelo());
		vueloGuardado.setHorasalida(vuelo.getHorasalida());
		vueloGuardado.setDestino(vuelo.getDestino());
		vueloGuardado.setOrigen(vuelo.getOrigen());
		vueloGuardado.setTarifaadulto(vuelo.getTarifaadulto());
		vueloGuardado.setTarifabase(vuelo.getTarifabase());
		vueloGuardado.setTarifafc(vuelo.getTarifafc());
		vueloGuardado.setTarifanino(vuelo.getTarifanino());
		vueloGuardado.setFechasalida(vuelo.getFechasalida());
		vueloService.saveVuelo(vueloGuardado);
		return new ResponseEntity<String>("Vuelo editado.", HttpStatus.OK);
	}
	
	@DeleteMapping(value="/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id,Model model){
		vueloService.deleteVuelo(id);
		return new ResponseEntity<String>("Vuelo eliminado.", HttpStatus.OK);
	}
}
