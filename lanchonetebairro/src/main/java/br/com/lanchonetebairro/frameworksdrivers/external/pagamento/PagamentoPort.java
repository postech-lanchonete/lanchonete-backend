package br.com.lanchonetebairro.frameworksdrivers.external.pagamento;

import br.com.lanchonetebairro.enterpriserules.entities.Pedido;

public interface PagamentoPort {
    void realizarPagamento(Pedido pedido);
}
