package br.com.lanchonetebairro.bdd.steps;

import br.com.lanchonetebairro.bdd.helper.RequestHelper;
import br.com.lanchonetebairro.enterpriserules.enums.CategoriaProduto;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoProdutoDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.ProdutoResponseDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

public class ProdutoStepDefinition {
    private static final String PATCH = "/v1/produtos";
    private RequestHelper<ProdutoResponseDTO> requestSingleHelper;
    private RequestHelper<ProdutoResponseDTO[]> requestListHelper;

    private CriacaoProdutoDTO produtoCriacaoDto;
    private ProdutoResponseDTO produtoEncontrado;

    @Dado("que exista um produto registrado no sistema com o nome {string}")
    public void dadoQueExista(String nome) {
        ProdutoResponseDTO[] produtos = RequestHelper
                .realizar(PATCH, HttpMethod.GET, null, ProdutoResponseDTO[].class)
                .validaStatusEqualsTo(HttpStatus.OK).getSuccessResponse().getBody();

        assert produtos != null;
        this.produtoEncontrado = Arrays.stream(produtos)
                .filter(p -> p.getNome().equals(nome))
                .findAny()
                .orElse(null);

        Assert.assertNotNull("Produto não encontrado", produtoEncontrado);
    }

    @Dado("que nao exista um produto registrado no sistema com o nome {string}")
    public void dadoQueNaoExista(String nome) {
        ProdutoResponseDTO[] produtos = RequestHelper
                .realizar(PATCH, HttpMethod.GET, null, ProdutoResponseDTO[].class)
                .validaStatusEqualsTo(HttpStatus.OK).getSuccessResponse().getBody();

        assert produtos != null;
        ProdutoResponseDTO produtoEncontrado = Arrays.stream(produtos)
                .filter(p -> p.getNome().equals(nome))
                .findAny()
                .orElse(null);

        Assert.assertNull("Produto encontrado", produtoEncontrado);
    }

    @E("o preco do produto seja diferente a {bigdecimal}")
    public void precoDiferenteA(BigDecimal preco) {
        Assert.assertNotEquals("Preco diferente", produtoEncontrado.getPreco(), preco);
    }

    @E("categoria do produto seja diferente a {string}")
    public void categoriaDiferenteA(String categoria) {
        Assert.assertNotEquals("Categoria diferente", produtoEncontrado.getCategoria(), CategoriaProduto.valueOf(categoria));
    }

    @Dado("for realizado uma busca nos produtos pela categoria {string}")
    public void forRealizadoUmaBusca(String categoria) {
        String parameters = "?categoria=" + categoria;
        this.requestListHelper = RequestHelper
                .realizar(PATCH + parameters, HttpMethod.GET, null, ProdutoResponseDTO[].class);
    }

    @Quando("for realizado uma busca nos produtos pelo id {long}")
    public void forRealizadoBuscaPorId(Long id) {
        String parameters = "/" + id;
        this.requestSingleHelper = RequestHelper
                .realizar(PATCH + parameters, HttpMethod.GET, null, ProdutoResponseDTO.class);
    }

    @Quando("for criado um produto o nome igual a {string}")
    public void quandoForCriadoUmProduto(String nome) {
        var produtoDTO = new CriacaoProdutoDTO();
        produtoDTO.setNome(nome);
        this.produtoCriacaoDto = produtoDTO;
    }

    @E("preço igual a {bigdecimal}")
    public void precoIgualA(BigDecimal preco) {
        this.produtoCriacaoDto.setPreco(preco);
    }

    @E("categoria igual a {string}")
    public void categoriaIgualA(String categoria) {
        this.produtoCriacaoDto.setCategoria(CategoriaProduto.valueOf(categoria));
    }

    @E("descricao igual a {string}")
    public void descricaoIgualA(String descricao) {
        this.produtoCriacaoDto.setDescricao(descricao);
    }

    @E("imagem igual a {string}")
    public void imagemIgualA(String imagem) {
        this.produtoCriacaoDto.setImagem(imagem);
    }

