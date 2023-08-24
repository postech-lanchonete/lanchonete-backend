package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.ClienteAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.core.applications.usecases.ClienteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController implements ClienteAPI {
    private final ClienteUseCase clienteUseCase;

    public ClienteController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@Valid @RequestBody CriacaoClienteDTO cliente) {
        return clienteUseCase.criar(cliente);
    }

    @Override
    @GetMapping("/{cpf}")
    public ClienteResponseDTO buscarPorCPF(@PathVariable String cpf) {
        return clienteUseCase.buscarPorCPF(cpf);
    }
}
