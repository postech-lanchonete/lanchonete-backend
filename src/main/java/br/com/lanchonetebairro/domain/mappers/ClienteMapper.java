package br.com.lanchonetebairro.domain.mappers;

import br.com.lanchonetebairro.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.infraestructure.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toDto(Cliente cliente);
    Cliente toEntity(CriacaoClienteDTO clienteDto);
}
