package ar.edu.ubp.das.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.ubp.das.rest.classes.Actividad;
import ar.edu.ubp.das.rest.classes.Equipo;
import ar.edu.ubp.das.rest.classes.Persona;
import ar.edu.ubp.das.rest.classes.PersonaData;
import ar.edu.ubp.das.rest.repository.PersonaRepository;

@RestController
@RequestMapping(
  path="/personas",
  produces={MediaType.APPLICATION_JSON_VALUE}
)

public class PersonaController {
	

    @Autowired
    PersonaRepository repository;

    @GetMapping(path="/listado")
    public ResponseEntity<List<Persona>> getPersonas() {
      return new ResponseEntity<>(repository.getPersonas(), HttpStatus.OK);
    }
    
    @GetMapping(path="/equipo/{nro_persona}")
    public ResponseEntity<List<Equipo>> getEquipos(@PathVariable("nro_persona") int nroPersona) {
      return new ResponseEntity<>(repository.getEquipoPersona(nroPersona), HttpStatus.OK);
    }
    
    @DeleteMapping(path="/{nro_persona}")
    public ResponseEntity<?> deletePersona(@PathVariable("nro_persona") int nroPersona) {
      return new ResponseEntity<>(repository.deletePersona(nroPersona), HttpStatus.OK);
    }
    
    @GetMapping(path="/actividad/{nro_persona}")
    public ResponseEntity<List<Actividad>> getActividades(@PathVariable("nro_persona") int nroPersona) {
      return new ResponseEntity<>(repository.getActividadesPersona(nroPersona), HttpStatus.OK);
    }
    
    @PostMapping(
    	path="/persona",
    	consumes={MediaType.APPLICATION_JSON_VALUE}
    )
	public ResponseEntity<PersonaData> insPersona(@RequestBody PersonaData persona) {
    	return new ResponseEntity<>(repository.insPersona(persona), HttpStatus.CREATED);
	}
	    		
}
