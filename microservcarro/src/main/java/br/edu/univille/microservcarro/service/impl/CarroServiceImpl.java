package br.edu.univille.microservcarro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservcarro.entity.Carro;
import br.edu.univille.microservcarro.repository.CarroRepository;
import br.edu.univille.microservcarro.service.CarroService;

@Service
public class CarroServiceImpl implements CarroService{

    @Autowired
    private CarroRepository repository;

    @Override
    public List<Carro> getAll() {
        var iterador = repository.findAll();
        List<Carro> listaCarros = new ArrayList<>();

        iterador.forEach(listaCarros::add);
        /*while(iterador.iterator().hasNext()){
            var umItem = iterador.iterator().next();
            listaCarros.add(umItem);
        }*/

        return listaCarros;
    }
    
}
