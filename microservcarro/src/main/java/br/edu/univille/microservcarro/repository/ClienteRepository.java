package br.edu.univille.microservcarro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.edu.univille.microservcarro.entity.Cliente;

@Repository
public interface ClienteRepository 
    extends CrudRepository<Cliente,String>{
    
}
