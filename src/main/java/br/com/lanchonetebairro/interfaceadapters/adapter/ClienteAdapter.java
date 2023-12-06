package br.com.lanchonetebairro.interfaceadapters.adapter;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.interfaceadapters.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteAdapter {

    ClienteResponseDTO toDto(Cliente cliente);

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(CriacaoClienteDTO clienteDto);

    default Cliente toEntity(String cpf) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        return cliente;
    }
}
