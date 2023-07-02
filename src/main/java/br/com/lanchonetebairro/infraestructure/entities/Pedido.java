package br.com.lanchonetebairro.infraestructure.entities;

import br.com.lanchonetebairro.domain.enums.StatusDoPedido;
import br.com.lanchonetebairro.domain.exceptions.NegocioException;
import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    @Enumerated(EnumType.STRING)
    private StatusDoPedido status;

    private LocalDateTime dataCriacao;

    public Pedido(Cliente cliente, List<Produto> produtos) {
        this.status = StatusDoPedido.RECEBIDO;
        this.dataCriacao = LocalDateTime.now();
        this.cliente = cliente;
        this.produtos = produtos;
        validarPedido();
    }

    private void validarPedido() {
        if (ObjectUtils.isEmpty(cliente)) {
            throw new NegocioException("Cliente não pode ser vazio ao criar um novo pedido");
        }
        if (CollectionUtils.isEmpty(produtos)) {
            throw new NegocioException("Deve ser informado ao menos um produto válido para criar um pedido");
        }
    }

    public Pedido() {
        this.status = StatusDoPedido.RECEBIDO;
        this.dataCriacao = LocalDateTime.now();
    }

    public void mudarStatus() {
        switch (this.status) {
            case RECEBIDO -> this.status = StatusDoPedido.EM_PREPARACAO;
            case EM_PREPARACAO -> this.status = StatusDoPedido.PRONTO;
            case PRONTO -> this.status = StatusDoPedido.FINALIZADO;
            default -> {
                // Manter o status em FINALIZADO
            }
        }
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public StatusDoPedido getStatus() {
        return status;
    }

    public void setStatus(StatusDoPedido statusDoPedido) {
        this.status = statusDoPedido;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}