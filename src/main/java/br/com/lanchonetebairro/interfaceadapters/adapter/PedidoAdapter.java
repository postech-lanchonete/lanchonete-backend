package br.com.lanchonetebairro.interfaceadapters.adapter;

import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import br.com.lanchonetebairro.interfaceadapters.dto.PedidoResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoAdapter {
    PedidoResponseDTO toDto(Pedido pedido);

    default Pedido toEntity(StatusDoPedido statusDoPedido){
        Pedido pedido = new Pedido();
        pedido.setStatus(statusDoPedido);
        return pedido;
    }
}
