package br.com.lanchonetebairro.interfaceadapters.gateways;

import br.com.lanchonetebairro.enterpriserules.entities.Produto;

import java.util.Optional;

public interface ProdutoGateway extends Gateway<Produto> {
    Optional<Produto> buscarPorId(Long id);

}
