package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.ProdutoAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import br.com.lanchonetebairro.core.applications.usecases.ProdutoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProdutoController implements ProdutoAPI {

    private final ProdutoUseCase produtoUseCase;

    public ProdutoController(ProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @Override
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoUseCase.buscarPorId(id);
    }

    @Override
    @GetMapping("/categoria/{categoria}")
    public List<ProdutoResponseDTO> buscarPorCategoria(@PathVariable String categoria) {
        return produtoUseCase.buscarPorCategoria(CategoriaProduto.encontrarEnumPorString(categoria));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(CriacaoProdutoDTO produto) {
        return produtoUseCase.criar(produto);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProdutoResponseDTO editar(@PathVariable Long id, @RequestBody EdicaoProdutoDTO produto) {
        return produtoUseCase.editar(id, produto);
    }
}