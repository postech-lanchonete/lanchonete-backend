package br.com.lanchonetebairro.core.applications.services.implementation;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.core.applications.exceptions.NotFoundException;
import br.com.lanchonetebairro.core.applications.mappers.ClienteMapper;
import br.com.lanchonetebairro.core.applications.services.ClienteService;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import br.com.lanchonetebairro.adapter.driven.infraestructure.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public ClienteResponseDTO buscarPorCPF(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(String.format("Cliente não encontrado com o cpf %s", cpf)));
        return clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteResponseDTO criarCliente(CriacaoClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        clienteRepository.save(cliente);
        return clienteMapper.toDto(cliente);
    }
}
