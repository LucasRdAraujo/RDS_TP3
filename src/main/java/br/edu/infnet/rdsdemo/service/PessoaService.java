package br.edu.infnet.rdsdemo.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import br.edu.infnet.rdsdemo.model.Pessoa;
import br.edu.infnet.rdsdemo.repository.PessoaRepository;

@Service
public class PessoaService implements IPessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Collection<Pessoa> getPessoas() {
        return (Collection<Pessoa>) pessoaRepository.findAll();
    }

    @Override
    public void store(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    // @Override
    // public void update(Pessoa pessoa) {
    //     pessoaRepository.
    // }

    @Override
    public void deletePessoaById(Long id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public Pessoa getPessoaById(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Pessoa> getPessoasByEmail(String email) {
        return Streamable.of(pessoaRepository.findAll()).toList().stream().filter(p -> p.getEmail().equals(email)).collect(Collectors.toList());
    }

}
