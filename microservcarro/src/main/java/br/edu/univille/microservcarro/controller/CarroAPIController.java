package br.edu.univille.microservcarro.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univille.microservcarro.entity.Carro;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroAPIController {
    @GetMapping
    public ResponseEntity<List<Carro>> listaCarros(){
        var listaCarros = new ArrayList<Carro>();
        
        return 
            new ResponseEntity<List<Carro>>
            (listaCarros, HttpStatus.OK);
    }
}
