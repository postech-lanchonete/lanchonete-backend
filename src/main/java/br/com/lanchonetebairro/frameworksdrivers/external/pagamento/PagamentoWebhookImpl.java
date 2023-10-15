package br.com.lanchonetebairro.frameworksdrivers.external.pagamento;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusPagamento;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class PagamentoWebhookImpl implements PagamentoWebhook {

    private final UseCase<Pagamento, Pedido> recebeRespostaPagamentoUseCase;

    public PagamentoWebhookImpl(@Qualifier("recebeRespostaPagamentoUseCase") UseCase<Pagamento, Pedido> recebeRespostaPagamentoUseCase) {
        this.recebeRespostaPagamentoUseCase = recebeRespostaPagamentoUseCase;
    }

    @Override
    public Pagamento registrarPagamento(Pedido pedido) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(pedido.calculaValorTotal());
        pagamento.setStatus(StatusPagamento.PENDENTE);
        pagamento.setPedido(pedido);
        System.out.println("............. Registrando pagamento .............");
        System.out.printf("Pagamento no valor de R$ %,.2f do cliente com CPF %s registrado com sucesso\n", pagamento.getValor().doubleValue(), pedido.getCliente().getCpf());

        simulaAcionamentoWebhookResposta(pagamento);
        return pagamento;
    }

    public void recebeConfirmacaoPagamento(Pagamento pagamento) {
        System.out.println("............. Confirmacao pagamento recebida .............");
        try {
            recebeRespostaPagamentoUseCase.realizar(pagamento);
        } catch (Exception e) {
            System.out.println("Erro ao receber confirmação de pagamento");
        }
    }

    @Async
    public CompletableFuture<Void> simulaAcionamentoWebhookResposta(Pagamento pagamento) {
        return CompletableFuture.runAsync(() -> {
            try {
                int valorRandom = (int) (Math.random() * 10000);
                System.out.println("Delay de " + valorRandom + " milisegundos para simular o acionamento do webhook de resposta");
                Thread.sleep(valorRandom);
                StatusPagamento statusRandom = valorRandom > 100 ? StatusPagamento.APROVADO : StatusPagamento.REPROVADO;
                pagamento.setStatus(statusRandom);
                recebeConfirmacaoPagamento(pagamento);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
    }
}
