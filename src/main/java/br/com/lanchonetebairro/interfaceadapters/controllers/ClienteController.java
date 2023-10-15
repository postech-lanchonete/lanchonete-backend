package br.com.lanchonetebairro.interfaceadapters.controllers;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.frameworksdrivers.web.ClienteAPI;
import br.com.lanchonetebairro.interfaceadapters.adapter.ClienteAdapter;
import br.com.lanchonetebairro.interfaceadapters.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoClienteDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController implements ClienteAPI {
    private final UseCase<Cliente, Cliente> buscarPorCpfUseCase;
    private final UseCase<Cliente, Cliente> criarUseCase;
    private final ClienteAdapter clienteAdapter;

    public ClienteController(@Qualifier("clienteBuscarPorCpfUseCase") UseCase<Cliente, Cliente> buscarPorCpfUseCase,
                             @Qualifier("clienteCriarUseCase") UseCase<Cliente, Cliente> criarUseCase,
                             ClienteAdapter clienteAdapter) {
        this.buscarPorCpfUseCase = buscarPorCpfUseCase;
        this.criarUseCase = criarUseCase;
        this.clienteAdapter = clienteAdapter;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@Valid @RequestBody CriacaoClienteDTO criacaoClienteDTO) {
        Cliente cliente = clienteAdapter.toEntity(criacaoClienteDTO);
        Cliente clienteCriado = criarUseCase.realizar(cliente);
        return clienteAdapter.toDto(clienteCriado);
    }

    @Override
    @GetMapping("/{cpf}")
    public ClienteResponseDTO buscarPorCPF(@PathVariable String cpf) {
        Cliente cliente = clienteAdapter.toEntity(cpf);
        Cliente clienteEncontrado = buscarPorCpfUseCase.realizar(cliente);
        return clienteAdapter.toDto(clienteEncontrado);
    }
}
