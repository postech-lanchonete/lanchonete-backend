package br.com.lanchonetebairro.api.controllers;

import br.com.lanchonetebairro.api.PedidoAPI;
import br.com.lanchonetebairro.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.business.PedidoService;
import br.com.lanchonetebairro.business.enums.StatusDoPedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController implements PedidoAPI {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CriacaoPedidoDTO pedido) {
        CriacaoPedidoDTO pedidoSalvo = pedidoService.criar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/{statusDoPedido}")
    public List<PedidoResponseDTO> buscarPorStatus(@PathVariable String statusDoPedido) {
        return pedidoService.buscarPorStatus(StatusDoPedido.valueOf(statusDoPedido));
    }
}
