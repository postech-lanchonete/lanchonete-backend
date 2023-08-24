package br.com.lanchonetebairro.core.applications.services.implementation;

import br.com.lanchonetebairro.adapter.driven.infrastructure.repositories.ClienteRepository;
import br.com.lanchonetebairro.core.applications.exceptions.NotFoundException;
import br.com.lanchonetebairro.core.applications.services.ClienteService;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException(String.format("Cliente não encontrado com o cpf %s", cpf)));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> buscarPor(Example<Cliente> clienteExample) {
        return clienteRepository.findAll(clienteExample);
    }
}
