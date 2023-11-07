package br.edu.univille.microservcarro.service;
import java.util.List;
import br.edu.univille.microservcarro.entity.Carro;

public interface CarroService {
    public List<Carro> getAll();
    public Carro getById(String id);
    public Carro saveNew(Carro carro);
    public Carro update(String id, Carro carro);
    public Carro delete(String id);
}
