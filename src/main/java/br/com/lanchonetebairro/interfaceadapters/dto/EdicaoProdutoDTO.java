package br.com.lanchonetebairro.interfaceadapters.dto;

import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@SuppressWarnings("unused")
@Schema(description = "Objeto de edição de dados para produto")
public class EdicaoProdutoDTO {

    @Schema(description = "Nome do produto")
    private String nome;
    @Schema(description = "Categoria do produto.", enumAsRef = true)
    private CategoriaProduto categoria;

    @Schema(description = "Preço do produto.")
    private BigDecimal preco;

    @Schema(description = "Descrição do produto")
    private String descricao;

    @Schema(description = "Imagem do produto em BLOB")
    private String imagem;
}
