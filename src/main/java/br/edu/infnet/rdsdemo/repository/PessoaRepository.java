package br.edu.infnet.rdsdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.edu.infnet.rdsdemo.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    
}
