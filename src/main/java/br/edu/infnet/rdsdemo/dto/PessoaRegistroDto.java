package br.edu.infnet.rdsdemo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class PessoaRegistroDto {

    @NotEmpty
    private String nome;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String cep;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
