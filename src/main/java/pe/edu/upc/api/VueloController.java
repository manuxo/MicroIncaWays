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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pe.edu.upc.entity.Vuelo;
import pe.edu.upc.service.IVueloService;


@Api(value = "IncaWays", description = "Operaciones perteneciente a vuelos en IncaWays.")
@RestController
@RequestMapping("/api/vuelo")
public class VueloController {
	
	@Autowired
	private IVueloService vueloService;
	
	@ApiOperation(value = "Visualiza una lista de los vuelos disponibles(A la venta).", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public Iterable<Vuelo> list(Model model) {
		Iterable<Vuelo> productList = vueloService.findAll();
		return productList;
	}
	
	@ApiOperation(value = "Busca un vuelo con un ID.", response = Vuelo.class)
	@GetMapping(value="/{id}")
	public Vuelo getVuelo(@PathVariable Long id,Model model) {
		Vuelo vuelo = vueloService.findById(id);
		return vuelo;
	}
	
	@ApiOperation(value = "Agrega un vuelo.")
	@PostMapping(value="/guardar")
	public ResponseEntity<String> guardar(@RequestBody Vuelo vuelo,Model model){
		vueloService.save(vuelo);
		return new ResponseEntity<String>("Vuelo publicado.",HttpStatus.OK);
	}
	
	@ApiOperation(value = "Edita un vuelo.")
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
		vueloService.save(vueloGuardado);
		return new ResponseEntity<String>("Vuelo editado.", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina un vuelo.")
	@DeleteMapping(value="/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable Long id,Model model){
		vueloService.delete(id);
		return new ResponseEntity<String>("Vuelo eliminado.", HttpStatus.OK);
	}
}
