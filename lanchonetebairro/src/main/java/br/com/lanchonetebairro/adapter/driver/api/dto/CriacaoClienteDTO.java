package br.com.lanchonetebairro.adapter.driver.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CriacaoClienteDTO {
    @NotBlank(message = "Nome é mandatorio")
    private String nome;
    @NotBlank(message = "Sobrenome é mandatorio")
    private String sobrenome;
    @NotBlank(message = "E-mail é mandatorio")
    @Email(message = "O e-mail precisa estar em um formato correto, por exemplo teste@teste.com.br")
    private String email;
    @NotBlank(message = "CPF é mandatorio")
    private String cpf;

    @NotBlank(message = "Senha é mandatorio")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
