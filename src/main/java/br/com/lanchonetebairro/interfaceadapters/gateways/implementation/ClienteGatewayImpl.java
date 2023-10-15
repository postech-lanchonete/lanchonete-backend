package br.com.lanchonetebairro.interfaceadapters.gateways.implementation;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.interfaceadapters.gateways.ClienteGateway;
import br.com.lanchonetebairro.interfaceadapters.repositories.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteGatewayImpl implements ClienteGateway {
    private final ClienteRepository clienteRepository;

    public ClienteGatewayImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
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
