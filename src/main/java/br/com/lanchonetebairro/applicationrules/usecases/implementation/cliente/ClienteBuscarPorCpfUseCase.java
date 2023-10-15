package br.com.lanchonetebairro.applicationrules.usecases.implementation.cliente;

import br.com.lanchonetebairro.applicationrules.exceptions.NotFoundException;
import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.interfaceadapters.gateways.ClienteGateway;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component("clienteBuscarPorCpfUseCase")
public class ClienteBuscarPorCpfUseCase implements UseCase<Cliente, Cliente> {

    private final ClienteGateway clienteGateway;

    public ClienteBuscarPorCpfUseCase(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }
    @Override
    public Cliente realizar(Cliente cpf) {
        return clienteGateway.buscarPor(Example.of(cpf))
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

}
