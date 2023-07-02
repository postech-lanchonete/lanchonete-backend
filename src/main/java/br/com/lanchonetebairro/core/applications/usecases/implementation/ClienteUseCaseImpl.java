package br.com.lanchonetebairro.core.applications.usecases.implementation;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.core.applications.mappers.ClienteMapper;
import br.com.lanchonetebairro.core.applications.services.ClienteService;
import br.com.lanchonetebairro.core.applications.usecases.ClienteUseCase;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteUseCaseImpl(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteResponseDTO buscarPorCPF(String cpf) {
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        return clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteResponseDTO criar(CriacaoClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        clienteService.salvar(cliente);
        return clienteMapper.toDto(cliente);
    }
}
