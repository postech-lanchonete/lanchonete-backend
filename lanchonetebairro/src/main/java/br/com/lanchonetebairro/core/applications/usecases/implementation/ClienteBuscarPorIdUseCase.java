package br.com.lanchonetebairro.core.applications.usecases.implementation;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.core.applications.exceptions.NotFoundException;
import br.com.lanchonetebairro.core.applications.mappers.ClienteMapper;
import br.com.lanchonetebairro.core.applications.services.ClienteService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class ClienteBuscarPorIdUseCase implements UseCase<String, ClienteResponseDTO> {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteBuscarPorIdUseCase(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }
    @Override
    public ClienteResponseDTO realizar(String cpf) {
        Cliente clienteExample = new Cliente();
        clienteExample.setCpf(cpf);
        Cliente cliente = clienteService.buscarPor(Example.of(clienteExample))
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Cliente não encontrado com o cpf %s", cpf)));
        return clienteMapper.toDto(cliente);
    }
}
