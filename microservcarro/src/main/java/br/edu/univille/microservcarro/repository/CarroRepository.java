package br.edu.univille.microservcarro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.univille.microservcarro.entity.Carro;

@Repository
public interface CarroRepository 
    extends CrudRepository<Carro,String>{
    
}
