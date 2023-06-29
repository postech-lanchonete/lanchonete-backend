package br.com.lanchonetebairro.business;

import br.com.lanchonetebairro.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.api.dto.CriacaoClienteDTO;

public interface ClienteService {

    ClienteResponseDTO buscarPorCPF(String cpf);
    ClienteResponseDTO criarCliente(CriacaoClienteDTO clienteDTO);

}
