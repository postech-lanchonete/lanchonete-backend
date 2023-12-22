package br.com.lanchonetebairro.bdd.steps;

import br.com.lanchonetebairro.bdd.helper.RequestHelper;
import br.com.lanchonetebairro.interfaceadapters.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.interfaceadapters.dto.CriacaoClienteDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Objects;

public class ClienteStepDefinition {

    private RequestHelper<ClienteResponseDTO> requestHelper;

    @Dado("que exista um cliente registrado no sistema com o CPF {string}")
    public void queExistaUmClienteRegistradoNoSistemaComOCPF(String cpf) {
        this.requestHelper = RequestHelper
                .realizar("/v1/clientes/" + cpf, HttpMethod.GET, null, ClienteResponseDTO.class);
    }

    @Quando("for realizado uma busca nos clientes pelo CPF {string}")
    public void forRealizadoUmaBuscaNosClientesPeloCPF(String cpf) {
        this.requestHelper = RequestHelper
                .realizar("/v1/clientes/" + cpf, HttpMethod.GET, null, ClienteResponseDTO.class);
    }

    @Quando("for requisitado a criacao de um cliente com o nome igual a {string}, sobrenome igual a {string} e cpf igual a {string} e email {string}")
    public void forRequisitadoACriacaoDeUmClienteComONomeIgualASobrenomeIgualAECpfIgualAEEmail(String nome, String sobrenome, String cpf, String email) {
        var dto = criarClienteDto(nome, sobrenome, cpf, email);
        this.requestHelper = RequestHelper
                .realizar("/v1/clientes", HttpMethod.POST, dto, ClienteResponseDTO.class);
    }

    @Entao("deve retornar um cliente")
    public void deveRetornarUmCliente() {
        Assert.assertNotNull("Resposta nao é nula", this.requestHelper.getSuccessResponse().getBody());
    }

    @E("o status da busca pelo cliente deve ser igual a {int}")
    public void oStatusDaBuscaPeloClienteDeveSerIgualA(int status) {
        if(this.requestHelper.getSuccessResponse() != null) {
            Assert.assertEquals("Status should match", HttpStatusCode.valueOf(status), this.requestHelper.getSuccessResponse().getStatusCode());
        } else {
            Assert.assertEquals("Status should match", HttpStatusCode.valueOf(status), this.requestHelper.getErrorResponse().getStatusCode());
        }
    }

    @Dado("que nao exista um cliente registrado no sistema com o CPF {string}")
    public void queNaoExistaUmClienteRegistradoNoSistemaComOCPF(String cpf) {
        RequestHelper
                .realizar("/v1/clientes/" + cpf, HttpMethod.GET, null, ClienteResponseDTO.class)
                .validaStatusEqualsTo(HttpStatus.NOT_FOUND);
    }

    @Entao("nao deve retornar um cliente")
    public void naoDeveRetornarUmCliente() {
        Assert.assertNull("Resposta deve ser nula", this.requestHelper.getSuccessResponse());
    }

    @E("que o email do cliente seja {string}")
    public void queOEmailDoClienteSeja(String email) {
        Assert.assertNotNull("Resposta deve ser nula", this.requestHelper.getSuccessResponse());
        Assert.assertEquals("Email deve ser igual", email, Objects.requireNonNull(this.requestHelper.getSuccessResponse().getBody()).getEmail());
    }

    @E("o erro retornado durante a requisicao feito para o endpoint cliente deve conter {string}")
    public void oErroRetornadoDuranteARequisicaoFeitoParaOEndpointClienteDeveConter(String mensagemErro) {
        Assert.assertNotNull("Resposta de erro nao é nula", this.requestHelper.getErrorResponse().getMessage());
        Assert.assertNull("Resposta é nula", this.requestHelper.getSuccessResponse());
        Assert.assertTrue("Mensagem de erro deve conter", Objects.requireNonNull(this.requestHelper.getErrorResponse().getMessage()).contains(mensagemErro));
    }

    private static CriacaoClienteDTO criarClienteDto(String nome,String sobrenome, String cpf, String email) {
        CriacaoClienteDTO dto = new CriacaoClienteDTO();
        dto.setCpf(cpf);
        dto.setNome(nome);
        dto.setSobrenome(sobrenome);
        dto.setEmail(email);
        dto.setSenha("senha");
        return dto;
    }
}
