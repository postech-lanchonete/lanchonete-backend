package br.com.lanchonetebairro.interfaceadapters.controllers;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import br.com.lanchonetebairro.frameworksdrivers.web.PedidoAPI;
import br.com.lanchonetebairro.interfaceadapters.adapter.ClienteAdapter;
import br.com.lanchonetebairro.interfaceadapters.adapter.PedidoAdapter;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.PedidoResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class PedidoController implements PedidoAPI {
    private final UseCase<Pedido, List<Pedido>> pedidoBuscarTodosUseCase;
    private final UseCase<Pedido, Pedido> criarUseCase;
    private final UseCase<Long, Pedido> mudarStatusUseCase;
    private final UseCase<Cliente, Cliente> clienteBuscarPorCpfUseCase;
    private final UseCase<Long, Produto> produtoBuscarPorIdUseCase;

    private final PedidoAdapter pedidoAdapter;
    private final ClienteAdapter clienteAdapter;

    public PedidoController(@Qualifier("pedidoBuscarTodosUseCase") UseCase<Pedido, List<Pedido>> pedidoBuscarTodosUseCase,
                            @Qualifier("pedidoCriarUseCase") UseCase<Pedido, Pedido> criarUseCase,
                            @Qualifier("clienteBuscarPorCpfUseCase") UseCase<Cliente, Cliente> clienteBuscarPorCpfUseCase,
                            @Qualifier("pedidoMudarStatusUseCase") UseCase<Long, Pedido> mudarStatusUseCase,
                            @Qualifier("produtoBuscarPorIdUseCase") UseCase<Long, Produto> produtoBuscarPorIdUseCase,
                            PedidoAdapter pedidoAdapter,
                            ClienteAdapter clienteAdapter) {
        this.pedidoBuscarTodosUseCase = pedidoBuscarTodosUseCase;
        this.criarUseCase = criarUseCase;
        this.mudarStatusUseCase = mudarStatusUseCase;
        this.clienteBuscarPorCpfUseCase = clienteBuscarPorCpfUseCase;
        this.produtoBuscarPorIdUseCase = produtoBuscarPorIdUseCase;
        this.pedidoAdapter = pedidoAdapter;
        this.clienteAdapter = clienteAdapter;
    }

    @Override
    @GetMapping
    public List<PedidoResponseDTO> buscarTodos(String status) {
        Pedido pedido = new Pedido();
        if(StringUtils.isNotEmpty(status)) {
            StatusDoPedido statusDoPedido = StatusDoPedido.encontrarEnumPorString(status);
            pedido = pedidoAdapter.toEntity(statusDoPedido);
        }

        return pedidoBuscarTodosUseCase.realizar(pedido)
                .stream()
                .map(pedidoAdapter::toDto)
                .toList();
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponseDTO criar(@RequestBody CriacaoPedidoDTO criacaoPedidoDTO) {
        Cliente clientePedido = clienteAdapter.toEntity(criacaoPedidoDTO.getCpfCliente());
        Cliente cliente = clienteBuscarPorCpfUseCase.realizar(clientePedido);
        List<Produto> produtos = criacaoPedidoDTO.getIdsProdutos().stream().map(produtoBuscarPorIdUseCase::realizar).toList();
        Pedido pedido = new Pedido(cliente, produtos);
        Pedido pedidoCrido = criarUseCase.realizar(pedido);
        return pedidoAdapter.toDto(pedidoCrido);
    }

    @Override
    @PutMapping("/{id}/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PedidoResponseDTO mudarStatus(@PathVariable Long id) {
        Pedido pedido = mudarStatusUseCase.realizar(id);
        return pedidoAdapter.toDto(pedido);
    }
}
