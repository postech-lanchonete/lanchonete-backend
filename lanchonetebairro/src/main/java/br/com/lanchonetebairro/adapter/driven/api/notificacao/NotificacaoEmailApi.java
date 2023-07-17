package br.com.lanchonetebairro.adapter.driven.api.notificacao;

import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoEmailApi implements NotificacaoEmailPort {
    public void notificaCliente(Cliente cliente, String mensagem) {
        System.out.printf("Esta é apenas uma simulação de envio de e-mail para o cliente %s com o e-mail %s", cliente.getNome(), cliente.getEmail());
    }

}
