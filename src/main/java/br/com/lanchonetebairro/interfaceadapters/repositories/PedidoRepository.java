package br.com.lanchonetebairro.interfaceadapters.repositories;

import br.com.lanchonetebairro.enterpriserules.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}