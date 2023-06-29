package br.com.lanchonetebairro.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Objeto de transferência de dados para pedido")
public class CriacaoPedidoDTO {

    @Schema(description = "CPF do cliente que fez o pedido.")
    private String cpfCliente;

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
