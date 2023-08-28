package br.com.lanchonetebairro.core.applications.usecases.implementation.produto;

import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.applications.mappers.ProdutoMapper;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.entities.Produto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class ProdutoCriarUseCase implements UseCase<CriacaoProdutoDTO, ProdutoResponseDTO> {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoCriarUseCase(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public ProdutoResponseDTO realizar(CriacaoProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        try {
            procurarPorDuplicidade(produtoDTO);
            produtoService.salvar(produto);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é permitido inserir dois produtos com o mesmo nome.", e);
        }

        return produtoMapper.toDtoResponse(produto);
    }

    private void procurarPorDuplicidade(CriacaoProdutoDTO produtoDTO) {
        Produto produtoExample = new Produto();
        produtoExample.setNome(produtoDTO.getNome());
        produtoService.buscarPor(Example.of(produtoExample))
                .stream()
                .findFirst()
                .ifPresent(produto -> {
                    throw new DataIntegrityViolationException("Não é permitido inserir dois produtos com o mesmo nome.");
                });
    }
}
