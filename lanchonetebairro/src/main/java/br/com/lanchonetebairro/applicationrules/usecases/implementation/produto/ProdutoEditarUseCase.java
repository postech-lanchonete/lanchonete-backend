package br.com.lanchonetebairro.applicationrules.usecases.implementation.produto;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.interfaceadapters.gateways.ProdutoGateway;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("produtoEditarUseCase")
public class ProdutoEditarUseCase implements UseCase<Produto, Produto> {

    private final ProdutoGateway produtoGateway;
    private final UseCase<Long, Produto> buscarPorIdUseCase;

    public ProdutoEditarUseCase(ProdutoGateway produtoGateway,
                                @Qualifier("produtoBuscarPorIdUseCase") UseCase<Long, Produto> buscarPorIdUseCase) {
        this.produtoGateway = produtoGateway;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
    }

    @Override
    public Produto realizar(Produto produtoEdicao) {
        Produto produto = buscarPorIdUseCase.realizar(produtoEdicao.getId());
        this.updateNonNullFields(produtoEdicao, produto);
        return produtoGateway.salvar(produto);
    }

    private void updateNonNullFields(Produto dto, @MappingTarget Produto entity) {
        if (dto.getNome() != null) {
            entity.setNome(dto.getNome());
        }
        if (dto.getCategoria() != null) {
            entity.setCategoria(dto.getCategoria());
        }
        if (dto.getPreco() != null) {
            entity.setPreco(dto.getPreco());
        }
        if (dto.getDescricao() != null) {
            entity.setDescricao(dto.getDescricao());
        }
        if (dto.getImagem() != null) {
            entity.setImagem(dto.getImagem());
        }
    }

}
