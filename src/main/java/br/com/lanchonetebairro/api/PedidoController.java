package br.com.lanchonetebairro.api;

import br.com.lanchonetebairro.entities.Pedido;
import br.com.lanchonetebairro.enums.StatusDoPedido;
import br.com.lanchonetebairro.persistence.ClienteRepository;
import br.com.lanchonetebairro.persistence.PedidoRepository;
import br.com.lanchonetebairro.persistence.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class PedidoController {
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoController(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, PedidoRepository
            pedidoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Void> criarPedido(@RequestBody Pedido pedido) {
        pedido.setStatusDoPedido(StatusDoPedido.RECEBIDO);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/pedidos")
    public List<Pedido> obterPedidosPorStatus(@RequestParam String statusDoPedido) {
        return pedidoRepository.findByStatusDoPedido(StatusDoPedido.valueOf(statusDoPedido));
    }
}
