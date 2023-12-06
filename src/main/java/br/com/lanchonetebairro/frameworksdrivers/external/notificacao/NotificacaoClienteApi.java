package br.com.lanchonetebairro.frameworksdrivers.external.notificacao;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificacaoClienteApi implements NotificacaoClientePort {
    public void notificaCliente(Cliente cliente, String mensagem) {
        log.info("............. Conectando a plataforma de envio de mensagem .............");
        log.info("Esta é apenas uma simulação de envio de mensagem para o cliente {} com o e-mail {}",
                cliente.getNome(), cliente.getEmail());
    }

}
