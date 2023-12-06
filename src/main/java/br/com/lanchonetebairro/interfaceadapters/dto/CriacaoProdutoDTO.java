package br.com.lanchonetebairro.interfaceadapters.dto;

import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "Objeto de transferência de dados para produto")
public class CriacaoProdutoDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    @Schema(description = "Nome do produto")
    private String nome;
    @NotNull(message = "Categoria é obrigatória")
    @Schema(description = "Categoria do produto.", enumAsRef = true)
    private CategoriaProduto categoria;

    @NotNull(message = "Preço do produto é obrigatório")
    @Schema(description = "Preço do produto.")
    private BigDecimal preco;

    @Schema(description = "Descrição do produto")
    private String descricao;

    @Schema(description = "Imagem do produto em BLOB")
    private String imagem;
}
