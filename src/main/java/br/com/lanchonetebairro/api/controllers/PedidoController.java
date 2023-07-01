package br.com.lanchonetebairro.api.controllers;

import br.com.lanchonetebairro.api.PedidoAPI;
import br.com.lanchonetebairro.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.domain.enums.StatusDoPedido;
import br.com.lanchonetebairro.domain.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController implements PedidoAPI {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoResponseDTO> buscarTodos() {
        return pedidoService.buscarTodos();
    }

    @Override
    @GetMapping("/{statusDoPedido}")
    public List<PedidoResponseDTO> buscarPorStatus(@PathVariable String statusDoPedido) {
        return pedidoService.buscarPorStatus(StatusDoPedido.valueOf(statusDoPedido));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CriacaoPedidoDTO criar(@RequestBody CriacaoPedidoDTO pedido) {
        return pedidoService.criar(pedido);
    }
}
