package br.com.lanchonetebairro.frameworksdrivers.external.pagamento;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusPagamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class PagamentoWebhookImpl implements PagamentoWebhook {
    SecureRandom random = new SecureRandom();

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
        log.info("............. Registrando pagamento .............");
        log.info("Pagamento no valor de R$ {} do cliente com CPF {} registrado com sucesso", pagamento.getValor().doubleValue(), pedido.getCliente().getCpf());

        simulaAcionamentoWebhookResposta(pagamento);
        return pagamento;
    }

    void recebeConfirmacaoPagamento(Pagamento pagamento) {
        log.info("............. Confirmacao pagamento recebida .............");
        try {
            recebeRespostaPagamentoUseCase.realizar(pagamento);
        } catch (Exception e) {
            log.error("Erro ao receber confirmação de pagamento");
        }
    }

    @Async
    public CompletableFuture<Void> simulaAcionamentoWebhookResposta(Pagamento pagamento) {
        return CompletableFuture.runAsync(() -> {
            try {
                int valorRandom = random.nextInt(10000);
                log.info("Delay de " + valorRandom + " milisegundos para simular o acionamento do webhook de resposta");
                Thread.sleep(valorRandom);
                StatusPagamento statusRandom = valorRandom > 1000 ? StatusPagamento.APROVADO : StatusPagamento.REPROVADO;
                pagamento.setStatus(statusRandom);
                recebeConfirmacaoPagamento(pagamento);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
