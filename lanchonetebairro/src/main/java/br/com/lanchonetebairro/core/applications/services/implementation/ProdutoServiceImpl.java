package br.com.lanchonetebairro.core.applications.services.implementation;

import br.com.lanchonetebairro.adapter.driven.infrastructure.repositories.ProdutoRepository;
import br.com.lanchonetebairro.core.applications.exceptions.NotFoundException;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Produto não encontrado com o id %d", id)));
    }

    @Override
    public List<Produto> buscarPorCategoria(CategoriaProduto categoria) {
        Produto produtoExample = new Produto();
        produtoExample.setCategoria(categoria);
        return produtoRepository.findAll(Example.of(produtoExample));
    }

    @Override
    public List<Produto> buscarPor(Example<Produto> produtoExample) {
        return produtoRepository.findAll(produtoExample);
    }
}
