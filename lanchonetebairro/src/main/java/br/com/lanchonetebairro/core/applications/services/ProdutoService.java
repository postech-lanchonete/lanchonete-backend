package br.com.lanchonetebairro.core.applications.services;

import br.com.lanchonetebairro.core.domain.entities.Produto;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import org.springframework.data.domain.Example;

import java.util.List;

public interface ProdutoService {
    Produto salvar(Produto produto);
    Produto buscarPorId(Long id);
    List<Produto> buscarPorCategoria(CategoriaProduto categoria); //TODO Remover
    List<Produto> buscarPor(Example<Produto> produtoExample);

}
