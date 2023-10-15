package br.com.lanchonetebairro.interfaceadapters.repositories;

import br.com.lanchonetebairro.enterpriserules.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}