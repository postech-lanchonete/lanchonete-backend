package br.com.lanchonetebairro.adapter.driven.api.notificacao;

import br.com.lanchonetebairro.core.domain.entities.Cliente;

public interface NotificacaoEmailPort {
    void notificaCliente(Cliente cliente, String mensagem);
}
