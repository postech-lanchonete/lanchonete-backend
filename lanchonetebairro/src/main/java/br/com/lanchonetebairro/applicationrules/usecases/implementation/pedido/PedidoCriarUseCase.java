package br.com.lanchonetebairro.applicationrules.usecases.implementation.pedido;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.frameworksdrivers.external.pagamento.PagamentoPort;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import org.springframework.stereotype.Component;

@Component("pedidoCriarUseCase")
public class PedidoCriarUseCase implements UseCase<Pedido, Pedido> {

    private final PedidoGateway pedidoGateway;
    private final PagamentoPort pagamentoPort;

    public PedidoCriarUseCase(PedidoGateway pedidoGateway, PagamentoPort pagamentoPort) {
        this.pedidoGateway = pedidoGateway;
        this.pagamentoPort = pagamentoPort;
    }

    @Override
    public Pedido realizar(Pedido pedido) {
        pagamentoPort.realizarPagamento(pedido);
        pedidoGateway.salvar(pedido);
        return pedido;
    }

}
