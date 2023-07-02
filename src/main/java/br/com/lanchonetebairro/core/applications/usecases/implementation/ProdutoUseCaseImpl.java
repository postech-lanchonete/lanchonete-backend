package br.com.lanchonetebairro.core.applications.usecases.implementation;

import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.ProdutoMapper;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import br.com.lanchonetebairro.core.applications.usecases.ProdutoUseCase;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoUseCaseImpl(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseDTO criar(CriacaoProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        produtoService.salvar(produto);
        return produtoMapper.toDtoResponse(produto);
    }

    @Override
    public ProdutoResponseDTO editar(Long id, EdicaoProdutoDTO produtoDTO) {
        Produto produto = produtoService.buscarPorId(id);
        produtoMapper.updateNonNullFields(produtoDTO, produto);
        produtoService.salvar(produto);
        return produtoMapper.toDtoResponse(produto);
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        return produtoMapper.toDtoResponse(produtoService.buscarPorId(id));
    }

    @Override
    public List<ProdutoResponseDTO> buscarPorCategoria(CategoriaProduto categoria) {
        return produtoService.buscarPorCategoria(categoria)
                .stream()
                .map(produtoMapper::toDtoResponse)
                .collect(Collectors.toList());
    }
}
