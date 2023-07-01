package br.com.lanchonetebairro.domain.services;

import br.com.lanchonetebairro.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.domain.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoService {

    ProdutoResponseDTO criar(CriacaoProdutoDTO produtoDTO);
    ProdutoResponseDTO editar(Long id, EdicaoProdutoDTO produtoDTO);
    ProdutoResponseDTO buscarPorId(Long id);
    List<ProdutoResponseDTO> buscarPorCategoria(CategoriaProduto categoria);
}
