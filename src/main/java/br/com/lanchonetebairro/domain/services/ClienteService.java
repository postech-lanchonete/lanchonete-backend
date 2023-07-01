package br.com.lanchonetebairro.domain.services;

import br.com.lanchonetebairro.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.api.dto.CriacaoClienteDTO;

public interface ClienteService {

    ClienteResponseDTO buscarPorCPF(String cpf);
    ClienteResponseDTO criarCliente(CriacaoClienteDTO clienteDTO);

}
