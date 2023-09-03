package br.com.lanchonetebairro.applicationrules.usecases.implementation.pedido;

import br.com.lanchonetebairro.applicationrules.usecases.UseCase;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("pedidoBuscarTodosUseCase")
public class PedidoBuscarTodosUseCase implements UseCase<Pedido, List<Pedido>> {

    private final PedidoGateway pedidoGateway;

    public PedidoBuscarTodosUseCase(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public List<Pedido> realizar(Pedido entrada) {
        return pedidoGateway.buscarPor(Example.of(entrada));
    }

}
