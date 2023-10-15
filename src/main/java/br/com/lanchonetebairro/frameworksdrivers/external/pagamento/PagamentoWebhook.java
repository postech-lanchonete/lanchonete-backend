package br.com.lanchonetebairro.frameworksdrivers.external.pagamento;

import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;

public interface PagamentoWebhook {
    Pagamento registrarPagamento(Pedido pedido);
}
