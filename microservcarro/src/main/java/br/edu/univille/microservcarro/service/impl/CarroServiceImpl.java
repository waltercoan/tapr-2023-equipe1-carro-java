package br.edu.univille.microservcarro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.univille.microservcarro.entity.Carro;
import br.edu.univille.microservcarro.repository.CarroRepository;
import br.edu.univille.microservcarro.service.CarroService;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@Service
public class CarroServiceImpl implements CarroService{

    @Autowired
    private CarroRepository repository;
    private DaprClient client = new DaprClientBuilder().build();
    @Value("${app.component.topic.carro}")
    private String TOPIC_NAME;
    @Value("${app.component.service}")
	private String PUBSUB_NAME;

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
        carro = repository.save(carro);
        publicarAtualizacao(carro);
        return carro;
    }

    @Override
    public Carro update(String id, Carro carro) {
        var buscaCarroAntigo = repository.findById(id);
        if (buscaCarroAntigo.isPresent()){
            var carroAntigo = buscaCarroAntigo.get();

            //Atualizar cada atributo do objeto antigo 
            carroAntigo.setPlaca(carro.getPlaca());
            carroAntigo = repository.save(carroAntigo);
            publicarAtualizacao(carroAntigo);
            return carroAntigo;
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
    //método privado para publicar a atualização
    private void publicarAtualizacao(Carro carro){
        client.publishEvent(
					PUBSUB_NAME,
					TOPIC_NAME,
					carro).block();
    }
}
