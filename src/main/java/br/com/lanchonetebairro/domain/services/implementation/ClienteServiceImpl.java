package br.com.lanchonetebairro.domain.services.implementation;

import br.com.lanchonetebairro.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.domain.exceptions.NotFoundException;
import br.com.lanchonetebairro.domain.mappers.ClienteMapper;
import br.com.lanchonetebairro.domain.services.ClienteService;
import br.com.lanchonetebairro.infraestructure.entities.Cliente;
import br.com.lanchonetebairro.infraestructure.repositories.ClienteRepository;
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
