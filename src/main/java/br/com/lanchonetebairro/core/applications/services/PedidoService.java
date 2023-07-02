package br.com.lanchonetebairro.core.applications.services;

import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.domain.entities.Pedido;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> buscarTodos();

    Pedido salvar(Pedido pedido);

    Pedido buscarPorId(Long id);
    List<Pedido> buscarPorStatus(StatusDoPedido status);
}
