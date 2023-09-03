package br.com.lanchonetebairro.interfaceadapters.dto;

import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    @Schema(description = "ID do produto.")
    private Long id;
    @Schema(description = "Nome do produto.")
    private String nome;

    @Schema(description = "Categoria do produto.", enumAsRef = true)
    private CategoriaProduto categoria;

    @Schema(description = "Preço do produto.")
    private BigDecimal preco;

    @Schema(description = "Descrição do produto.")
    private String descricao;

    @Schema(description = "Imagem do produto em BLOB.")
    private String imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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