    @E("o usuário enviar uma requisição com os dados do produto")
    public void enviarRequisicao() {
        this.requestSingleHelper = RequestHelper
                .realizar(PATCH, HttpMethod.POST, this.produtoCriacaoDto, ProdutoResponseDTO.class);
    }

    @E("o usuário enviar uma requisição de edicao com os dados deste novo produto")
    public void enviarRequisicaoEdicao() {
        this.requestSingleHelper = RequestHelper
                .realizar(PATCH + "/" + this.produtoEncontrado.getId(), HttpMethod.PUT, this.produtoCriacaoDto, ProdutoResponseDTO.class);
    }


    @Entao("deve retornar um produto")
    public void deveRetornarUmObjeto() {
        Assert.assertNotNull("Resposta nao é nula", this.requestSingleHelper.getSuccessResponse().getBody());
    }

    @Entao("nao deve retornar um produto")
    public void naoDeveRetornarUmCliente() {
        Assert.assertNull("Resposta deve ser nula", this.requestSingleHelper.getSuccessResponse());
    }

    @Entao("deve retornar uma lista de produtos com {int} produtos")
    public void deveRetornarUmaLista(int tamanhoLista) {
        Assert.assertNotNull("Resposta nao é nula", this.requestListHelper.getSuccessResponse().getBody());
        Assert.assertEquals("Tamanho da lista", tamanhoLista, this.requestListHelper.getSuccessResponse().getBody().length);
    }

    @E("o status da busca pelo produto deve ser igual a {int}")
    public void oStatusDaBuscaPeloProdutoIgualA(int status) {
        if(this.requestSingleHelper.getSuccessResponse() != null) {
            Assert.assertEquals("Status should match", HttpStatusCode.valueOf(status), this.requestSingleHelper.getSuccessResponse().getStatusCode());
        } else {
            Assert.assertEquals("Status should match", HttpStatusCode.valueOf(status), this.requestSingleHelper.getErrorResponse().getStatusCode());
        }
    }

    @E("o status da busca pelos produtos deve ser igual a {int}")
    public void oStatusDaBuscaPelosProdutosIgualA(int status) {
        if(this.requestListHelper.getSuccessResponse() != null) {
            Assert.assertEquals("Status should match", HttpStatusCode.valueOf(status), this.requestListHelper.getSuccessResponse().getStatusCode());
        } else {
            Assert.assertEquals("Status should match", HttpStatusCode.valueOf(status), this.requestListHelper.getErrorResponse().getStatusCode());
        }
    }

    @E("o resultado da busca deve conter um produto com nome {string}")
    public void oStatusDaBuscaDeveTerUmProdutoComNomeIgualA(String nome) {
        ProdutoResponseDTO[] produtos = this.requestListHelper.getSuccessResponse().getBody();

        assert produtos != null;
        ProdutoResponseDTO produtoEncontrado = Arrays.stream(produtos)
                .filter(p -> p.getNome().equals(nome))
                .findAny()
                .orElse(null);

        Assert.assertNotNull("Produto não encontrado", produtoEncontrado);
    }

    @Entao("o resultado da busca deve conter o produto com nome {string}")
    public void oResultadoDaBuscaDeveConterUmProdutoComNome(String nome) {
        String nomeProdutoEncontrado = Objects.requireNonNull(this.requestSingleHelper.getSuccessResponse().getBody()).getNome();
        Assert.assertEquals("Nome do produto encontrado", nome, nomeProdutoEncontrado);
    }

    @E("o erro retornado durante a requisicao feito para o endpoint produto deve conter {string}")
    public void erroRetornadoContem(String mensagemErro) {
        Assert.assertNotNull("Resposta de erro nao é nula", this.requestSingleHelper.getErrorResponse().getMessage());
        Assert.assertNull("Resposta é nula", this.requestSingleHelper.getSuccessResponse());
        Assert.assertTrue("Mensagem de erro deve conter", Objects.requireNonNull(this.requestSingleHelper.getErrorResponse().getMessage()).contains(mensagemErro));
    }

}
