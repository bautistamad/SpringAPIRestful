package ar.edu.ubp.das.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.ubp.das.rest.classes.Actividad;
import ar.edu.ubp.das.rest.repository.ActividadRepository;

@RestController
@RequestMapping(
  path="/actividades",
  produces={MediaType.APPLICATION_JSON_VALUE}
)

public class ActividadController {
	
    @Autowired
    ActividadRepository repository;

    @GetMapping(path="/listado")
    public ResponseEntity<List<Actividad>> getActividads() {
      return new ResponseEntity<>(repository.getActividades(), HttpStatus.OK);
    }

}
