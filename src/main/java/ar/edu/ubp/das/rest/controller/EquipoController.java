package ar.edu.ubp.das.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.ubp.das.rest.classes.Equipo;
import ar.edu.ubp.das.rest.repository.EquipoRepository;

@RestController
@RequestMapping(
  path="/equipos",
  produces={MediaType.APPLICATION_JSON_VALUE}
)

public class EquipoController {
	
    @Autowired
    EquipoRepository repository;

    @GetMapping(path="/listado")
    public ResponseEntity<List<Equipo>> getEquipos() {
      return new ResponseEntity<>(repository.getEquipos(), HttpStatus.OK);
    }

}
