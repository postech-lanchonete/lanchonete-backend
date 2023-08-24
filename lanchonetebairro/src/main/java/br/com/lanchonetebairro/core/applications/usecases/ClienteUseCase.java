package br.com.lanchonetebairro.core.applications.usecases;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;

@Deprecated
public interface ClienteUseCase {

    ClienteResponseDTO buscarPorCPF(String cpf);
    ClienteResponseDTO criar(CriacaoClienteDTO clienteDTO);

}
