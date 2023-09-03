package br.com.lanchonetebairro.frameworksdrivers.external.pagamento;

import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PagamentoApi implements PagamentoPort {
    public void realizarPagamento(Pedido pedido) {
        System.out.println("............. Conectando a plataforma de pagamento .............");
        System.out.printf("Esta é apenas uma simulação de pagamento no valor de R$ %,.2f do cliente com CPF %s", pedido.calculaValorTotal().doubleValue(), pedido.getCliente().getCpf());
        System.out.println("............. XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX .............");
    }

}
