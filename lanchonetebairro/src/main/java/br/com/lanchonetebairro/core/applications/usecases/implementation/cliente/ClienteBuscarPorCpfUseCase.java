package br.com.lanchonetebairro.core.applications.usecases.implementation.cliente;

import br.com.lanchonetebairro.core.applications.services.ClienteService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteBuscarPorCpfUseCase implements UseCase<String, Cliente> {

    private final ClienteService clienteService;

    public ClienteBuscarPorCpfUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @Override
    public Cliente realizar(String cpf) {
        return clienteService.buscarPorCpf(cpf);
    }

}
