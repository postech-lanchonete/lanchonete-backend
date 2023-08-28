package br.com.lanchonetebairro.core.applications.usecases.implementation.pedido;

import br.com.lanchonetebairro.adapter.driven.api.notificacao.NotificacaoEmailPort;
import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.PedidoMapper;
import br.com.lanchonetebairro.core.applications.services.PedidoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.entities.Pedido;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMudarStatusUseCase implements UseCase<Long, PedidoResponseDTO> {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;
    private final NotificacaoEmailPort notificacaoEmailPort;

    public PedidoMudarStatusUseCase(PedidoService pedidoService, PedidoMapper pedidoMapper, NotificacaoEmailPort notificacaoEmailPort) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
        this.notificacaoEmailPort = notificacaoEmailPort;
    }


    @Override
    public PedidoResponseDTO realizar(Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        pedido.mudarStatus();
        pedidoService.salvar(pedido);
        if (pedido.getStatus() == StatusDoPedido.FINALIZADO) {
            notificacaoEmailPort.notificaCliente(pedido.getCliente(), "Seu pedido está pronto. Venha buscar!");
        }
        return pedidoMapper.toDto(pedido);
    }

}
