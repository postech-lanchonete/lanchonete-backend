package br.com.lanchonetebairro.infraestructure.repositories;

import br.com.lanchonetebairro.infraestructure.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}