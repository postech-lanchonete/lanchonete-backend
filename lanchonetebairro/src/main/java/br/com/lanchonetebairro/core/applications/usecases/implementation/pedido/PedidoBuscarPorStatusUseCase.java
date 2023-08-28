package br.com.lanchonetebairro.core.applications.usecases.implementation.pedido;

import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.PedidoMapper;
import br.com.lanchonetebairro.core.applications.services.PedidoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoBuscarPorStatusUseCase implements UseCase<StatusDoPedido, List<PedidoResponseDTO>> {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoBuscarPorStatusUseCase(PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }


    @Override
    public List<PedidoResponseDTO> realizar(StatusDoPedido status) {
        return pedidoService.buscarPorStatus(status).stream().map(pedidoMapper::toDto).toList();
    }

}
