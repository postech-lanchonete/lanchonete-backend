package br.com.lanchonetebairro.domain.services.implementation;

import br.com.lanchonetebairro.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.domain.enums.StatusDoPedido;
import br.com.lanchonetebairro.domain.exceptions.NegocioException;
import br.com.lanchonetebairro.domain.exceptions.NotFoundException;
import br.com.lanchonetebairro.domain.mappers.PedidoMapper;
import br.com.lanchonetebairro.domain.services.PedidoService;
import br.com.lanchonetebairro.infraestructure.entities.Cliente;
import br.com.lanchonetebairro.infraestructure.entities.Pedido;
import br.com.lanchonetebairro.infraestructure.entities.Produto;
import br.com.lanchonetebairro.infraestructure.repositories.ClienteRepository;
import br.com.lanchonetebairro.infraestructure.repositories.PedidoRepository;
import br.com.lanchonetebairro.infraestructure.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoMapper pedidoMapper;


    public PedidoServiceImpl(ClienteRepository clienteRepository,
                             PedidoRepository pedidoRepository,
                             ProdutoRepository produtoRepository,
                             PedidoMapper pedidoMapper) {
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public List<PedidoResponseDTO> buscarPorStatus(StatusDoPedido status) {
        return pedidoRepository.findByStatus(status).stream().map(pedidoMapper::toDto).toList();
    }

    @Override
    public PedidoResponseDTO criar(CriacaoPedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findByCpf(pedidoDTO.getCpfCliente())
                .orElseThrow(() -> new NegocioException(String.format("Cliente não encontrado com o cpf %s", pedidoDTO.getCpfCliente())));
        List<Produto> produtos = buscarProdutos(pedidoDTO.getIdsProdutos());
        Pedido pedido = new Pedido(cliente, produtos);
        pedidoRepository.save(pedido);
        return pedidoMapper.toDto(pedido);
    }

    @Override
    public List<PedidoResponseDTO> buscarTodos() {
        return pedidoRepository.findAll().stream().map(pedidoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO mudarStatus(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Pedido não encontrado com o id %d", id)));
        pedido.mudarStatus();
        pedidoRepository.save(pedido);
        return pedidoMapper.toDto(pedido);
    }

    private List<Produto> buscarProdutos(List<Long> idsProdutos) {
        return idsProdutos.stream()
                .map(produtoRepository::findById)
                .map(produto -> produto.orElse(null))
                .filter(Objects::nonNull).toList();
    }
}
