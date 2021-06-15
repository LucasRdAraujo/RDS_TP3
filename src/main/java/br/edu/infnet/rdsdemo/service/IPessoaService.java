package br.edu.infnet.rdsdemo.service;

import java.util.Collection;

import br.edu.infnet.rdsdemo.model.Pessoa;

public interface IPessoaService {
    void store(Pessoa pessoa);
    void deletePessoaById(Long id);
    Pessoa getPessoaById(Long id);
    Collection<Pessoa> getPessoasByEmail(String email);
    Collection<Pessoa> getPessoas();
}
