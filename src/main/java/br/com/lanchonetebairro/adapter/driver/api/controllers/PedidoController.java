package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.PedidoAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import br.com.lanchonetebairro.core.applications.usecases.PedidoUseCase;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController implements PedidoAPI {
    private final PedidoUseCase pedidoUseCase;

    public PedidoController(PedidoUseCase pedidoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
    }

    @GetMapping
    public List<PedidoResponseDTO> buscarTodos() {
        return pedidoUseCase.buscarTodos();
    }

    @Override
    @GetMapping("/{status}")
    public List<PedidoResponseDTO> buscarPorStatus(@PathVariable String status) {
        StatusDoPedido statusDoPedido = StatusDoPedido.encontrarEnumPorString(status);
        return pedidoUseCase.buscarPorStatus(statusDoPedido);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO criar(@RequestBody CriacaoPedidoDTO pedido) {
        return pedidoUseCase.criar(pedido);
    }

    @Override
    @PatchMapping("/{id}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PedidoResponseDTO mudarStatus(@PathVariable @NotNull Long id) {
        return pedidoUseCase.mudarStatus(id);
    }
}
