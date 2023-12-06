package br.com.lanchonetebairro.interfaceadapters.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
