package br.edu.infnet.rdsdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.rdsdemo.model.Endereco;

@FeignClient(url = "https://viacep.com.br/ws/", name="IEnderecoService")
public interface IEnderecoService {
    @GetMapping(value = "{cep}/json")
    Endereco pegarEndereco(@PathVariable("cep") String cep);
}

