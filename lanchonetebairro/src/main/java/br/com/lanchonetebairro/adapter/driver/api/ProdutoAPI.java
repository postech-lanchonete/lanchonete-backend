package br.com.lanchonetebairro.adapter.driver.api;

import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.EdicaoProdutoDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.ProdutoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/produtos")
@Tag(name = "Produtos", description = "Todas as operações referentes aos produtos")
public interface ProdutoAPI {
    @Operation(summary = "Obter produto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ProdutoResponseDTO buscarPorId(@Parameter(description = "ID do produto", example = "1", required = true) Long id);

    @Operation(summary = "Obter produto por Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado ou lista vazia")
    })
    List<ProdutoResponseDTO> buscarPorCategoria(@Parameter(description = "Categoria do produto", example = "BEBIDA", required = true) String categoria);

    @Operation(summary = "Criar um novo produto")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    ProdutoResponseDTO criar(@Valid @RequestBody @Parameter(description = "Objeto Produto", required = true) CriacaoProdutoDTO produto);

    @Operation(summary = "Editar um produto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ProdutoResponseDTO editar(
            @Parameter(description = "ID do produto", example = "1", required = true) Long id,
            @Parameter(description = "Objeto Produto", required = true) EdicaoProdutoDTO produto);
}
