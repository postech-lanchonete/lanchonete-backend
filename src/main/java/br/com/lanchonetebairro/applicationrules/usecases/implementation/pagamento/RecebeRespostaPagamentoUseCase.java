package br.com.lanchonetebairro.applicationrules.usecases.implementation.pagamento;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusPagamento;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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
        double valor = pagamento.getValor().doubleValue();
        String cpf = pagamento.getPedido().getCliente().getCpf();
        if (pagamento.getStatus() == StatusPagamento.APROVADO) {
            log.info("Pagamento no valor de R$ {} do cliente com CPF {} confirmado com sucesso", valor, cpf);
            pedido.setStatus(StatusDoPedido.EM_PREPARACAO);
        } else {
            log.info("Pagamento no valor de R$ {} do cliente com CPF {} REPROVADO   ", valor, cpf);
            pedido.setStatus(StatusDoPedido.FINALIZADO);
        }
        return pedidoGateway.salvar(pedido);
    }
}
