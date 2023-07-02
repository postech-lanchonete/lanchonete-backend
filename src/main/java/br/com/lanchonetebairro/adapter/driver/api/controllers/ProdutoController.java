package br.com.lanchonetebairro.adapter.driver.api.controllers;

import br.com.lanchonetebairro.adapter.driver.api.ProdutoAPI;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import br.com.lanchonetebairro.core.applications.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProdutoController implements ProdutoAPI {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @Override
    @GetMapping("/categoria/{categoria}")
    public List<ProdutoResponseDTO> buscarPorCategoria(@PathVariable String categoria) {
        return produtoService.buscarPorCategoria(CategoriaProduto.encontrarEnumPorString(categoria));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO criar(CriacaoProdutoDTO produto) {
        return produtoService.criar(produto);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProdutoResponseDTO editar(@PathVariable Long id, @RequestBody EdicaoProdutoDTO produto) {
        return produtoService.editar(id, produto);
    }
}