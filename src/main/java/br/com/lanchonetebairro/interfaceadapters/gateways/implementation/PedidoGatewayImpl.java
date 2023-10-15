package br.com.lanchonetebairro.interfaceadapters.gateways.implementation;

import br.com.lanchonetebairro.applicationrules.exceptions.NotFoundException;
import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import br.com.lanchonetebairro.interfaceadapters.gateways.PedidoGateway;
import br.com.lanchonetebairro.interfaceadapters.repositories.PedidoRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoGatewayImpl implements PedidoGateway {
    private final PedidoRepository pedidoRepository;

    public PedidoGatewayImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> buscarPor(Example<Pedido> pedidoExample) {
        return pedidoRepository.findAll(pedidoExample);
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Pedido não encontrado com o id %d", id)));
    }

}
