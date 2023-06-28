package br.com.lanchonetebairro.persistence;

import br.com.lanchonetebairro.entities.Cliente;
import br.com.lanchonetebairro.entities.Pedido;
import br.com.lanchonetebairro.enums.StatusDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatusDoPedido(StatusDoPedido statusDoPedido);
}