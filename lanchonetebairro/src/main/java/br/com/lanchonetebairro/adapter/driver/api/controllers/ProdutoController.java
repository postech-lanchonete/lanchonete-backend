package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.ProdutoAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.applications.usecases.implementation.produto.ProdutoBuscarPorCategoriaUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.produto.ProdutoBuscarPorIdUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.produto.ProdutoCriarUseCase;
import br.com.lanchonetebairro.core.applications.usecases.implementation.produto.ProdutoEditarUseCase;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
public class ProdutoController implements ProdutoAPI {

    private final ProdutoBuscarPorIdUseCase produtoBuscarPorIdUseCase;
    private final ProdutoEditarUseCase produtoEditarUseCase;
    private final ProdutoCriarUseCase produtoCriarUseCase;
    private final ProdutoBuscarPorCategoriaUseCase produtoBuscarPorCategoriaUseCase;

    public ProdutoController(ProdutoBuscarPorIdUseCase produtoBuscarPorIdUseCase,
                             ProdutoEditarUseCase produtoEditarUseCase,
                             ProdutoCriarUseCase produtoCriarUseCase,
                             ProdutoBuscarPorCategoriaUseCase produtoBuscarPorCategoriaUseCase) {
        this.produtoBuscarPorIdUseCase = produtoBuscarPorIdUseCase;
        this.produtoEditarUseCase = produtoEditarUseCase;
        this.produtoCriarUseCase = produtoCriarUseCase;
        this.produtoBuscarPorCategoriaUseCase = produtoBuscarPorCategoriaUseCase;
    }


    @Override
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoBuscarPorIdUseCase.realizar(id);
    }

    @Override
    @GetMapping("/categoria/{categoria}")
    public List<ProdutoResponseDTO> buscarPorCategoria(@PathVariable String categoria) {
        return produtoBuscarPorCategoriaUseCase.realizar(CategoriaProduto.encontrarEnumPorString(categoria));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(CriacaoProdutoDTO produto) {
        return produtoCriarUseCase.realizar(produto);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProdutoResponseDTO editar(@PathVariable Long id, @RequestBody EdicaoProdutoDTO produto) {
        produto.setId(id);
        return produtoEditarUseCase.realizar(produto);
    }
}