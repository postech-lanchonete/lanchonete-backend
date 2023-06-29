package br.com.lanchonetebairro.api.dto;

import br.com.lanchonetebairro.business.enums.StatusDoPedido;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private List<ProdutoResponseDTO> produtos;
    private ClienteResponseDTO cliente;
    private StatusDoPedido status;
    private LocalDateTime dataCriacao;

    public List<ProdutoResponseDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoResponseDTO> produtos) {
        this.produtos = produtos;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public StatusDoPedido getStatus() {
        return status;
    }

    public void setStatus(StatusDoPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
