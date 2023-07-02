package br.com.lanchonetebairro.core.applications.services;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;

public interface ClienteService {

    ClienteResponseDTO buscarPorCPF(String cpf);
    ClienteResponseDTO criarCliente(CriacaoClienteDTO clienteDTO);

}
