package ar.edu.ubp.das.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.ubp.das.rest.classes.Genero;
import ar.edu.ubp.das.rest.repository.GeneroRepository;

@RestController
@RequestMapping(
  path="/generos",
  produces={MediaType.APPLICATION_JSON_VALUE}
)

public class GeneroController {
	
    @Autowired
    GeneroRepository repository;

    @GetMapping(path="/listado")
    public ResponseEntity<List<Genero>> getGeneros() {
      return new ResponseEntity<>(repository.getGeneros(), HttpStatus.OK);
    }

}
