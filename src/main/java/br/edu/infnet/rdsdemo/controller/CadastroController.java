package br.edu.infnet.rdsdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.edu.infnet.rdsdemo.dto.PessoaRegistroDto;
import br.edu.infnet.rdsdemo.model.Endereco;
import br.edu.infnet.rdsdemo.model.Pessoa;
import br.edu.infnet.rdsdemo.service.AmazonService;
import br.edu.infnet.rdsdemo.service.IEnderecoService;
import br.edu.infnet.rdsdemo.service.PessoaService;
import feign.FeignException.FeignClientException;

@EnableFeignClients
@Controller
@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
public class CadastroController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private AmazonService amazonService;

    @Autowired
    private IEnderecoService iEnderecoService;

    @ModelAttribute("pessoadto")
    public PessoaRegistroDto pessoaCadastro() {
        return new PessoaRegistroDto();
    }

    @GetMapping
    public String paginaCadastrar() {
        return "cadastro";
    }

    @PostMapping("/")
    public String cadastrarPessoa(@RequestParam("file") MultipartFile file, @ModelAttribute("pessoadto") @Valid PessoaRegistroDto prdto, BindingResult result) {
        try {
            amazonService.uploadFile(file);
            Endereco endereco = iEnderecoService.pegarEndereco(prdto.getCep());
            
            if(endereco.getErro() || result.hasErrors()) {
                result.rejectValue("cep", null, "CEP inválido");
                return "cadastro";
            } else {
                Pessoa pessoa = new Pessoa(prdto.getNome(), prdto.getEmail(), prdto.getTelefone(), endereco);
                endereco.setPessoa(pessoa);
                pessoaService.store(pessoa);
            }

            return "redirect:/pessoas";
        } catch (FeignClientException e) {
            result.rejectValue("cep", null, "CEP inválido");
            return "cadastro";
        }

    }
}
