package ar.edu.ubp.das.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.ubp.das.rest.classes.Nacionalidad;
import ar.edu.ubp.das.rest.repository.NacionalidadRepository;

@RestController
@RequestMapping(
  path="/nacionalidades",
  produces={MediaType.APPLICATION_JSON_VALUE}
)


public class NacionalidadController {
	
    @Autowired
    NacionalidadRepository repository;
    
    @GetMapping(path="/listado")
    public ResponseEntity<List<Nacionalidad>> getNacionalidades() {
      return new ResponseEntity<>(repository.getNacionalidades(), HttpStatus.OK);
    }
	
	
}
