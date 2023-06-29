package br.com.lanchonetebairro.api.controllers;

import br.com.lanchonetebairro.api.ClienteAPI;
import br.com.lanchonetebairro.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.business.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> criar(@RequestBody CriacaoClienteDTO cliente) {
        ClienteResponseDTO clienteSalvo = clienteService.criarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/{cpf}")
    public ClienteResponseDTO buscarPorCPF(@PathVariable String cpf) {
        return clienteService.buscarPorCPF(cpf);
    }
}
