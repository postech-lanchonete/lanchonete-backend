package br.com.lanchonetebairro.interfaceadapters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@SuppressWarnings("unused")
@Schema(description = "Objeto de transferência de dados para pedido")
public class CriacaoPedidoDTO {
    @NotEmpty(message = "Deve ser informado o CPF do cliente que fez o pedido.")
    @Schema(description = "CPF do cliente que fez o pedido.")
    private String cpfCliente;

    @NotEmpty(message = "A lista de produtos não pode ser vazia.")
    @Schema(description = "Lista de identificadores dos produtos.")
    private List<Long> idsProdutos;

}
