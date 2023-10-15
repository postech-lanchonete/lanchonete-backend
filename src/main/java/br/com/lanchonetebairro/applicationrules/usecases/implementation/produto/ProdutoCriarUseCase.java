package br.com.lanchonetebairro.applicationrules.usecases.implementation.produto;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.interfaceadapters.gateways.ProdutoGateway;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component("produtoCriarUseCase")
public class ProdutoCriarUseCase implements UseCase<Produto, Produto> {

    private final ProdutoGateway produtoGateway;

    public ProdutoCriarUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto realizar(Produto produto) {
        this.procurarPorDuplicidade(produto);
        return produtoGateway.salvar(produto);
    }

    private void procurarPorDuplicidade(Produto produtoDTO) {
        Produto produtoExample = new Produto();
        produtoExample.setNome(produtoDTO.getNome());
        produtoGateway.buscarPor(Example.of(produtoExample))
                .stream()
                .findFirst()
                .ifPresent(produto -> {
                    throw new DataIntegrityViolationException("Não é permitido inserir dois produtos com o mesmo nome.");
                });
    }
}
