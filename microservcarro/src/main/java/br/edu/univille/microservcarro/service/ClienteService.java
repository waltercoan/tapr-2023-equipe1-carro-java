package br.edu.univille.microservcarro.service;

import java.util.List;
import br.edu.univille.microservcarro.entity.Cliente;

public interface ClienteService {
    public List<Cliente> getAll();
    public Cliente getById(String id);
    public Cliente saveNew(Cliente cliente);
    public Cliente update(String id, Cliente cliente);
    public Cliente update(Cliente cliente);
    public Cliente delete(String id);
}
