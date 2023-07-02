package br.com.lanchonetebairro.core.applications.services;

import br.com.lanchonetebairro.core.domain.entities.Cliente;

public interface ClienteService {
    Cliente buscarPorCpf(String cpf);

    Cliente salvar(Cliente cliente);
}
