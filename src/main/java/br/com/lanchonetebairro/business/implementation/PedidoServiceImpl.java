package br.com.lanchonetebairro.business.implementation;

import br.com.lanchonetebairro.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.business.PedidoService;
import br.com.lanchonetebairro.business.enums.StatusDoPedido;
import br.com.lanchonetebairro.infraestructure.entities.Cliente;
import br.com.lanchonetebairro.infraestructure.entities.Pedido;
import br.com.lanchonetebairro.infraestructure.entities.Produto;
import br.com.lanchonetebairro.infraestructure.repositories.ClienteRepository;
import br.com.lanchonetebairro.infraestructure.repositories.PedidoRepository;
import br.com.lanchonetebairro.infraestructure.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PedidoServiceImpl implements PedidoService {


    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;


    public PedidoServiceImpl(ClienteRepository clienteRepository,
                             PedidoRepository pedidoRepository,
                             ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<PedidoResponseDTO> buscarPorStatus(StatusDoPedido status) {
        return null;
    }

    @Override
    public CriacaoPedidoDTO criar(CriacaoPedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findByCpf(pedidoDTO.getCpfCliente()).orElseThrow(RuntimeException::new);
        List<Produto> produtos = buscarProdutos(pedidoDTO.getIdsProdutos());
        Pedido pedido = new Pedido(cliente, produtos);
        pedidoRepository.save(pedido);
        return null;
    }

    private List<Produto> buscarProdutos(List<Long> idsProdutos) {
        return idsProdutos.stream()
                .map(produtoRepository::findById)
                .map(produto -> produto.orElse(null))
                .filter(Objects::nonNull).toList();
    }
}
