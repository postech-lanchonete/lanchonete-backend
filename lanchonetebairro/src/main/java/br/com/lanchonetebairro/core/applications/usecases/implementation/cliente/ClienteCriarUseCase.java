package br.com.lanchonetebairro.core.applications.usecases.implementation.cliente;

import br.com.lanchonetebairro.adapter.driver.api.dto.ClienteResponseDTO;
import br.com.lanchonetebairro.adapter.driver.api.dto.CriacaoClienteDTO;
import br.com.lanchonetebairro.core.applications.mappers.ClienteMapper;
import br.com.lanchonetebairro.core.applications.services.ClienteService;
import br.com.lanchonetebairro.core.applications.usecases.UseCase;
import br.com.lanchonetebairro.core.domain.entities.Cliente;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class ClienteCriarUseCase implements UseCase<CriacaoClienteDTO, ClienteResponseDTO> {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteCriarUseCase(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }
    @Override
    public ClienteResponseDTO realizar(CriacaoClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        this.procurarPorDuplicidade(clienteDTO);
        try {
            clienteService.salvar(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("E-mail já utilizado. Utilize outro e-mail.");
        }
        return clienteMapper.toDto(cliente);
    }

    private void procurarPorDuplicidade(CriacaoClienteDTO clienteDTO) {
        Cliente clienteExample = new Cliente();
        clienteExample.setNome(clienteDTO.getNome());
        clienteExample.setSobrenome(clienteDTO.getSobrenome());
        clienteExample.setCpf(clienteDTO.getCpf());
        clienteService.buscarPor(Example.of(clienteExample))
                .stream()
                .findFirst()
                .ifPresent(cliente -> {
                    throw new DataIntegrityViolationException("Não é permitido inserir dois clientes com o mesmo nome, sobrenome e CPF.");
                });
    }
}
