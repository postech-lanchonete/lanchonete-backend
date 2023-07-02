package br.com.lanchonetebairro.core.applications.services;

import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoService {

    ProdutoResponseDTO criar(CriacaoProdutoDTO produtoDTO);
    ProdutoResponseDTO editar(Long id, EdicaoProdutoDTO produtoDTO);
    ProdutoResponseDTO buscarPorId(Long id);
    List<ProdutoResponseDTO> buscarPorCategoria(CategoriaProduto categoria);
}
