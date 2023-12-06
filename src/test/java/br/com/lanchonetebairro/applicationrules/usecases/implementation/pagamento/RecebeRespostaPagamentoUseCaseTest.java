package br.com.lanchonetebairro.applicationrules.usecases.implementation.pagamento;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import br.com.lanchonetebairro.enterpriserules.entities.Pagamento;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusDoPedido;
import br.com.lanchonetebairro.enterpriserules.enums.StatusPagamento;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecebeRespostaPagamentoUseCaseTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private RecebeRespostaPagamentoUseCase recebeRespostaPagamentoUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPagamentoAprovado() {
        Pagamento pagamento = new Pagamento();
        Pedido pedido = criarStubPedido(pagamento);
        when(pedidoGateway.buscarPorId(anyLong())).thenReturn(pedido);
        when(pedidoGateway.salvar(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        pagamento.setStatus(StatusPagamento.APROVADO);
        Pedido resultado = recebeRespostaPagamentoUseCase.realizar(pagamento);

        assertEquals(resultado.getStatus(), StatusDoPedido.EM_PREPARACAO);
        verify(pedidoGateway).salvar(pedido);
    }

    @Test
    public void testPagamentoReprovado() {
        Pagamento pagamento = new Pagamento();
        Pedido pedido = criarStubPedido(pagamento);
        when(pedidoGateway.buscarPorId(anyLong())).thenReturn(pedido);
        when(pedidoGateway.salvar(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        pagamento.setStatus(StatusPagamento.REPROVADO);
        Pedido resultado = recebeRespostaPagamentoUseCase.realizar(pagamento);

        assertEquals(resultado.getStatus(), StatusDoPedido.FINALIZADO);
        verify(pedidoGateway).salvar(pedido);
    }

    private Pedido criarStubPedido(Pagamento pagamento) {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        pagamento.setValor(BigDecimal.ZERO);
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setId(1L);
        pagamento.setPedido(pedido);
        return pedido;
    }
}
