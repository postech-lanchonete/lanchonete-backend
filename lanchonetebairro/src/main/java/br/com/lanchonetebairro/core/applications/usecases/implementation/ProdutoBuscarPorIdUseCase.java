package br.com.lanchonetebairro.core.applications.usecases.implementation;

import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.ProdutoMapper;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import org.springframework.stereotype.Component;

@Component
public class ProdutoBuscarPorIdUseCase implements UseCase<Long, ProdutoResponseDTO> {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoBuscarPorIdUseCase(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseDTO realizar(Long id) {
        return produtoMapper.toDtoResponse(produtoService.buscarPorId(id));
    }

}
