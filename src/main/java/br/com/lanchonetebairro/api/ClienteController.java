package br.com.lanchonetebairro.api;

import br.com.lanchonetebairro.entities.Cliente;
import br.com.lanchonetebairro.persistence.ClienteRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    public ResponseEntity<Void> criarCliente(@RequestBody Cliente cliente) {
        // Lógica para salvar o cliente no banco de dados
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> obterClientePorCpf(@PathVariable String cpf) {
        // Lógica para buscar o cliente pelo CPF
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if (cliente.isPresent()) {
            cliente.get().setSenha(null); // Para não mostrar a senha na resposta
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
