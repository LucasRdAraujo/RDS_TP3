package br.edu.infnet.rdsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.infnet.rdsdemo.service.PessoaService;

@Controller
@RequestMapping(value = "/deletar", method = RequestMethod.GET)
public class DeletarController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String deletar() {
        return "redirect:/pessoas";
    }

    @GetMapping("/{id}")
    public String deletarPessoa(@PathVariable("id") Long id, Model model) {
        pessoaService.deletePessoaById(id);
        return "redirect:/pessoas";
    }


}
