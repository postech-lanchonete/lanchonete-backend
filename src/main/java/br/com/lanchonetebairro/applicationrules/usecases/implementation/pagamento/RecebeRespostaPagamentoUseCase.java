package br.com.lanchonetebairro.applicationrules.usecases.implementation.pagamento;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusPagamento;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import org.springframework.stereotype.Component;

@Component("recebeRespostaPagamentoUseCase")
public class RecebeRespostaPagamentoUseCase implements UseCase<Pagamento, Pedido> {

    private final PedidoGateway pedidoGateway;

    public RecebeRespostaPagamentoUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido realizar(Pagamento pagamento) {
        Pedido pedido = pedidoGateway.buscarPorId(pagamento.getPedido().getId());
        pedido.setPagamento(pagamento);
        if (pagamento.getStatus() == StatusPagamento.APROVADO) {
            System.out.printf("Pagamento no valor de R$ %,.2f do cliente com CPF %s confirmado com sucesso\n", pagamento.getValor().doubleValue(), pagamento.getPedido().getCliente().getCpf());
            pedido.setStatus(StatusDoPedido.EM_PREPARACAO);
        } else {
            System.out.printf("Pagamento no valor de R$ %,.2f do cliente com CPF %s REPROVADO\n", pagamento.getValor().doubleValue(), pagamento.getPedido().getCliente().getCpf());
            pedido.setStatus(StatusDoPedido.FINALIZADO);
        }
        return pedidoGateway.salvar(pedido);
    }
}
