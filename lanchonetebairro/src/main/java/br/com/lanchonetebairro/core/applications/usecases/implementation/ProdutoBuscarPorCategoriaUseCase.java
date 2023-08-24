package br.com.lanchonetebairro.core.applications.usecases.implementation;

import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.ProdutoMapper;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoBuscarPorCategoriaUseCase implements UseCase<CategoriaProduto, List<ProdutoResponseDTO>> {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoBuscarPorCategoriaUseCase(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public List<ProdutoResponseDTO> realizar(CategoriaProduto categoria) {
        return produtoService.buscarPorCategoria(categoria)
                .stream()
                .map(produtoMapper::toDtoResponse)
                .collect(Collectors.toList());
    }

}
