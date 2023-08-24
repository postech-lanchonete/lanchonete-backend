package br.com.lanchonetebairro.adapter.driven.infrastructure.repositories;

import br.com.lanchonetebairro.core.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}