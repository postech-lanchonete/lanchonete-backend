package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.PedidoAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.applications.usecases.implementation.PedidoBuscarPorStatusUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.PedidoBuscarPorTodosUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.PedidoCriarUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.PedidoMudarStatusUseCase;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class PedidoController implements PedidoAPI {
    private final PedidoBuscarPorStatusUseCase buscarPorStatusUseCase;
    private final PedidoBuscarPorTodosUseCase pedidoBuscarPorTodosUseCase;
    private final PedidoCriarUseCase criarUseCase;
    private final PedidoMudarStatusUseCase mudarStatusUseCase;

    public PedidoController(PedidoBuscarPorStatusUseCase buscarPorStatusUseCase,
                            PedidoBuscarPorTodosUseCase pedidoBuscarPorTodosUseCase, PedidoCriarUseCase criarUseCase,
                            PedidoMudarStatusUseCase mudarStatusUseCase) {
        this.buscarPorStatusUseCase = buscarPorStatusUseCase;
        this.pedidoBuscarPorTodosUseCase = pedidoBuscarPorTodosUseCase;
        this.criarUseCase = criarUseCase;
        this.mudarStatusUseCase = mudarStatusUseCase;
    }

    @GetMapping
    public List<PedidoResponseDTO> buscarTodos() {
        return pedidoBuscarPorTodosUseCase.realizar(null);
    }

    @Override
    @GetMapping("/{status}")
    public List<PedidoResponseDTO> buscarPorStatus(@PathVariable String status) {
        StatusDoPedido statusDoPedido = StatusDoPedido.encontrarEnumPorString(status);
        return buscarPorStatusUseCase.realizar(statusDoPedido);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO criar(@RequestBody CriacaoPedidoDTO pedido) {
        return criarUseCase.realizar(pedido);
    }

    @Override
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PedidoResponseDTO mudarStatus(@PathVariable @NotNull Long id) {
        return mudarStatusUseCase.realizar(id);
    }
}
