package br.com.lanchonetebairro.interfaceadapters.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("unused")
public class ClienteResponseDTO {
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;
}
