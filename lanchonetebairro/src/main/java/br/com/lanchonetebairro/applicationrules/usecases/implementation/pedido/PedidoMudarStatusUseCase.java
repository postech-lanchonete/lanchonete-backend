package br.com.lanchonetebairro.applicationrules.usecases.implementation.pedido;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import br.com.lanchonetebairro.frameworksdrivers.external.notificacao.NotificacaoClientePort;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import org.springframework.stereotype.Component;

@Component("pedidoMudarStatusUseCase")
public class PedidoMudarStatusUseCase implements UseCase<Long, Pedido> {

    private final PedidoGateway pedidoGateway;
    private final NotificacaoClientePort notificacaoClientePort;

    public PedidoMudarStatusUseCase(PedidoGateway pedidoGateway, NotificacaoClientePort notificacaoClientePort) {
        this.pedidoGateway = pedidoGateway;
        this.notificacaoClientePort = notificacaoClientePort;
    }


    @Override
    public Pedido realizar(Long id) {
        Pedido pedido = pedidoGateway.buscarPorId(id);
        this.mudarStatus(pedido);
        pedidoGateway.salvar(pedido);
        if (pedido.getStatus() == StatusDoPedido.FINALIZADO) {
            notificacaoClientePort.notificaCliente(pedido.getCliente(), "Seu pedido está pronto. Venha buscar!");
        }
        return pedido;
    }

    private void mudarStatus(Pedido pedido) {
        switch (pedido.getStatus()) {
            case RECEBIDO -> pedido.setStatus(StatusDoPedido.EM_PREPARACAO);
            case EM_PREPARACAO -> pedido.setStatus(StatusDoPedido.PRONTO);
            case PRONTO -> pedido.setStatus(StatusDoPedido.FINALIZADO);
            default -> {
                // Manter o status em FINALIZADO
            }
        }
    }

}
