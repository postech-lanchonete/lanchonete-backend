package br.com.lanchonetebairro.adapter.driven.api.pagamento;

import br.com.lanchonetebairro.core.domain.entities.Cliente;
import br.com.lanchonetebairro.core.domain.entities.Pedido;

public interface PagamentoPort {
    void realizarPagamento(Cliente cliente, Pedido pedido);
}
