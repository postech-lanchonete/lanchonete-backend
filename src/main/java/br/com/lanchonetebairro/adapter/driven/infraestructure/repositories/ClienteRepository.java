package br.com.lanchonetebairro.adapter.driven.infraestructure.repositories;

import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}