package br.com.lanchonetebairro.adapter.driven.api.pagamento;

import br.com.lanchonetebairro.core.domain.entities.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PagamentoApi implements PagamentoPort {
    public void realizarPagamento(Pedido pedido) {
        System.out.printf("Esta é apenas uma simulação de pagamento no valor de R$ %,.2f do cliente com CPF %s", pedido.calculaValorTotal().doubleValue(), pedido.getCliente().getCpf());
    }

}
