package br.edu.infnet.rdsdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.infnet.rdsdemo.dto.PessoaRegistroDto;
import br.edu.infnet.rdsdemo.model.Endereco;
import br.edu.infnet.rdsdemo.model.Pessoa;
import br.edu.infnet.rdsdemo.service.IEnderecoService;
import br.edu.infnet.rdsdemo.service.PessoaService;
import feign.FeignException.FeignClientException;

@Controller
@RequestMapping(value = "/editar", method = RequestMethod.GET)
public class EdicaoController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private IEnderecoService iEnderecoService;

    @ModelAttribute("pessoadto")
    public PessoaRegistroDto pessoaCadastro() {
        return new PessoaRegistroDto();
    }

    @GetMapping
    public String edicao() {
        return "redirect:/pessoas";
    }

    @GetMapping("/{id}")
    public String editarPessoa(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pessoa", pessoaService.getPessoaById(id));
        return "edicao";
    }

    @PostMapping("/{id}/")
    public String editarPessoa(@PathVariable("id") Long id, @ModelAttribute("pessoadto") @Valid PessoaRegistroDto prdto,
            BindingResult result) {

        try {
            Pessoa pessoa = pessoaService.getPessoaById(id);
            pessoa.setNome(prdto.getNome());
            pessoa.setEmail(prdto.getEmail());
            pessoa.setTelefone(prdto.getTelefone());

            Endereco endereco = iEnderecoService.pegarEndereco(prdto.getCep());
            if (endereco.getErro() || result.hasErrors()) {
                result.rejectValue("cep", null, "CEP inválido");
                return new StringBuilder().append("editar/").append(id).toString();
            } else {
                pessoa.setEndereco(endereco);
                // endereco.setPessoa(pessoa);
                // pessoaService.deletePessoaById(id);
                pessoaService.store(pessoa);
            }
            return "redirect:/pessoas";
        } catch (FeignClientException e) {
            result.rejectValue("cep", null, "CEP inválido");
            return new StringBuilder().append("editar/").append(id).toString();
        }
    }

}
