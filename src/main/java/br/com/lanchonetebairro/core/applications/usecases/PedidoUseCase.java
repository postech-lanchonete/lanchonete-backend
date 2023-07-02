package br.com.lanchonetebairro.core.applications.usecases;

import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;

import java.util.List;

public interface PedidoUseCase {

    List<PedidoResponseDTO> buscarPorStatus(StatusDoPedido status);
    PedidoResponseDTO criar(CriacaoPedidoDTO clienteDTO);
    List<PedidoResponseDTO> buscarTodos();

    PedidoResponseDTO mudarStatus(Long id);
}
