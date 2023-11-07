package br.edu.univille.microservcarro.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univille.microservcarro.entity.Carro;
import br.edu.univille.microservcarro.service.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroAPIController {

    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<List<Carro>> listaCarros(){
        var listaCarros = service.getAll();
        return 
            new ResponseEntity<List<Carro>>
            (listaCarros, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarCarro(@PathVariable("id")  String id){
        var carro = service.getById(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Carro>
            (carro, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Carro> inserirCarro(@RequestBody Carro carro){
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carro = service.saveNew(carro);
        return 
            new ResponseEntity<Carro>
            (carro, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable("id")  String id, @RequestBody Carro carro){
        if(carro == null || id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        carro = service.update(id, carro);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Carro>
            (carro, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Carro> removerCarro(@PathVariable("id")  String id){
        if(id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var carro = service.delete(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Carro>
            (carro, HttpStatus.OK);
    }
}
