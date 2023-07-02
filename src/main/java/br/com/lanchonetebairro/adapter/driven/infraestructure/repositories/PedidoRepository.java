package br.com.lanchonetebairro.adapter.driven.infraestructure.repositories;

import br.com.lanchonetebairro.core.domain.enums.StatusDoPedido;
import br.com.lanchonetebairro.core.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(StatusDoPedido statusDoPedido);
}