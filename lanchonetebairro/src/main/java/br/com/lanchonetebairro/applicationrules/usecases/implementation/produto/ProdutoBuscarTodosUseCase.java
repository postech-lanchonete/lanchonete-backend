package br.com.lanchonetebairro.applicationrules.usecases.implementation.produto;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.interfaceadapters.gateways.ProdutoGateway;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("produtoBuscarTodosUseCase")
public class ProdutoBuscarTodosUseCase implements UseCase<Produto, List<Produto>> {

    private final ProdutoGateway produtoGateway;

    public ProdutoBuscarTodosUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public List<Produto> realizar(Produto produto) {
        return produtoGateway.buscarPor(Example.of(produto));
    }

}
