package br.com.lanchonetebairro.infraestructure.repositories;

import br.com.lanchonetebairro.business.enums.StatusDoPedido;
import br.com.lanchonetebairro.infraestructure.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatusDoPedido(StatusDoPedido statusDoPedido);
}