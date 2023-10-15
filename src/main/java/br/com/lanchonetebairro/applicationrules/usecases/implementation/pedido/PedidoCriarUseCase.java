package br.com.lanchonetebairro.applicationrules.usecases.implementation.pedido;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.frameworksdrivers.external.pagamento.PagamentoWebhook;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("pedidoCriarUseCase")
public class PedidoCriarUseCase implements UseCase<Pedido, Pedido> {

    private final PedidoGateway pedidoGateway;
    private final PagamentoWebhook pagamentoWebhook;

    public PedidoCriarUseCase(PedidoGateway pedidoGateway, PagamentoWebhook pagamentoWebhook) {
        this.pedidoGateway = pedidoGateway;
        this.pagamentoWebhook = pagamentoWebhook;
    }

    @Override
    @Transactional
    public Pedido realizar(Pedido pedido) {
        Pagamento pagamento = pagamentoWebhook.registrarPagamento(pedido);
        pedido.setPagamento(pagamento);
        pedidoGateway.salvar(pedido);
        return pedido;
    }

}
