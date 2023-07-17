package br.com.lanchonetebairro.core.applications.usecases.implementation;

import br.com.lanchonetebairro.adapter.driven.api.notificacao.NotificacaoEmailPort;
import br.com.lanchonetebairro.adapter.driven.api.pagamento.PagamentoPort;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.PedidoMapper;
import br.com.lanchonetebairro.core.applications.services.ClienteService;
import br.com.lanchonetebairro.core.applications.services.PedidoService;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import br.com.lanchonetebairro.core.applications.usecases.PedidoUseCase;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import br.com.lanchonetebairro.core.domain.entities.Pedido;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoUseCaseImpl implements PedidoUseCase {
    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PedidoMapper pedidoMapper;
    private final NotificacaoEmailPort notificacaoEmailPort;
    private final PagamentoPort pagamentoPort;


    public PedidoUseCaseImpl(ClienteService clienteService,
                             PedidoService pedidoService,
                             ProdutoService produtoService,
                             PedidoMapper pedidoMapper, NotificacaoEmailPort notificacaoEmailPort, PagamentoPort pagamentoPort) {
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
        this.pedidoMapper = pedidoMapper;
        this.notificacaoEmailPort = notificacaoEmailPort;
        this.pagamentoPort = pagamentoPort;
    }

    @Override
    public List<PedidoResponseDTO> buscarPorStatus(StatusDoPedido status) {
        return pedidoService.buscarPorStatus(status).stream().map(pedidoMapper::toDto).toList();
    }

    @Override
    public PedidoResponseDTO criar(CriacaoPedidoDTO pedidoDTO) {
        Cliente cliente = clienteService.buscarPorCpf(pedidoDTO.getCpfCliente());
        List<Produto> produtos = buscarProdutos(pedidoDTO.getIdsProdutos());
        Pedido pedido = new Pedido(cliente, produtos);
        pagamentoPort.realizarPagamento(pedido);
        pedidoService.salvar(pedido);
        return pedidoMapper.toDto(pedido);
    }

    @Override
    public List<PedidoResponseDTO> buscarTodos() {
        return pedidoService.buscarTodos().stream().map(pedidoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PedidoResponseDTO mudarStatus(Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        pedido.mudarStatus();
        pedidoService.salvar(pedido);
        if (pedido.getStatus() == StatusDoPedido.FINALIZADO) {
            notificacaoEmailPort.notificaCliente(pedido.getCliente(), "Seu pedido está pronto. Venha buscar!");
        }
        return pedidoMapper.toDto(pedido);
    }

    private List<Produto> buscarProdutos(List<Long> idsProdutos) {
        return idsProdutos.stream().map(produtoService::buscarPorId).toList();
    }
}
