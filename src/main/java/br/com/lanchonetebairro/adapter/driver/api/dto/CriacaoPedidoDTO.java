package br.com.lanchonetebairro.adapter.driver.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "Objeto de transferência de dados para pedido")
public class CriacaoPedidoDTO {
    @NotEmpty(message = "Deve ser informado o CPF do cliente que fez o pedido.")
    @Schema(description = "CPF do cliente que fez o pedido.")
    private String cpfCliente;

    @NotEmpty(message = "A lista de produtos não pode ser vazia.")
    @Schema(description = "Lista de identificadores dos produtos.")
    private List<Long> idsProdutos;

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public List<Long> getIdsProdutos() {
        return idsProdutos;
    }

    public void setIdsProdutos(List<Long> idsProdutos) {
        this.idsProdutos = idsProdutos;
    }
}
