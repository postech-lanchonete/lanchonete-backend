package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.ClienteAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.core.applications.mappers.ClienteMapper;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController implements ClienteAPI {
    private final UseCase<String, Cliente> buscarPorCpfUseCase;
    private final UseCase<CriacaoClienteDTO, ClienteResponseDTO> criarUseCase;
    private final ClienteMapper clienteMapper;

    public ClienteController(UseCase<String, Cliente> buscarPorCpfUseCase,
                             UseCase<CriacaoClienteDTO, ClienteResponseDTO> criarUseCase, ClienteMapper clienteMapper) {
        this.buscarPorCpfUseCase = buscarPorCpfUseCase;
        this.criarUseCase = criarUseCase;
        this.clienteMapper = clienteMapper;
    }


    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@Valid @RequestBody CriacaoClienteDTO cliente) {
        return criarUseCase.realizar(cliente);
    }

    @Override
    @GetMapping("/{cpf}")
    public ClienteResponseDTO buscarPorCPF(@PathVariable String cpf) {
        return clienteMapper.toDto(buscarPorCpfUseCase.realizar(cpf));
    }
}
