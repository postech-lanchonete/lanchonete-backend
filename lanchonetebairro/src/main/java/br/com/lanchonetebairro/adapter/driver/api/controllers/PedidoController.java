package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.PedidoAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.pedido.PedidoBuscarPorStatusUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.pedido.PedidoBuscarPorTodosUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.pedido.PedidoCriarUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.pedido.PedidoMudarStatusUseCase;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class PedidoController implements PedidoAPI {
    private final UseCase<StatusDoPedido, List<PedidoResponseDTO>> buscarPorStatusUseCase;
    private final UseCase.NoInputUseCase<List<PedidoResponseDTO>> pedidoBuscarPorTodosUseCase;
    private final UseCase<CriacaoPedidoDTO, PedidoResponseDTO> criarUseCase;
    private final UseCase<Long, PedidoResponseDTO> mudarStatusUseCase;

    public PedidoController(PedidoBuscarPorStatusUseCase buscarPorStatusUseCase,
                            PedidoBuscarPorTodosUseCase pedidoBuscarPorTodosUseCase, PedidoCriarUseCase criarUseCase,
                            PedidoMudarStatusUseCase mudarStatusUseCase) {
        this.buscarPorStatusUseCase = buscarPorStatusUseCase;
        this.pedidoBuscarPorTodosUseCase = pedidoBuscarPorTodosUseCase;
        this.criarUseCase = criarUseCase;
        this.mudarStatusUseCase = mudarStatusUseCase;
    }

    @Override
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
    public PedidoResponseDTO mudarStatus(@PathVariable Long id) {
        return mudarStatusUseCase.realizar(id);
    }
}
