package br.com.lanchonetebairro.domain.mappers;

import br.com.lanchonetebairro.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.infraestructure.entities.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoResponseDTO toDto(Pedido pedido);
}
