package br.com.lanchonetebairro.api.controllers;

import br.com.lanchonetebairro.api.ClienteAPI;
import br.com.lanchonetebairro.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.domain.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController implements ClienteAPI {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@RequestBody CriacaoClienteDTO cliente) {
        return clienteService.criarCliente(cliente);
    }

    @Override
    @GetMapping("/{cpf}")
    public ClienteResponseDTO buscarPorCPF(@PathVariable String cpf) {
        return clienteService.buscarPorCPF(cpf);
    }
}
