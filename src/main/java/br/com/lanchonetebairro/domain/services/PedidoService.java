package br.com.lanchonetebairro.domain.services;

import br.com.lanchonetebairro.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.domain.enums.StatusDoPedido;

import java.util.List;

public interface PedidoService {

    List<PedidoResponseDTO> buscarPorStatus(StatusDoPedido status);
    CriacaoPedidoDTO criar(CriacaoPedidoDTO clienteDTO);
    List<PedidoResponseDTO> buscarTodos();

}
