package br.com.lanchonetebairro.adapter.driver.api.dto;

import br.com.lanchonetebairro.core.domain.enums.CategoriaProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

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


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}