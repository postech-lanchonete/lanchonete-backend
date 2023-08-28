package br.com.lanchonetebairro.core.applications.services;

import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.data.domain.Example;

import java.util.List;

public interface ClienteService {
    Cliente buscarPorCpf(String cpf);

    Cliente salvar(Cliente cliente);

    List<Cliente> buscarPor(Example<Cliente> clienteExample);
}
