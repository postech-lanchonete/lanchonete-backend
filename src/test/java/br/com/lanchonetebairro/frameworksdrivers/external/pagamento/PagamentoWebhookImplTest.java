package br.com.lanchonetebairro.frameworksdrivers.external.pagamento;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.entities.Produto;
import br.com.lanchonetebairro.enterpriserules.enums.StatusPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PagamentoWebhookImplTest {

    @Mock
    private UseCase<Pagamento, Pedido> recebeRespostaPagamentoUseCase;

    @InjectMocks
    private PagamentoWebhookImpl pagamentoWebhook;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrarPagamento() {
        Pagamento pagamento = new Pagamento();
        Pedido pedido = criarStubPedido(pagamento);

        Pagamento pagamentoResponse = pagamentoWebhook.registrarPagamento(pedido);

        assertNotNull(pagamentoResponse);
        assertEquals(StatusPagamento.PENDENTE, pagamentoResponse.getStatus());
        assertEquals(BigDecimal.valueOf(100), pagamentoResponse.getValor());
        assertEquals(pedido, pagamentoResponse.getPedido());
    }

    @Test
    public void testSimulaAcionamentoWebhookResposta() throws InterruptedException, ExecutionException {
        Pagamento pagamento = new Pagamento();

        CompletableFuture<Void> future = pagamentoWebhook.simulaAcionamentoWebhookResposta(pagamento);
        future.get();

        verify(recebeRespostaPagamentoUseCase, times(1)).realizar(eq(pagamento));
    }

    private Pedido criarStubPedido(Pagamento pagamento) {
        Produto produto = new Produto();
        produto.setPreco(BigDecimal.valueOf(100));
        List<Produto> produtos = List.of(produto);
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        pagamento.setValor(BigDecimal.valueOf(100));
        Pedido pedido = new Pedido();
        pedido.setProdutos(produtos);
        pedido.setCliente(cliente);
        pedido.setId(1L);
        pagamento.setPedido(pedido);
        return pedido;
    }
}
