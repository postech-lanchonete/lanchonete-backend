package br.com.lanchonetebairro.core.applications.usecases;

import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoUseCase {

    ProdutoResponseDTO criar(CriacaoProdutoDTO produtoDTO);
    ProdutoResponseDTO editar(Long id, EdicaoProdutoDTO produtoDTO);
    ProdutoResponseDTO buscarPorId(Long id);
    List<ProdutoResponseDTO> buscarPorCategoria(CategoriaProduto categoria);
}
