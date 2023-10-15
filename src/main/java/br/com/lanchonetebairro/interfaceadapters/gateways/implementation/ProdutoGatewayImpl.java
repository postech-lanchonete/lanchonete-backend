package br.com.lanchonetebairro.interfaceadapters.gateways.implementation;

import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.interfaceadapters.gateways.ProdutoGateway;
import br.com.lanchonetebairro.interfaceadapters.repositories.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProdutoGatewayImpl implements ProdutoGateway {
    private final ProdutoRepository produtoRepository;

    public ProdutoGatewayImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> buscarPor(Example<Produto> produtoExample) {
        return produtoRepository.findAll(produtoExample);
    }
}
