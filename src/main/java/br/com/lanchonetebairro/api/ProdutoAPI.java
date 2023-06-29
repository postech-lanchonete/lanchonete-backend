package br.com.lanchonetebairro.api;

import br.com.lanchonetebairro.api.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.api.dto.ProdutoResponseDTO;
import br.com.lanchonetebairro.infraestructure.entities.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Produtos", description = "Todas as operações referentes aos produtos")
public interface ProdutoAPI {
    @Operation(summary = "Obter produto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ProdutoResponseDTO obterProdutoPorId(@Parameter(description = "ID do produto", example = "1", required = true) Long id);

    @Operation(summary = "Criar um novo produto")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    ResponseEntity<Void> criarProduto(@Parameter(description = "Objeto Produto", required = true) CriacaoProdutoDTO produto);

    @Operation(summary = "Editar um produto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    ResponseEntity<Void> editarProduto(
            @Parameter(description = "ID do produto", example = "1", required = true) Long id,
            @Parameter(description = "Objeto Produto", required = true) Produto produto);
}
