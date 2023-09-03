package br.com.lanchonetebairro.frameworksdrivers.external.notificacao;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoClienteApi implements NotificacaoClientePort {
    public void notificaCliente(Cliente cliente, String mensagem) {
        System.out.printf("Esta é apenas uma simulação de envio de e-mail para o cliente %s com o e-mail %s", cliente.getNome(), cliente.getEmail());
    }

}
