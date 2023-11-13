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

import br.edu.univille.microservcarro.entity.Cliente;
import br.edu.univille.microservcarro.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteAPIController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> listaClientes(){
        var listaClientes = service.getAll();
        return 
            new ResponseEntity<List<Cliente>>
            (listaClientes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable("id")  String id){
        var cliente = service.getById(id);
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Cliente>
            (cliente, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente){
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cliente = service.saveNew(cliente);
        return 
            new ResponseEntity<Cliente>
            (cliente, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable("id")  String id, @RequestBody Cliente cliente){
        if(cliente == null || id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cliente = service.update(id, cliente);
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Cliente>
            (cliente, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> removerCliente(@PathVariable("id")  String id){
        if(id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var cliente = service.delete(id);
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Cliente>
            (cliente, HttpStatus.OK);
    }
    
}
