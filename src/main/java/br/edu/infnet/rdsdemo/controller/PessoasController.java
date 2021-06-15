package br.edu.infnet.rdsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.infnet.rdsdemo.service.PessoaService;

@Controller
@RequestMapping(value = "/pessoas", method = RequestMethod.GET)
public class PessoasController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public String こんいちは(Model model) {
        model.addAttribute("pessoas", pessoaService.getPessoas());
        return "pessoas";
    }

    @PostMapping("/buscar")
    public String getPessoa(@RequestParam("email") String email, Model model) {
        model.addAttribute("pessoas", pessoaService.getPessoasByEmail(email));
        return "pessoas";
    }

}
