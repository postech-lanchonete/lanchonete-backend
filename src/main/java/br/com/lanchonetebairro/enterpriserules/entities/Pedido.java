package br.com.lanchonetebairro.enterpriserules.entities;

import br.com.lanchonetebairro.applicationrules.exceptions.NegocioException;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuppressWarnings("unused")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    @Enumerated(EnumType.STRING)
    private StatusDoPedido status;

    private LocalDateTime dataCriacao;

    public Pedido() {
    }

    public Pedido(Cliente cliente, List<Produto> produtos) {
        this.status = StatusDoPedido.RECEBIDO;
        this.dataCriacao = LocalDateTime.now();
        this.cliente = cliente;
        this.produtos = produtos;
        validar();
    }

    private void validar() {
        if (ObjectUtils.isEmpty(cliente)) {
            throw new NegocioException("Cliente não pode ser vazio ao criar um novo pedido");
        }
        if (CollectionUtils.isEmpty(produtos)) {
            throw new NegocioException("Deve ser informado ao menos um produto válido para criar um pedido");
        }
    }

    public BigDecimal calculaValorTotal() {
        return produtos.stream()
                .map(Produto::getPreco)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}