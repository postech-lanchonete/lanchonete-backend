package br.com.lanchonetebairro.core.applications.usecases.implementation.pedido;

import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.PedidoMapper;
import br.com.lanchonetebairro.core.applications.services.PedidoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoBuscarPorTodosUseCase implements UseCase.NoInputUseCase<List<PedidoResponseDTO>> {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoBuscarPorTodosUseCase(PedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }


    @Override
    public List<PedidoResponseDTO> realizar(Void entrada) {
        return pedidoService.buscarTodos().stream().map(pedidoMapper::toDto).collect(Collectors.toList());
    }

}
