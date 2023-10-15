package br.com.lanchonetebairro.interfaceadapters.adapter;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.interfaceadapters.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteAdapter {

    ClienteResponseDTO toDto(Cliente cliente);
    Cliente toEntity(CriacaoClienteDTO clienteDto);

    default Cliente toEntity(String cpf) {
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        return cliente;
    }
}
