package br.com.lanchonetebairro.adapter.driven.infraestructure.repositories;

import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoria(CategoriaProduto categoria);
}