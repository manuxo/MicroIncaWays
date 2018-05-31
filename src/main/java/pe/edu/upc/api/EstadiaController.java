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
import pe.edu.upc.entity.Estadia;
import pe.edu.upc.service.IEstadiaService;


@Api(value="IncaWays", description="Operaciones pertenecientes a estadias en IncaWays")
@RestController
@RequestMapping("/api/estadia")
public class EstadiaController {
	
	@Autowired
	private IEstadiaService estadiaService;
	
	@ApiOperation(value = "Visualiza una lista de las estadias disponibles(A la venta).", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public Iterable<Estadia> listar(Model model){
		Iterable<Estadia> estadiasDisponibles = estadiaService.findAll();
		return estadiasDisponibles;
	}
	
	@ApiOperation(value = "Busca una estadia por ID.", response = Estadia.class)
	@GetMapping(value="/{id}")
	public Estadia getEstadia(@PathVariable Long id, Model model) {
		Estadia estadia = estadiaService.findById(id);
		return estadia;
	}
	
	@ApiOperation(value="Guarda una estadia.")
	@PostMapping(value="/guardar")
	public ResponseEntity<String> guardar(@RequestBody Estadia estadia,Model model){
		estadiaService.save(estadia);
		return new ResponseEntity<String>("Estadia publicada", HttpStatus.OK);
	}
	
	@ApiOperation(value="Edita una estadia")
	@PutMapping(value="/editar/{id}")
	public ResponseEntity<String> editar(@RequestBody Estadia estadia, @PathVariable Long id, Model model){
		Estadia estadiaGuardada = estadiaService.findById(id);
		estadiaGuardada.setPais(estadia.getPais());
		estadiaGuardada.setCiudad(estadia.getCiudad());
		estadiaGuardada.setDireccion(estadia.getDireccion());
		estadiaGuardada.setCostoxdia(estadia.getCostoxdia());
		estadiaGuardada.setTipoestadia(estadia.getTipoestadia());
		estadiaGuardada.setEmpresaestadia(estadia.getEmpresaestadia());
		estadiaGuardada.setComprado(estadia.isComprado());
		
		estadiaService.save(estadiaGuardada);
		return new ResponseEntity<String>("Estadia editada", HttpStatus.OK);
	}
	
	@ApiOperation(value="Elimina una estadia por id")
	@DeleteMapping
	public ResponseEntity<String> eliminar(@PathVariable Long id, Model model){
		estadiaService.delete(id);
		return new ResponseEntity<String>("Estadia eliminada", HttpStatus.OK);
	}
}
