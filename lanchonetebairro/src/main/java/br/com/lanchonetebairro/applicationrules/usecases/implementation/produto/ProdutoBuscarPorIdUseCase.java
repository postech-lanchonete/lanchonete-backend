package br.com.lanchonetebairro.applicationrules.usecases.implementation.produto;

import br.com.lanchonetebairro.applicationrules.exceptions.NotFoundException;
import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.interfaceadapters.gateways.ProdutoGateway;
import org.springframework.stereotype.Component;

@Component("produtoBuscarPorIdUseCase")
public class ProdutoBuscarPorIdUseCase implements UseCase<Long, Produto> {

    private final ProdutoGateway produtoGateway;

    public ProdutoBuscarPorIdUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto realizar(Long id) {
        return produtoGateway.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException(String.format("Produto não encontrado com o id %d", id)));
    }

}
