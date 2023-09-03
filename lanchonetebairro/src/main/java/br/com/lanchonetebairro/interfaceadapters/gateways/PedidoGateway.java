package br.com.lanchonetebairro.interfaceadapters.gateways;

import br.com.lanchonetebairro.enterpriserules.entities.Pedido;

public interface PedidoGateway extends Gateway<Pedido> {
    Pedido buscarPorId(Long id);
}
