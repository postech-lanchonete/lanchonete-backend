package br.com.lanchonetebairro.infraestructure.repositories;

import br.com.lanchonetebairro.domain.enums.CategoriaProduto;
import br.com.lanchonetebairro.infraestructure.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoria(CategoriaProduto categoria);
}