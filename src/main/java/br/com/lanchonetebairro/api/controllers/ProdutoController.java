package br.com.lanchonetebairro.api.controllers;

import br.com.lanchonetebairro.api.ProdutoAPI;
import br.com.lanchonetebairro.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.infraestructure.entities.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController implements ProdutoAPI {

    @Override
    @GetMapping("/{id}")
    public ProdutoResponseDTO obterProdutoPorId(@PathVariable Long id) {

        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody CriacaoProdutoDTO produto) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> editarProduto(
            @PathVariable Long id,
            @RequestBody Produto produto) {
        return ResponseEntity.ok().build();
    }
}