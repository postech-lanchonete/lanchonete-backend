package br.com.lanchonetebairro.core.applications.mappers;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponseDTO toDto(Cliente cliente);
    Cliente toEntity(CriacaoClienteDTO clienteDto);
}
