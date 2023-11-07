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

        return listaCarros;
    }

    @Override
    public Carro getById(String id) {
        var carro = repository.findById(id);
        if(carro.isPresent())
            return carro.get();
        return null;
    }

    @Override
    public Carro saveNew(Carro carro) {
        carro.setId(null);
        return repository.save(carro);
    }

    @Override
    public Carro update(String id, Carro carro) {
        var buscaCarroAntigo = repository.findById(id);
        if (buscaCarroAntigo.isPresent()){
            var carroAntigo = buscaCarroAntigo.get();

            //Atualizar cada atributo do objeto antigo 
            carroAntigo.setPlaca(carro.getPlaca());
            
            return repository.save(carroAntigo);
        }
        return null;
    }

    @Override
    public Carro delete(String id) {
        var buscaCarro = repository.findById(id);
        if (buscaCarro.isPresent()){
            var carro = buscaCarro.get();

            repository.delete(carro);

            return carro;
        }
        return null;
    }
}
