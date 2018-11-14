package com.ipartek.formacion.prestamos.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.prestamos_libros.pojo.Editorial;
import com.ipartek.formacion.prestamos_libros.service.ServiceEditorial;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/editoriales")
public class EditorialesController {
	
	ServiceEditorial serviceEditorial = null;
	ValidatorFactory factory = null;
	Validator validator = null;
	
	//Logger
	private final static Logger LOG = Logger.getLogger(EditorialesController.class);
	
	public EditorialesController() {
		super();
		LOG.trace("Constructor");
		serviceEditorial = ServiceEditorial.getInstance();
		LOG.trace("Servicio editorial instanciado");
		
		//Crear Factoria y Validador
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@ApiOperation(value = "Listado de editoriales", notes = "Muestra el listado de todas las editoriales"
			+ " de todos los prestamos", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Mostrar lista editoriales correctamente", responseContainer="List")})
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Editorial>> listado() {
		ResponseEntity<ArrayList<Editorial>> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		ArrayList<Editorial> editoriales = new ArrayList<Editorial>();
		
		try {
			editoriales = (ArrayList<Editorial>) serviceEditorial.listar();
			response = new ResponseEntity<>(editoriales, HttpStatus.OK);
			LOG.debug("Editoriales devueltas correctamente" + editoriales.size());
		}catch(Exception e) {
//			e.printStackTrace();
			LOG.error(e);
		}
		
		return response;
	}

	@ApiOperation(value = "Detalle editorial", notes = "Muestra el detalle de una editorial en concreto", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Mostrar detalle editorial correctamente", responseContainer="Editorial"),
							@ApiResponse(code = 404, message = "No existe la editorial")})
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "id", value = "Id editorial", required = true, dataType = "long", paramType = "Path")
	  })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Editorial> detalle(@PathVariable long id) {
		
		ResponseEntity<Editorial> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			Editorial editorial = serviceEditorial.buscarPorId(id);
			if(editorial != null && editorial.getId() > 0) {
				response = new ResponseEntity<>(editorial, HttpStatus.OK);
				LOG.debug("Detalle editorial devuelta correctamente");
			}else {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				LOG.warn("No se ha encontrado la editorial a devolver");
			}
		}catch(Exception e) {
//			e.printStackTrace();
			LOG.error(e);
		}

		return response;
	}
	
	@ApiOperation(value = "Crear editorial", notes = "Crea una nueva editorial", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Crear una editorial correctamente", responseContainer="Editorial"),
			@ApiResponse(code = 409, message = "Conflicto por validaciones de editorial o porque ya existe una editorial con el mismo nombre")})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> crear(@RequestBody Editorial editorial) {
		
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			
			Set<ConstraintViolation<Editorial>> violations = validator.validate(editorial);
			if (violations.isEmpty()) {
			    /* Ha pasado la validacion*/
				if(serviceEditorial.crear(editorial)) {
					response = new ResponseEntity<>(editorial, HttpStatus.CREATED);
					LOG.debug("Editorial creada correctamente");
				}else {
					response = new ResponseEntity<>(HttpStatus.CONFLICT);
					LOG.warn("Conflicto a la hora de crear editorial");
				}
			}else{
			    /* Hay fallos, la Validacion no es correcta */
				ResponseMensaje msj = new ResponseMensaje();
				msj.setMensaje("Los datos no son válidos.");
				 for (ConstraintViolation<Editorial> violation : violations) {
					 msj.addError(violation.getPropertyPath()+": "+violation.getMessage());
					 LOG.warn("Error de validacion al crear editorial: "+ violation.getPropertyPath()+": "+violation.getMessage());
				 }
				response = new ResponseEntity<>(msj, HttpStatus.CONFLICT);
			}
			
			
		}catch(MySQLIntegrityConstraintViolationException e) {
//			e.printStackTrace();
			LOG.error(e);
			ResponseMensaje msj = new ResponseMensaje("Ya existe la editorial, por favor prueba con otro nombre.");
			response = new ResponseEntity<>(msj, HttpStatus.CONFLICT);
		}
		catch(Exception e) {
//			e.printStackTrace();
			LOG.error(e);
		}
		 
		return response;
	}
	
	@ApiOperation(value = "Modificar editorial", notes = "Modifica los valores de una editorial", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Modificar una editorial correctamente"),
			@ApiResponse(code = 409, message = "Conflicto por validaciones de editorial o porque ya existe una editorial con el mismo nombre")})
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> modificar(@PathVariable long id, @RequestBody Editorial editorial) {
		
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			Set<ConstraintViolation<Editorial>> violations = validator.validate(editorial);
			
			editorial.setId(id);
			
			if (violations.isEmpty()) {
			    /* Ha pasado la validacion*/
				if(serviceEditorial.modificar(editorial)) {
					response = new ResponseEntity<>(editorial, HttpStatus.OK);
					LOG.debug("Editorial modificada correctamente");
				}else {
					response = new ResponseEntity<>(HttpStatus.CONFLICT);
					LOG.warn("No se ha podido modificar la editorial por conflicto");
				}
			}else{
			    /* Hay fallos, la Validacion no es correcta */
				ResponseMensaje msj = new ResponseMensaje();
				msj.setMensaje("Los datos no son válidos.");
				 for (ConstraintViolation<Editorial> violation : violations) {
					 msj.getErrores().add(violation.getPropertyPath()+": "+violation.getMessage());
					 LOG.warn("Error de validacion al modificar editorial: "+violation.getPropertyPath()+": "+violation.getMessage());
				 }
				response = new ResponseEntity<>(msj, HttpStatus.CONFLICT);
			}
		}catch(MySQLIntegrityConstraintViolationException e) {
//			e.printStackTrace();
			LOG.error(e);
			ResponseMensaje msj = new ResponseMensaje("Ya existe la editorial, por favor prueba con otro nombre.");
			response = new ResponseEntity<>(msj, HttpStatus.CONFLICT);
		}catch(Exception e) {
//			e.printStackTrace();
			LOG.error(e);
		}
		 
		return response;
	}
	
	@ApiOperation(value = "Eliminar editorial", notes = "Elimina una editorial existente", produces="application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Eliminar una editorial correctamente"),
			@ApiResponse(code = 404, message = "No existe la editorial a borrar"),
			@ApiResponse(code = 409, message = "Conflicto por intentar eliminar una editorial que tiene libros asociados")})
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable long id) {
		
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			if(serviceEditorial.eliminar(id)) {
				response = new ResponseEntity<>(HttpStatus.OK);
				LOG.debug("Editorial eliminada correctamente");
			}else {
//				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
				ResponseMensaje msj = new ResponseMensaje("No se puede borrar un registro que no existe");
				response = new ResponseEntity<>(msj, HttpStatus.NOT_FOUND);
				LOG.warn("No se ha encontrado la editorial a eliminar");
			}
		}catch(MySQLIntegrityConstraintViolationException e) {
//			e.printStackTrace();
			LOG.error(e);
			ResponseMensaje msj = new ResponseMensaje("No se puede borrar la editorial porque tiene libros asociados.");
			response = new ResponseEntity<>(msj, HttpStatus.CONFLICT);
		}catch(Exception e) {
//			e.printStackTrace();
			LOG.error(e);
		}
		
		return response;
	}
	
}
