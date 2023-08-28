package br.com.lanchonetebairro.core.applications.usecases.implementation.pedido;

import br.com.lanchonetebairro.adapter.driven.api.pagamento.PagamentoPort;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoPedidoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.PedidoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.PedidoMapper;
import br.com.lanchonetebairro.core.applications.mappers.ProdutoMapper;
import br.com.lanchonetebairro.core.applications.services.PedidoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.produto.ProdutoBuscarPorIdUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.cliente.ClienteBuscarPorCpfUseCase;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import br.com.lanchonetebairro.core.domain.entities.Pedido;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoCriarUseCase implements UseCase<CriacaoPedidoDTO, PedidoResponseDTO> {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;
    private final ProdutoMapper produtoMapper;
    private final ProdutoBuscarPorIdUseCase produtoBuscarPorIdUseCase;
    private final ClienteBuscarPorCpfUseCase clienteBuscarPorCpfUseCase;
    private final PagamentoPort pagamentoPort;

    public PedidoCriarUseCase(PedidoService pedidoService, PedidoMapper pedidoMapper,
                              ProdutoMapper produtoMapper, ProdutoBuscarPorIdUseCase produtoBuscarPorIdUseCase,
                              ClienteBuscarPorCpfUseCase clienteBuscarPorCpfUseCase, PagamentoPort pagamentoPort) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
        this.produtoMapper = produtoMapper;
        this.produtoBuscarPorIdUseCase = produtoBuscarPorIdUseCase;
        this.clienteBuscarPorCpfUseCase = clienteBuscarPorCpfUseCase;
        this.pagamentoPort = pagamentoPort;
    }


    @Override
    public PedidoResponseDTO realizar(CriacaoPedidoDTO pedidoDTO) {
        Cliente cliente = clienteBuscarPorCpfUseCase.realizar(pedidoDTO.getCpfCliente());
        List<Produto> produtos = buscarProdutos(pedidoDTO.getIdsProdutos());
        Pedido pedido = new Pedido(cliente, produtos);
        pagamentoPort.realizarPagamento(pedido);
        pedidoService.salvar(pedido);
        return pedidoMapper.toDto(pedido);
    }

    private List<Produto> buscarProdutos(List<Long> idsProdutos) {
        return idsProdutos.stream()
                .map(produtoBuscarPorIdUseCase::realizar)
                .map(produtoMapper::toEntity)
                .toList();
    }

}
