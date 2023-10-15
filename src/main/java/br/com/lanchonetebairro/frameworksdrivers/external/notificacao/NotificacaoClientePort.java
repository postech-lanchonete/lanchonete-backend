package br.com.lanchonetebairro.frameworksdrivers.external.notificacao;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;

public interface NotificacaoClientePort {
    void notificaCliente(Cliente cliente, String mensagem);
}
