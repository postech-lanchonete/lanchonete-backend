package br.com.lanchonetebairro.core.applications.usecases.implementation.produto;

import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.ProdutoMapper;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoEditarUseCase implements UseCase<EdicaoProdutoDTO, ProdutoResponseDTO> {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoEditarUseCase(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseDTO realizar(EdicaoProdutoDTO produtoDTO) {
        Produto produto = produtoService.buscarPorId(produtoDTO.getId());
        produtoMapper.updateNonNullFields(produtoDTO, produto);
        produtoService.salvar(produto);
        return produtoMapper.toDtoResponse(produto);
    }

}
