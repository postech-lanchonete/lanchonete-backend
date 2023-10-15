package br.com.lanchonetebairro.interfaceadapters.repositories;

import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}