package br.edu.univille.microservcarro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservcarro.entity.Cliente;
import br.edu.univille.microservcarro.repository.ClienteRepository;
import br.edu.univille.microservcarro.service.ClienteService;

@Service
public class ClienteServiceImpl 
    implements ClienteService{
    
    @Autowired
    private ClienteRepository repository;

    @Override
    public List<Cliente> getAll() {
        var iterador = repository.findAll();
        List<Cliente> listaClientes = new ArrayList<>();

        iterador.forEach(listaClientes::add);

        return listaClientes;
    }

    @Override
    public Cliente getById(String id) {
        var cliente = repository.findById(id);
        if(cliente.isPresent())
            return cliente.get();
        return null;
    }

    @Override
    public Cliente saveNew(Cliente cliente) {
        cliente.setId(null);
        return repository.save(cliente);
    }

    @Override
    public Cliente update(String id, Cliente cliente) {
        var buscaClienteAntigo = repository.findById(id);
        if (buscaClienteAntigo.isPresent()){
            var clienteAntigo = buscaClienteAntigo.get();

            //Atualizar cada atributo do objeto antigo 
            clienteAntigo.setNome(cliente.getNome());
            clienteAntigo.setEndereco(cliente.getEndereco());
            
            return repository.save(clienteAntigo);
        }
        return null;
    }

    @Override
    public Cliente delete(String id) {
        var buscaCliente = repository.findById(id);
        if (buscaCliente.isPresent()){
            var cliente = buscaCliente.get();

            repository.delete(cliente);

            return cliente;
        }
        return null;
    }

    @Override
    public Cliente update(Cliente cliente) {
        return repository.save(cliente);
    }
    
}
