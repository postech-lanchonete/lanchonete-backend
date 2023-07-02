package br.com.lanchonetebairro.core.applications.services.implementation;

import br.com.lanchonetebairro.adapter.driven.infrastructure.repositories.PedidoRepository;
import br.com.lanchonetebairro.core.applications.exceptions.NotFoundException;
import br.com.lanchonetebairro.core.applications.services.PedidoService;
import br.com.lanchonetebairro.core.domain.entities.Pedido;
import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
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

    @Override
    public List<Pedido> buscarPorStatus(StatusDoPedido status) {
        return pedidoRepository.findByStatus(status);
    }
}
